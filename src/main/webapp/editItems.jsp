<%@ page import="uk.ac.ucl.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<script>
    function updateInputField() {
        let z = document.getElementById("itemToEdit").value;
        let x = z.split(";")[0];
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
        document.getElementById("EditValue").innerHTML =
            '<br>' +
            '<label for="newValue"><b>Edit Value</b></label>' +
            '<br>' +
            '<br>' +
            '<input name="newValue" id="newValue" type="'+x+'" placeholder="Enter Value" '+y+'>' +
            '<br>';
    }
</script>
<%
    session.setAttribute("previousPage", "/editItems.jsp");
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
    <h2>Edit Items In <%=listName%></h2>
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
    <div class="SideInputTable" style="text-align: center;float: left;margin-left: 5%;">
        <div class="EditList" style="text-align: center;float:bottom">
            <form method="POST" action="${pageContext.request.contextPath}/editList.html">
                <div class="ChooseItemToEdit">
                    <label for="itemToEdit"><b>Type of Item</b></label>
                    <br>
                    <br>
                    <select name="itemToEdit" id="itemToEdit" onchange="updateInputField()">
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
                <div class="EditValue" id="EditValue">
                    <br>
                    <label for="newValue"><b>Edit Value</b></label>
                    <br>
                    <br>
                    <input name="newValue" id="newValue" type="text" placeholder="Enter Value">
                    <br>
                </div>
                <div class="EditItemSubmit" style="text-align: center;float:bottom">
                    <br>
                    <input class="SubmitButton" type="submit" value="Edit Item"/>
                    <br>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>