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
	int nid = Integer.parseInt(request.getParameter("nid"));
	%>
	<form method="post" action="share">
		<table>
			<tr>
				<td>Share to:</td>
				<td><input type="text" name="receiver"></td>
				<input type="hidden" name="sender" value="<%=username%>">
				<input type="hidden" name="nid" value="<%= nid%>">				
				<td><input type="submit" value="Send"></td>
			</tr>
		</table>
	</form>
</body>
</html>