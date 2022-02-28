<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmation page</title>
</head>
<body>
Hello World of Spring!
<br><br>
<img src="${pageContext.request.contextPath}/resources/gif/Student-ID.gif" width="100"/>
<br><br>
Student name ${param.studentName}
<br><br>
${message}
</body>
</html>