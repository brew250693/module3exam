<%--
  Created by IntelliJ IDEA.
  User: Brew
  Date: 5/27/2021
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<center>
    <h1>Product Search</h1>
</center>

<div align="center">
    <form method="post">
        <div class="search">
            <input type="text" placeholder="Search" name="search">
            <button>
                <a href="/product?action=search">Search</a>
            </button>
        </div>
    </form>
</div>
</body>
</html>
