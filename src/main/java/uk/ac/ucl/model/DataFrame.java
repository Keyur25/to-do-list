package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataFrame
{
    private Hashtable<String, ArrayList<Item>> frame;

    public DataFrame()
    {
        frame = new Hashtable<>();
    }

    public Hashtable<String, ArrayList<Item>> getDataFrame()
    {
        return frame;
    }

    public void addList(String key, ArrayList<Item> items)
    {
        frame.put(key, items);
    }

    public ArrayList<Item> getList(String key)
    {
        return frame.get(key);
    }

    public Enumeration<String> getKeys()
    {
        return frame.keys();
    }

    public boolean isKeyUsed(String key)
    {
        return frame.containsKey(key);
    }

    public void deleteList(String key)
    {
        frame.remove(key);
    }

    public int getIndexOfItemInList(String key, String type, String value)
    {
        ArrayList<Item> list = frame.get(key);
        for (int i = 0; i < list.size(); i++)
        {
            Item item = list.get(i);
            if (item.getType().equals(type) & item.getValue().equals(value)) return i;
        }
        return -1;
    }

    public void updateValueOfItemInList(String key, String newValue, int index)
    {
        ArrayList<Item> list = frame.get(key);
        list.get(index).setValue(newValue);
    }

    public void updateKey(String currentKey, String newKey)
    {
        ArrayList<Item> list = frame.get(currentKey);
        frame.remove(currentKey);
        frame.put(newKey, list);
    }

    public void removeItemFromList(String key, int index)
    {
        ArrayList<Item> list = frame.get(key);
        list.remove(index);
        frame.put(key, list);
    }
}