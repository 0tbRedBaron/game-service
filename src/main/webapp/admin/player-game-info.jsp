<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Search Games</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<script src="${root}/js/bootstrap.min.js"></script>

<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>
<p>
    <a role="button" href="${root}/admin/listGames" class="btn btn-default btn-primary btn-block">Back to Games List</a>
</p>

<p>
    <br>
    <a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a>
    <br>
</p>
<form class="form-style">

    <table class ="table table-striped table-hover">

        <thead>
            <tr>
                <th>Gamer Name</th>
                <th>Rating</th>
                <th>Review</th>
                <th>Date Created</th>
                <th>Date Updated</th>
                <th></th>
                <th></th>
            </tr>
        </thead>

    <c:forEach var="tempGame" items="${GAME_INFO}">
        <tr>
            <td>${tempGame.user_name}</td>
            <td>${tempGame.rating}</td>
            <td style="word-wrap: break-word; min-width: 400px; max-width: 400px;">${tempGame.review}</td>
            <td>${tempGame.created_timestamp}</td>
            <td>${tempGame.updated_timestamp}</td>
        </tr>
    </c:forEach>

    </table>
</form>

</body>
</html>
