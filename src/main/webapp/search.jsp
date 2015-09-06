<%--
  Created by IntelliJ IDEA.
  User: Kos
  Date: 04.09.2015
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>Test application</title>
  <style>
    table {
      font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
      font-size: 14px;
      border-radius: 10px;
      border-spacing: 0;
      text-align: center;
    }
    th {
      background: #BCEBDD;
      color: blueviolet;
      text-shadow: 0 1px 1px #2D2020;
      padding: 10px 20px;
    }
    th, td {
      border-style: solid;
      border-width: 0 1px 1px 0;
      border-color: white;
    }
    th:first-child, td:first-child {
      text-align: left;
    }
    th:first-child {
      border-top-left-radius: 10px;
    }
    th:last-child {
      border-top-right-radius: 10px;
      border-right: none;
    }
    td {
      padding: 10px 20px;
      background: #F8E391;
    }
    tr:last-child td:first-child {
      border-radius: 0 0 0 10px;
    }
    tr:last-child td:last-child {
      border-radius: 0 0 10px 0;
    }
    tr td:last-child {
      border-right: none;
    }
  </style>
</head>
<body>
<form action="/search" method="get">
  <table>
    <tr>
      <td>Category</td>
      <td>Product</td>
      <td>Min price</td>
      <td>Max price</td>
      <td></td>
    </tr>
    <tr>
      <td><input type="text" name="cat"></td>
      <td><input type="text" name="prod"></td>
      <td><input type="text" name="minprice"></td>
      <td><input type="text" name="maxprice"></td>
      <td><input type="submit" value="Search"></td>
    </tr>
  </table>
  <br/>
  <div style="color: red">
    ${requestScope.get("error")}
  </div>
  <br/>
  <c:if test="${productList != null}">
  <table class="itemTable">
    <tr>
      <th>Category</th>
      <th>Prod</th>
      <th>Price</th>
    </tr>
  <c:forEach var="product" items="${productList}">
    <tr>
      <td>${product.category.name}</td>
      <td>${product.name}</td>
      <td>${product.price}</td>
    </tr>
  </c:forEach>
  </table>
  </c:if>
</form>
</body>
</html>
