<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage==null) errorMessage = "";
%>
<body>
<div class="Title" style="text-align: center;">
    <h2>Existing List Name</h2>
</div>
<div class="ErrorSpace" style="text-align: center;">
    <p style="color:red;"><%=errorMessage%></p>
</div>
<div class="EnterName" style="text-align: center;">
    <form method="POST" action="${pageContext.request.contextPath}/existingListFrame.html">
        <input type="text" id="List Name" name="listName" placeholder="Enter List Name">
        <br>
        <br>
        <input class="SubmitButton" type="submit" value="Find"/>
    </form>
</div>
</body>
</html>
