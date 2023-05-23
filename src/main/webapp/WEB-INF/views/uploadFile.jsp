<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/11/2023
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/upload/k" method="post" enctype="multipart/form-data">
    <input type="file" name="image">
    <button type="submit">upload</button>
</form>
</body>
</html>
