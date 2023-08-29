<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String username = request.getParameter("username");
	%>

	<form method="post" action="addNote?username=<%= username%>">
		<table>
			<tr>
				<td>Title :</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>Description :</td>
				<td><textarea name="desc" rows="10" cols="10"></textarea></td>
			</tr>
			<tr>
				<input type="hidden" name="action" value="add">
				<td><input type="submit" value="Add"></td>
			</tr>
		</table>
	</form>
</body>
</html>