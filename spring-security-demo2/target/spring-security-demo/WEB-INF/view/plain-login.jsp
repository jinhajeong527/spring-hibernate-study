<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Custom Login Page</title>
	<style>
		.failed {
			color: red;
		}
	</style>
</head>
<body>
	<h3>My Custom Login Page</h3>
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser"
			   method="POST">
			   <!-- Login Error 체크하기 -->
			   <c:if test="${param.error != null}">
			   		<i class="failed">등록되지 않은 유저 이름 혹은 비밀번호를 입력하셨습니다.</i>
			   </c:if>
			   <!-- 스프링 시큐리티가 찾아볼 default form field name : username, password-->
			   <p>
			   	User name: <input type="text" name="username" />
			   </p>
			   <p>
			   	Password: <input type="password" name="password" />
			   </p>
			   
			   <input type="submit" value="Login" />
	</form:form>

</body>
</html>