<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Update Game</title>
    <link href="${root}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${root}/js/bootstrap.min.js"></script>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <br><br>
    </div>
</div>

<h3>Update Game <b> <c:out value="${THE_GAME.title}"/> </b></h3>

<form action="${root}/updateGame" method="post">

    <input type="hidden" name="gameId" value="${THE_GAME.game_id}"/>

    <table>
        <tbody>
        <tr>
            <td><label><b>Rating:</b></label></td>
            <td>
                <select class="form-control" name="rating" value="${THE_GAME.rating}">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label><b>Review:</b></label></td>
            <td>
                <textarea class="form-control" rows="5" name="review"><c:out value="${THE_GAME.review}"/></textarea>
            </td>
        </tr>
        <br>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="btn btn-success"/></td>
        </tr>

        </tbody>
    </table>
</form>

<p>
    <a role="button"  href="${root}/userCollection" class="btn btn-default" >Back to Collection</a>
</p>

<p>
    <br>
    <a role="button" href="${root}/logout" class="btn btn-primary" >Logout</a>
</p>

</body>

</html>