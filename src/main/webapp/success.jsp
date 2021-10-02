<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}styles.css" />
</head>
<body style="text-align: center;">
<h2><%=request.getAttribute("message")%></h2>
</body>
</html>
