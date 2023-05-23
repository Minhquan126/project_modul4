<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%response.sendRedirect("/homeController/goHome");%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<a href="<%=request.getContextPath()%>/productController/getAll">Hello Servlet</a><br>
<a href="<%=request.getContextPath()%>/upload/image">file upload</a><br>
<a href="<%=request.getContextPath()%>/userController/registerPage">Register</a><br>
<a href="<%=request.getContextPath()%>/homeController/goHome">Go to home</a><br>
<a href="<%=request.getContextPath()%>/adminController/getAll">Go to admin</a>




</body>
</html>