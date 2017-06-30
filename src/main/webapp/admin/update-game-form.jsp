<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Update Game</title>
</head>
<body>

<h3>Update Game</h3>

<form action="${root}/admin/updateGame" method="post">

    <input type="hidden" name="gameId" value="${THE_GAME.id}"/>

    <table>
        <tbody>

        <tr>
            <td><label><b>Title:</b></label></td>
            <td><input type="text" name="title" value="${THE_GAME.title}" required/></td>
        </tr>
        <tr>
            <td><label><b>Publisher:</b></label></td>
            <td><input type="text" name="publisher" value="${THE_GAME.publisher}" required/></td>
        </tr>

        <tr>
            <td><label><b>Description:</b></label></td>
            <td><input type="text" name="description" value="${THE_GAME.description}" required/></td>
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
