<%--
  Created by IntelliJ IDEA.
  User: Nam_Phuong
  Date: 2019-04-15
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer information</title>
</head>
<body>
    <h1>Customer information</h1>
    <table>
        <tr>
            <td>Name: </td>
            <td> ${requestScope["customer"].getName()}</td>
        </tr>

        <tr>
            <td>Email: </td>
            <td> ${requestScope["customer"].getEmail()}</td>
        </tr>

        <tr>
            <td>Address: </td>
            <td> ${requestScope["customer"].getAddress()}</td>
        </tr>

        <tr>
            <td> <a href="/customers">Back to customer list</a></td>
        </tr>
    </table>
</body>
</html>
