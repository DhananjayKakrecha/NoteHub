<%@page import="java.util.List"%>
<%@page import="com.download.NotesDAO"%>
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
	String username = (String) request.getSession().getAttribute("username");
	username.strip();

	NotesDAO dao = new NotesDAO();
	List<String> lists = dao.getLabels(username);
	%>
	<center>
		<h2>${username}</h2>
		<form method="post" action="SearchResult.jsp?username=${username}">
			<table>
				<tr>
					<td><input type="text" name="search"></td>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>

		<a href="./Archive.jsp?username=<%=username%>">Archive</a>
		<h3>Labels</h3>
		<form method="post" action="label">
			<table>
				<tr>
					<td>Name :</td>
					<td><input type="text" name="labelName"></td>
					<input type="hidden" name="username" value="<%=username%>">
					<td><input type="submit" value="Create"></td>
				</tr>
			</table>
		</form>
		<%
		for (String list : lists) {
		%>
		<a href="./Batman.jsp?username=<%=username%>&label=<%=list%>"><%=list%></a>
		<%
		}
		%>
		<a href="./Inbox.jsp?username=<%= username%>">Inbox</a>
	</center>
</body>
</html>