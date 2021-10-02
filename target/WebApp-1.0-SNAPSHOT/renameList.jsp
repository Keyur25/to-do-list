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
    <h2>Rename  <%=(String) request.getAttribute("listName")%></h2>
</div>
<div class="ErrorSpace" style="text-align: center;">
    <p style="color:red;"><%=errorMessage%></p>
</div>
<div class="EnterName" style="text-align: center;">
    <form method="POST" action="${pageContext.request.contextPath}/renameList.html">
        <input type="text" id="newListName" name="newListName" placeholder="Enter New List Name">
        <br>
        <br>
        <input type="submit" class="SubmitButton" value="Rename"/>
    </form>
</div>
</body>
</html>
