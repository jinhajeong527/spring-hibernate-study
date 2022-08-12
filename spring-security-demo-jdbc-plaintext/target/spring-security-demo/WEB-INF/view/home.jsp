<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags/" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>JJH Company Home Page</title>
</head>
<body>
	<h2>JJH Company Home Page</h2>
	<hr>
	
	<p>
	Welcome to the JJH Company Home Page
	</p>
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
		<input type="submit" value="Logout" />
	</form:form>

</body>
</html>