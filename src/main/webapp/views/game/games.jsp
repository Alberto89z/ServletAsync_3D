<%--
  Created by IntelliJ IDEA.
  User: mario
  Date: 8/4/2021
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Listado de juegos</title>
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/styles.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
    <table class="table">
        <thead class="table-dark">
            <tr>
                <th>id</th>
                <th>Nombre</th>
                <th>categoria</th>
                <th>fecha de registro</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${ ListGames }" var="game" varStatus="status">
            <tr>
                <td>${ status.count }</td>
                <td>${ game.nameGame }</td>
                <td>${ game.NameCategory }</td>
                <td>${ game.datePremiere }</td>
                <td>
                    <c:if test="${ game.status == 1 }">
                        <span class="badge rounded-pill bg-success">Activo</span>
                    </c:if>
                    <c:if test="${ game.status == 0 }">
                        <span class="badge rounded-pill bg-danger">Inactivo</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
