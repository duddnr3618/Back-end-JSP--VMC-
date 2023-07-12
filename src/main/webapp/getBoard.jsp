<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mysite.board.BoardDTO" %>    


<%
	//세션에 지정된 변수의 값을 불러옴.
	BoardDTO board = (BoardDTO) session.getAttribute("board");
	
	//브라우져에 출력
	out.println(board.getSeq());
	out.println(board.getTitle());
	out.println(board.getWrite());
	out.println(board.getContent());
	out.println(board.getCnt());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
<style>
div {width:700px; margin:0 auto;}
td {padding:10px }
</style>
</head>
<body>
<div>
	<h1>글 상세 페이지</h1>
	
	<hr>
	<br><br>
	
	<form action = "updateBoard.do" method = "post">
	
	<!-- 글 수정시 seq 변수값을 서버로 전송 -->
	<input type = "hidden" name = "seq" value = "<%= board.getSeq() %>">
	
		<table border = "1px" cellspacing = "0" cellpadding = "0">
			<tr> <td bgcolor = "orange">제목</td> 
				<td><input type = "text" name = "title" value = "<%= board.getTitle() %>"></td></tr>
			<tr> <td bgcolor = "orange">작성자</td> 
				<td><%= board.getWrite() %></td></tr>
			<tr> <td bgcolor = "orange">내용</td> 
				<td><textarea rows="10" cols="50" name = "content"><%= board.getContent() %></textarea></td></tr>
			<tr> <td bgcolor = "orange">등록일</td> 
				<td><%= board.getRegdate() %></td></tr>
			<tr> <td bgcolor = "orange">조회수</td> 
				<td><%= board.getCnt() %></td></tr>
			<tr> <td colspan = "2"><input type = "submit" value = "글 수정"></td></tr>
		
		</table>
	</form>
	
	<br><br>
	<a href = "getBoardList.do">글 목록보기</a>
	
	<br><br>
	<a href ="insertBoard.jsp">글 쓰기</a>
	
	<br><br>
	<a href ="deleteBoard.do?seq=<%= board.getSeq() %>">글 삭제</a>
	
</div>	
</body>
</html>