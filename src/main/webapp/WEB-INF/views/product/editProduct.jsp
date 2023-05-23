<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/12/2023
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #888;
        }

        input[type="text"],
        input[type="number"],
        input[type="file"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            margin-bottom: 15px;
        }

        textarea {
            height: 100px;
        }

        button[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Update Product</h2>
    <form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/productController/update">
        <label for="id">#</label>
        <input type="text"  id="id" name="productId" value="${editProduct.productId}">

        <label for="name">Product Name</label>
        <input id="name" type="text" name="productName" value="${editProduct.productName}">

        <label for="price">Price</label>
        <input type="number" id="price" name="price" value="${editProduct.price}">
        <label for="price">Price</label>
        <input type="number" id="quantity" name="quantity" value="${editProduct.quantity}">
        <label for="description">Description</label>
        <input type="text" id="description" name="description" value="${editProduct.description}">
        <label>Image</label>
        <input type="file" name="image" value="${editProduct.image}">
        <label for="catalogId">CatalogId</label>
        <input type="text" id="catalogId" name="catalogId" value="${editProduct.catalogId}">
        <label for="status">Status</label>
        <select name="status" id="status">${editProduct.status}
            <option value="true">true</option>
            <option value="false">false</option>
        </select>
        <button type="submit" >Update</button>
    </form>
</div>
</body>
</html>
