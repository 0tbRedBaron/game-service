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
    <a role="button" href="${root}/admin/listGames" class="btn btn-default btn-block">Back to Games List</a>
</p>

<p>
    <br>
    <a role="button" href="${root}/logout" class="btn btn-default btn-block">Logout</a>
</p>
<form class="form-style">

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

        <c:choose>
            <c:when test="${not empty GAMES}">
                <c:forEach var="tempGame" items="${GAMES}">
                    <!-- set up a link for each game -->
                    <c:url var ="updateLink" value="/admin/loadGameToForm">
                        <c:param name="gameId" value="${tempGame.id}"/>
                    </c:url>

                    <!-- set up a link to delete a game -->
                    <c:url var = "deleteLink" value="/admin/deleteGame">
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
                            <a role="button" href="${deleteLink}" class="btn btn-danger" onclick="if (!(confirm('Are you sure you want to delete entry?'))) return false">Delete</a><br>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p><b> No GAMES found !!</b></p>
            </c:otherwise>
        </c:choose>

    </table>
</form>

</body>
</html>
