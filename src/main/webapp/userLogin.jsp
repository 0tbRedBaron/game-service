<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Login</title>
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${root}/js/bootstrap.min.js"></script>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

<h1 align="center"><strong>Welcome!!!</strong></h1>
<br>
<form action="${root}/userLogin">
    <table align="center">
        <tr>
            <td align="right"><strong>Login:</strong></td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td align="right"><strong>Password:</strong></td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" class="btn btn-success btn-block" value="Log In"></td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" class="btn btn-primary btn-block" value="Registration" name="register"></td>
        </tr>
    </table>
</form>
</body>
</html>