<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>Overflow-Dash</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>




<h1 class="m-4">Dojo-Overflow</h1> 

<a href="/questions/new" class="m-4 btn btn-warning">Post a Question</a>
<hr>




<h3 class="m-3">Current Questions:</h3>
<table class="m-2 table table-hover">
    <thead>
        <tr>
            <th>Questions</th>
            <th>Tags</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${questions}" var="question">
        <tr>
            <td><a href="/questions/${question.id}">${question.aQuestion}</td>
            <td>
            <c:forEach items="${question.tags}" var="tag">
			    <span>#${ tag.subject } <c:if test="${ question.tags.indexOf(tag) != question.tags.size() - 1}"></c:if></span>
			</c:forEach>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>

</body>
</html>