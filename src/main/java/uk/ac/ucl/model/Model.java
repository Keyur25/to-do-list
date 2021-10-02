package uk.ac.ucl.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Model
{
  private File file;
  private DataFrame frame;
  private String currentListName;
  private ArrayList<Item> currentList;
  private String modifyAction;

  public Model()
  {
    file = new File("");
    frame = new DataFrame();
    currentListName = "";
    currentList = new ArrayList<>();
    modifyAction = "";
  }

  public void readFile(File file) throws IOException
  {
    this.file = file;
    ArrayList<String> allLists = getListsFromFile(file);
      addListsToFrame(allLists);
  }

  public String getCurrentListName()
  {
    return currentListName;
  }

  public void setCurrentListName(String currentListName)
  {
    this.currentListName = currentListName;
  }

  public ArrayList<Item> getCurrentList()
  {
    return currentList;
  }

  public void setCurrentList(ArrayList<Item> currentList)
  {
    this.currentList = currentList;
  }

  public String getModifyAction()
  {
    return modifyAction;
  }

  public void setModifyAction(String action)
  {
    modifyAction = action;
  }

  public String validateNewListName(String listName)
  {
    if (listName.contains("\"")|listName.contains(";")) return "List Name Cannot Contain \" or ; !";
    else if (listName.equals("")) return "List Name Cannot Be Empty!";
    else if (isListNameUsed(listName)) return "List Name Is Already In Use!";
    else return "";
  }

  public String validateNewItem(String type, String value)
  {
    if (value.contains("\"") | value.contains(";")) return "Item Cannot Contain \" or ; !";
    else if (type.equals("")) return "Type Cannot Be Empty!";
    else if (value.equals("")) return "Value Cannot Be Empty!";
    else if (type.equals("listType") & !isListNameUsed(value)) return "List "+value+" Does Not Exist!";
    else return "";
  }

  public void addItemToCurrentList(String type, String value) throws IOException
  {
    Item item = new Item(type, value);
    currentList.add(item);
    saveCurrentList();
  }

  public void saveCurrentList() throws IOException
  {
    frame.addList(currentListName, currentList);
    saveFrame();
  }

  public String validateExistingListName(String listName)
  {
    if (!isListNameUsed(listName)) return "List Not Found!";
    else return "";
  }

  public ArrayList<Item> getListWithName(String listName)
  {
    return frame.getList(listName);
  }

  public void editItemInCurrentList(String currentType, String currentValue, String newValue) throws IOException {
    for (Item item:currentList)
    {
      if (item.getType().equals(currentType) & item.getValue().equals(currentValue))
      {
        item.setValue(newValue);
        break;
      }
    }
    saveCurrentList();
  }

  public String validateRemoveItem()
  {
    if (currentList.size()<=1) return "Last Item In List Cannot Be Deleted!";
    else return "";
  }

  public void removeItemFromCurrentList(String type, String value) throws IOException
  {
    for (int i = 0; i < currentList.size(); i++)
    {
      Item item = currentList.get(i);
      if (item.getType().equals(type) & item.getValue().equals(value))
      {
        currentList.remove(i);
        break;
      }
    }
    saveCurrentList();
  }

  public void renameCurrentList(String newListName) throws IOException
  {
    String oldListName = currentListName;
    renameInstancesOfOldListName(oldListName, newListName);
    currentListName = newListName;
    frame.updateKey(oldListName, newListName);
    saveFrame();
  }

  public void deleteCurrentList() throws IOException
  {
    deleteInstancesOfCurrentListName();
    frame.deleteList(currentListName);
    saveFrame();
  }

  public Hashtable<String, ArrayList<Item>> getFrame()
  {
    return frame.getDataFrame();
  }

  public int lengthOfLongestList()
  {
    Hashtable<String, ArrayList<Item>> dataFrame = frame.getDataFrame();
    return computeLengthOfLongestList(dataFrame);
  }

  public int lengthOfLongestList(Hashtable<String, ArrayList<Item>> dataFrame)
  {
    return computeLengthOfLongestList(dataFrame);
  }

  public Hashtable<String, ArrayList<Item>> searchFor(String searchString, String isListOnly)
  {
    if (isListOnly.equals("true")) // return only the list with the name that is equal to the search string
    {
      Hashtable<String, ArrayList<Item>> list = new Hashtable<>();
      list.put(searchString, frame.getList(searchString));
      return list;
    }
    else // return all lists that contain the search string
    {
      return checkAllListsFor(searchString);
    }
  }

  private int computeLengthOfLongestList(Hashtable<String, ArrayList<Item>> dataFrame)
  {
    int max = 0;
    Enumeration<String> allKeys = dataFrame.keys();
    while(allKeys.hasMoreElements())
    {
      String key = allKeys.nextElement();
      int length = dataFrame.get(key).size();
      if (length>max) max = length;
    }
    return max;
  }

  private Hashtable<String, ArrayList<Item>> checkAllListsFor(String searchString)
  {
    Hashtable<String, ArrayList<Item>> matchedLists = new Hashtable<>();
    Enumeration<String> allKeys = frame.getKeys();
    while(allKeys.hasMoreElements())
    {
      String key = allKeys.nextElement();
      ArrayList<Item> list = frame.getList(key);
      boolean matched = isSearchStringInList(searchString, key, list);
      if (matched) matchedLists.put(key, list); // if the search string is in the list then add it to matchedLists
    }
    return matchedLists;
  }

  private boolean isSearchStringInList(String searchString, String key, ArrayList<Item> list)
  {
    for (Item item : list)
    {
      if (item.getValue().contains(searchString)) return true;
    }
    return key.contains(searchString);
  }

  private void deleteInstancesOfCurrentListName()
  {
    Enumeration<String> keys = frame.getKeys();
    while (keys.hasMoreElements())
    {
      String key = keys.nextElement();
      int index = frame.getIndexOfItemInList(key, "listType", currentListName);
      if (index!=-1)
      {
        frame.removeItemFromList(key, index);
      }
    }
  }

  private void renameInstancesOfOldListName(String oldListName, String newListName)
  {
    Enumeration<String> keys = frame.getKeys();
    while (keys.hasMoreElements())
    {
      String key = keys.nextElement();
      int index = frame.getIndexOfItemInList(key, "listType", oldListName);
      if (index!=-1)
      {
        frame.updateValueOfItemInList(key, newListName, index);
      }
    }
  }

  private void saveFrame() throws IOException
  {
    ArrayList<String> frameAsString = convertFrameToJSON();
    writeStringFrameToFile(frameAsString);
  }

  private void writeStringFrameToFile(ArrayList<String> frameAsString) throws IOException
  {
    String newLine = "\r\n";
    FileWriter myWriter = new FileWriter(file);
    myWriter.write("["+newLine);
    for (String list:frameAsString)
    {
      myWriter.write(list+newLine);
    }
    myWriter.write("]"+newLine);
    myWriter.close();
  }

  private ArrayList<String> convertFrameToJSON()
  {
    ArrayList<String> frameAsString = new ArrayList<>();
    Enumeration<String> keys = frame.getKeys();
    while (keys.hasMoreElements())
    {
      String key = keys.nextElement();
      String listAsJSON = convertListToJSON(key, frame.getList(key));
      frameAsString.add(listAsJSON);
    }
    return removeCommaFromLastList(frameAsString);
  }

  private ArrayList<String> removeCommaFromLastList(ArrayList<String> frameAsString)
  {
    int indexOfLastList = frameAsString.size()-1;
    String lastList = frameAsString.get(indexOfLastList);
    int lengthOfLastList = lastList.length();
    frameAsString.set(indexOfLastList, lastList.substring(0, lengthOfLastList-1));
    return frameAsString;
  }

  private String convertListToJSON(String key, ArrayList<Item> list) // converts list to string and adds JSON formatting
  {
    StringBuilder JSONList = new StringBuilder("{\"Name\":\"" + key + "\"");
    for (Item item:list)
    {
      String type = item.getType();
      String value = item.getValue();
      JSONList.append(",\"").append(type).append("\":\"").append(value).append("\"");
    }
    JSONList.append("},");
    return JSONList.toString();
  }

  private boolean isListNameUsed(String listName)
  {
    return frame.isKeyUsed(listName);
  }

  private void addListsToFrame(ArrayList<String> allLists)
  {
    for (String stringList : allLists)
    {
      String listName = "";
      ArrayList<Item> list = new ArrayList<>();
      String[] itemsInStringList = extractItemsFromStringList(stringList);
      listName = getListNameAndUpdateList(listName, list, itemsInStringList);
      frame.addList(listName, list);
    }
  }

  private String getListNameAndUpdateList(String listName, ArrayList<Item> list, String[] itemsInStringList)
  {
    for (int i = 0; i < itemsInStringList.length; i++)
    {
      if (i==0)
      {
        listName = itemsInStringList[i].split("\":\"")[1];
      }
      else
      {
        Item item = getItem(itemsInStringList, i);
        list.add(item);
      }
    }
    return listName;
  }

  private Item getItem(String[] itemsInStringList, int i)
  {
    String[] item = itemsInStringList[i].split("\":\"");
    String type = item[0];
    String value = item[1];
    if (i ==(itemsInStringList.length-1))
    {
      value = value.substring(0,value.length()-1);
    }
    return new Item(type, value);
  }

  private String[] extractItemsFromStringList(String list)
  {
    if (list.endsWith("},"))
    {
      list = list.substring(1,list.length()-2);
    }
    else if (list.endsWith("}"))
    {
      list = list.substring(1,list.length()-1);
    }
    return list.split("\",\"");
  }

  private ArrayList<String> getListsFromFile(File file) throws IOException
  {
    ArrayList<String> allLists = new ArrayList<>();
    BufferedReader data = new BufferedReader(new FileReader(file));
    String line;
    while ((line = data.readLine())!=null)
    {
      if (!(line.equals("[") | line.equals("]"))) allLists.add(line);
    }
    return allLists;
  }
}