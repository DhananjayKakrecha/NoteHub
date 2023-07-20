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
	String fileName = request.getParameter("fileName");
	%>
	<form method="post" action="share">
		<table>
			<tr>
				<td>Share to:</td>
				<td><input type="text" name="receiver"></td>
				<input type="hidden" name="username" value="<%=username%>">
				<input type="hidden" name="fileName" value="<%=fileName%>">
				<td><input type="submit" value="Send"></td>
			</tr>
		</table>
	</form>
</body>
</html>