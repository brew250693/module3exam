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
        <div align="center">
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Color</th>
                    <th>Category</th>
                </tr>
                <c:forEach var="book" items="${searchByName}">
                    <tr>
                        <td><c:out value="${book.id}"/></td>
                        <td><c:out value="${book.name}"/></td>
                        <td><c:out value="${book.price}"/></td>
                        <td><c:out value="${book.amount}"/></td>
                        <td><c:out value="${book.color}"/></td>
                        <td><c:out value="${book.category.name}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
</form>
</div>
</body>
</html>
