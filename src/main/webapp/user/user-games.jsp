<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.myproject.gameapp.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Welcome</title>

    <!-- Bootstrap -->
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

<h1 align="center">Welcome,  <b><%=((User)request.getSession().getAttribute("myUser")).getName()%>!!</b></h1>

<h2 align="center"> Your personal Game Collection! </h2>
<br>
<p>
    <a role="button" href="${root}/logout" class="btn btn-default" align="right">Logout</a>
</p>
<br>
<p>
    <a role="button" href="${root}/listGamesForUser" class="btn btn-primary" align="right">Game Catalog</a>
</p>
<br>
<br>
<form class="form-style">

    <table class ="table table-striped table-hover">

        <thead>
        <tr>
            <th>Title</th>
            <th>Publisher</th>
            <th>Description</th>
            <th>Your Review</th>
            <th>Your Rating</th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <%--- the attribute "GAME_LIST" comes from the "request" object ---%>
        <c:forEach var="tempGame" items="${GAME_LIST}">

            <!-- set up a link to Delete a game -->
            <c:url var = "deleteLink" value="/deleteGame">
                <c:param name="gameId" value="${tempGame.game_id}"/>
            </c:url>
            <!-- set up a link to Update a game -->
            <c:url var = "updateLink" value="/loadGameToForm">
                <c:param name="gameId" value="${tempGame.game_id}"/>
            </c:url>

            <tr>
                <td> ${tempGame.title} </td>
                <td> ${tempGame.publisher} </td>
                <td style="word-wrap: break-word; min-width: 400px; max-width: 400px;">
                        ${tempGame.description}
                </td>
                <td style="word-wrap: break-word; min-width: 400px; max-width: 400px;"> ${tempGame.review} </td>
                <td class="text-center"> ${tempGame.rating} </td>

                <td>
                    <a role="button" href="${updateLink}" class="btn btn-primary">Update</a><br>
                </td>

                <td>
                    <a role="button" href="${deleteLink}" class="btn btn-danger">Delete</a><br>
                </td>

                <td>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>