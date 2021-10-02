<%@ page import="java.util.ArrayList" %>
<%@ page import="uk.ac.ucl.model.Item" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Generic List Application</title>
  <div style="text-align: center;padding-bottom:3%"><h1><a class="HomePageButton" href="index.html">Generic List Application</a></h1></div>
  <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<%
  Hashtable<String, ArrayList<Item>> allLists = (Hashtable<String, ArrayList<Item>>) request.getAttribute("allLists");
  int maxLength = (int) request.getAttribute("maxLength");
  String filePath = "Images/";
%>
<body>
<div class="Title" style="text-align: center;">
  <h2><%=allLists.size()%> Lists Found</h2>
</div>
<div class="SearchBox" style="text-align: center;">
  <form method="POST" action="${pageContext.request.contextPath}/search.html">
    <div class="SearchInput">
      <input type="text" style="text-align: center" placeholder="Search" name="searchString">
    </div>
    <div class="SearchInput">
      <br>
      <input type="submit" class="SubmitButton" value="ENTER">
    </div>
  </form>
</div>
<div class="HintSpace" style="text-align: center;">
  <p>To reset press the enter button on an empty search field!</p>
</div>
<div class="WarningSpace" style="text-align: center;">
  <p>Images are only displayed if they are located in the Images directory in the project structure!</p>
  <br>
</div>
<div class="DisplayListBG" style="text-align:center;">
  <div class="DisplayTable" style="text-align:center;overflow:auto">
    <%
      if (allLists.size()>0)
      {
    %>
    <table style="width:100%;text-align: center;">
      <tr>
        <th>List Name</th>
        <%
          for(int i = 0; i<maxLength; i++)
          {
        %>
        <th>Item <%=i+1%></th>
        <%
          }
        %>
      </tr>
      <%
        Enumeration<String> allKeys = allLists.keys();
        while(allKeys.hasMoreElements())
        {
          String key = allKeys.nextElement();
          ArrayList<Item> list = allLists.get(key);
      %>
      <tr>
        <td><%=key%></td>
        <%
          int counter = 0;
          for(int i = 0; i < maxLength; i++)
          {
            if (counter<list.size())
            {
              Item item = list.get(i);
              String type = item.getType();
              String value = item.getValue();
              if (type.equals("url"))
              {
        %>
        <td><a href=<%=value%>><%=value%></a></td>
        <%
        }
        else if (type.equals("listType"))
        {
        %>
        <form method="POST" action="${pageContext.request.contextPath}/search.html">
          <td>
            <input type="hidden" name="searchString" value="<%=value%>">
            <input type="hidden" name="listNameOnly" value="true">
            <input type="submit" class="ListButton" value="<%=value%>">
          </td>
        </form>
        <%
        }
        else if (type.equals("image"))
        {
          %>
            <td>
              <img src="<%=filePath+value%>" width="50" height="50">
              <p><%=value%></p>
            </td>
        <%
        }
        else
        {
        %>
        <td><%=value%></td>
        <%
            }
            }
            else
            {
              %>
        <td><%=" "%></td>
        <%
            }
            counter++;
          }
        %>
      </tr>
      <%
        }
      %>
    </table>
    <%
      }
    %>
  </div>
</div>
</body>
</html>
