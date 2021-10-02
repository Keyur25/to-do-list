<%@ page import="uk.ac.ucl.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<%
    session.setAttribute("previousPage", "/removeItems.jsp");
    String listName = (String) request.getAttribute("listName");
    List<Item> list = (List<Item>) request.getAttribute("list");
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage==null) errorMessage = "";
    int listSize;
    try
    {
        listSize = list.size();
    }
    catch (NullPointerException e)
    {
        listSize = 0;
    }
%>
<body>
<div class="Title" style="text-align: center;">
    <h2>Remove Items From <%=listName%></h2>
</div>
<div class="ErrorSpace" style="text-align: center;">
    <p style="color:red;"><%=errorMessage%></p>
</div>
<div class="TableBG" style="text-align: center;width: 100%">
    <div class="MainInputTable" style="text-align: center;float: left;width: 65%;">
        <table style="width:100%;text-align: center;">
            <tr>
                <th>Type</th>
                <th>Value</th>
            </tr>
            <%
                if (listSize>0)
                {
                    for (Item item : list)
                    {
                        String type = item.getType();
                        String value = item.getValue();
            %>
            <tr>
                <td><%=type%>
                </td>
                <td><%=value%>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <%
            if (listSize<=0)
            {
        %>
        <p>No Items In List</p>
        <%
            }
        %>
    </div>
    <div class="SideInputTable" style="text-align: center;float: left; margin-left: 5%;">
        <div class="RemoveItem" style="text-align: center;float:bottom">
            <form method="POST" action="${pageContext.request.contextPath}/removeFromList.html">
                <div class="ChooseItemToRemove">
                    <label for="itemToRemove"><b>Choose Item To Remove</b></label>
                    <br>
                    <br>
                    <select name="itemToRemove" id="itemToRemove">
                        <%
                            for (Item item : list)
                            {
                                String type = item.getType();
                                String value = item.getValue();
                                String displayTypeAndValue = type+" - "+value;
                        %>
                        <option value="<%=type%>;<%=value%>"><%=displayTypeAndValue%></option>
                        <%
                            }
                        %>
                    </select>
                    <br>
                </div>
                <div class="RemoveItemSubmit" style="text-align: center;float:bottom">
                    <br>
                    <input type="submit" class="SubmitButton" value="Remove Item"/>
                    <br>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>