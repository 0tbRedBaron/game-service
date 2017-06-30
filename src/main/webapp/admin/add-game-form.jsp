<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Add Game</title>
</head>
<body>

<h3>Add Game</h3>

<form action="${root}/admin/createGame" method="post">

    <table>
        <tbody>
        <tr>
            <td><label><b>Title:</b></label></td>
            <td><input type="text" name="title" required/></td>
        </tr>
        <tr>
            <td><label><b>Publisher:</b></label></td>
            <td><input type="text" name="publisher" required/></td>
        </tr>

        <tr>
            <td><label><b>Description:</b></label></td>
            <td><input type="text" name="description" required/></td>
        </tr>

        <br>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save" /></td>
        </tr>

        </tbody>
    </table>
</form>

<p>
    <a href="${root}/admin/listGames">Back to Game List</a>
</p>
<p>
    <br>
    <b><a href="${root}/logout">Logout</a></b>
</p>
</body>
</html>