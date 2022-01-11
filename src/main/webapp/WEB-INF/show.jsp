<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>Show Product</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>




<h1 class="m-4">${question.aQuestion}</h1>  
<h4 class="m-4">Tags:  <c:forEach items="${question.tags}" var="tag">
			<span>#${ tag.subject } </span>
			</c:forEach></h4>

<a href="/questions" class="m-4">Home</a>
<hr>



<div class="container">
		<h3>Answers:</h3>
		<ul>
		<c:forEach items="${ question.answers }" var="answer">
			<li>${ answer.theAnswer }</li>		
		</c:forEach>
		</ul>
		<hr />
		<h3>Add your answer:</h3>

<form:form action="/questions/submitanswer" method="post" modelAttribute="answer">
		<div class="form-group">
		<form:label path="theAnswer">Answer</form:label>
		<form:errors path="theAnswer"/>
		<form:textarea class="form-control" path="theAnswer"></form:textarea>
		</div>
		<form:hidden path="question" value="${ question.id }"/>
		<button class="btn btn-primary">Submit Answer</button>
		</form:form>
	</div>







<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>