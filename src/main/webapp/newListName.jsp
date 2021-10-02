<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/header.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage==null) errorMessage = "";
%>
<body>
<div class="Title" style="text-align: center;">
    <h1>Create List</h1>
</div>
<div class="ErrorSpace" style="text-align: center;">
    <p style="color:red;"><%=errorMessage%></p>
</div>
<div class="EnterName" style="text-align: center;">
    <form method="POST" action="<c:url value="/newListFrame.html"/>">
        <input type="text" id="List Name" name="listName" placeholder="Enter List Name">
        <br>
        <br>
        <input class="SubmitButton" type="submit" value="Create"/>
    </form>
</div>
</body>
</html>
