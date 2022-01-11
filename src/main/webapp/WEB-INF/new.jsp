<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>New Question</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>

<h2 class="m-4">Create and Post your Question</h2>
<hr>


<form:form action="/questions/create" method="POST" modelAttribute="question" class="m-4">
    <p>
        <form:label class="col-1 col-form-label" path="aQuestion">Question: </form:label>
        <form:errors class="small" path="aQuestion"/>
        <form:textarea class="form-control" path="aQuestion"/>
    </p>
    <p>
        <form:label class="col-1 col-form-label" path="separateTags">Tags: </form:label>
        <form:errors class="small" path="separateTags"/>
        <form:input class="form-control" path="separateTags"/>
    </p>
    <input type="submit" value="Post Question"/>
</form:form>   


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>