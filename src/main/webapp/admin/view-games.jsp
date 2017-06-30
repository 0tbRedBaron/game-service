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
    <title>BootstrapTemplate</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${root}/js/bootstrap.min.js"></script>

<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>
<a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a>

<br><br>

<form class="form-style" action="${root}/admin/searchGames">

    <div>
        <label>Search Game:<input type="text" name ="gameName" required/></label> <input type="submit" value="Search">
    </div>

</form>
<br>

<form class="form-style" action="${root}/admin/listGames" method = "get">

    <input type="button" value="Add Game"
           onclick="window.location.href='/gameapp/admin/add-game-form.jsp'"
    /> <br><br>

    <table class ="table table-striped table-hover">

        <thead>
        <tr>
            <th>Title</th>
            <th>Publisher</th>
            <th>Description</th>
            <th>Average Rating</th>
            <th>Total Votes</th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <%--- the attribute "GAME_LIST" comes from the "request" object ---%>
        <c:forEach var="tempGame" items="${GAME_LIST}">

            <!-- set up a link for each game -->
            <c:url var ="updateLink" value="/admin/loadGameToForm">
                <c:param name="gameId" value="${tempGame.id}"/>
            </c:url>

            <!-- set up a link to delete a game -->
            <c:url var = "deleteLink" value="/admin/deleteGame">
                <c:param name="gameId" value="${tempGame.id}"/>
            </c:url>

            <!-- set up a link to view game info -->
            <c:url var = "gameInfoLink" value="/admin/viewGameInfo">
                <c:param name="gameId" value="${tempGame.id}"/>
            </c:url>

            <tr>
                <td> ${tempGame.title} </td>
                <td> ${tempGame.publisher} </td>
                <td style="word-wrap: break-word; min-width: 400px; max-width: 400px;">
                        ${tempGame.description}
                </td>
                <td class="text-center"> ${tempGame.avg_rating} </td>
                <td class="text-center"> ${tempGame.vote_count} </td>

                <td>
                    <a role="button" href="${updateLink}" class="btn btn-primary">Update</a><br>
                </td>

                <td>
                    <a role="button" href="${gameInfoLink}" class="btn btn-primary">Game info</a><br>
                </td>

                <td>
                    <a role="button" href="${deleteLink}" class="btn btn-danger" onclick="if (!(confirm('Are you sure you want to delete entry?'))) return false">Delete</a><br>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
