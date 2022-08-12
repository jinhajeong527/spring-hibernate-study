<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- search 기능 추가 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Genres From TMDB</title>
<!-- reference our style sheet -->
<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css" />
	  
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>TMDB - Genre List</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<!--  검색창 추가 -->
			<button id="submit">장르 가져오기</button>
			<table>
				<tr>
					<th>id</th>
					<th>genre</th>
				</tr>
				<div class="targeted"></div>
			</table>
		
		</div>
	
	</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script>
	$(document).ready(function () {
		
    	$("#submit").click(function(e) {
            CallAPI();
    });

    function CallAPI() {
        $.ajax({
        	
        	type: "GET",
            url: "https://api.themoviedb.org/3/genre/tv/list?api_key=153470773bab918898ca2900fb65077b&language=ko-KR",
            dataType: "text", //전송받을 데이터를 변환시킬 컨텐츠 타입
            //contentType: "text",
            success: function (result) {
                console.log(result);
                $('.targeted').html(result);
            },
            error: function (request, status, error) {
                console.log(request);
                console.log(status);
                console.log(error);
            }
        });
    }

	});  
</script>
    

</body>
</html>