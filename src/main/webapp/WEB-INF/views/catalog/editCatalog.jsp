<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/12/2023
  Time: 2:25 PM
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
    <form method="post" enctype="multipart/form-data" action="/catalogController/update">
        <label for="id">#</label>
        <input type="number"  id="id" name="catalogId" value="${editCatalog.catalogId}" readonly>

        <label for="name">Product Name</label>
        <input id="name" type="text" name="catalogName" value="${editCatalog.catalogName}">

        <label for="description">Price</label>
        <input type="text" id="description" name="description" value="${editCatalog.description}">
        <label for="country">Description</label>
        <input type="text" id="country" name="country" value="${editCatalog.country}">
        <div class="form-group">
            <label for="image">Image</label>
            <input type="file" class="form-control" id="image" name="image" value="${editCatalog.image}">
        </div>
        <button type="submit" >Update</button>
    </form>
</div>
</body>
</html>