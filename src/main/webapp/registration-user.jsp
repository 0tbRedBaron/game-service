<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Registration</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

<h1 align="center">Enter your data</h1>

<form action="${root}/registration" method="post">
    <table align="center">
        <tr>
            <td align="right">First Name:</td>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <td align="right">Login:</td>
            <td><input type="text" name="login" required></td>
        </tr>
        <tr>
            <td align="right">Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td align="right">Confirm password:</td>
            <td><input type="password" name="confirm password" required></td>
        </tr>
        <tr>
            <td align="right">Email:</td>
            <td><input type="text" name="email" required></td>
        </tr>

            <td></td>
            <td><input type="submit" value="Registration" class="btn btn-success btn-block"></td>
        </tr>
    </table>
</form>
</body>
</html>
