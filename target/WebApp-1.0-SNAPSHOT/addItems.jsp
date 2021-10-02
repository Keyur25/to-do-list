<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.Item" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<script>
    function updateInputField() {
        let x = document.getElementById("inputTypes").value;
        let y = "";
        if (x==="listType")
        {
            x = "text";
        }
        if (x==="image")
        {
            x = "file";
            y = "accept='image/*'";
        }
        document.getElementById("ChooseItem").innerHTML =
            '<br>' +
            '<label for="value"><b>Value Of Item<b></label>' +
            '<br>' +
            '<br>' +
            '<input name="value" id="value" type="'+x+'" placeholder="Enter Value" '+y+'>' +
            '<br>';

    }
</script>
<%
    session.setAttribute("previousPage", File.separator+"addItems.jsp");
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
    <h2>Add Items To <%=listName%></h2>
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
        <div class="AddToList" style="text-align: center;float:bottom">
            <form method="POST" action="${pageContext.request.contextPath}/addToList.html">
                <div class="ChooseType" style="text-align: center;float:bottom">
                    <label for="inputTypes"><b>Type of Item</b></label>
                    <br>
                    <br>
                    <select name="type" id="inputTypes" onchange="updateInputField()">
                        <option value="text">Text</option>
                        <option value="url">URL</option>
                        <option value="listType">List</option>
                        <option value="image">Image</option>
                    </select>
                    <br>
                </div>
                <div class="ChooseValue" id="ChooseItem" style="text-align: center;float:bottom">
                    <br>
                    <label for="value"><b>Value Of Item</b></label>
                    <br>
                    <br>
                    <input name="value" id="value" type="text" placeholder="Enter Value">
                    <br>
                </div>
                <div class="AddItemSubmit" style="text-align: center;float:bottom">
                    <br>
                    <input class="SubmitButton" type="submit" value="Add Item"/>
                    <br>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>