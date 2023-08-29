<%@page import="com.note.Note"%>
<%@page import="com.note.NoteDAO"%>
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
	NoteDAO dao = new NoteDAO();
	Note note = dao.getNoteById(nid);
%>
<form method="post" action="addNote?username=<%= username%>">
		<table>
			<tr>
				<td><%= note.getDate()%></td>
			</tr>
			<tr>
				<td>Title :</td>
				<td><input type="text" name="title" value="<%= note.getTitle()%>"></td>
			</tr>
			<tr>
				<td>Description :</td>
				<td><textarea name="desc" rows="10" cols="10"><%= note.getDescription() %></textarea></td>
			</tr>
			<tr>
				<input type="hidden" name="nid" value="<%= nid%>">
				<input type="hidden" name="action" value="update">
				<td><input type="submit" value="Update"></td>
			</tr>
		</table>
	</form>


</body>
</html>