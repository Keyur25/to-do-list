<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<%
    session.setAttribute("previousPage", "/deleteList.jsp");
    String listName = (String) request.getAttribute("listName");
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage==null) errorMessage = "";
%>
<body>
<div class="Title" style="text-align: center;">
    <h2>Warning! Deleting <%=listName%> Will Remove All Instances Of It!</h2>
    <br>
    <h2>Are You Sure You Want To Delete This List?</h2>
</div>
<div class="ErrorSpace" style="text-align: center;">
    <p style="color:red;"><%=errorMessage%></p>
</div>
<div class="DeleteListMain" style="text-align: center;">
    <form method="POST" action="${pageContext.request.contextPath}/deleteList.html">
        <div class="YesOrNo" style="text-align: center;float:bottom">
            <br>
            <br>
            <select name="option" id="option">
                <option value="no">No</option>
                <option value="yes">Yes</option>
            </select>
            <br>
        </div>
        <div class="DeleteListSubmit" style="text-align: center;float:bottom">
            <br>
            <input type="submit" class="SubmitButton" value="Delete List"/>
            <br>
        </div>
    </form>
</div>
</body>
</html>