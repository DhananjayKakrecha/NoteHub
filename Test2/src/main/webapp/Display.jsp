<%@page import="com.download.Notes"%>
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
	<h2>Notes</h2>
	<%
	String username = "user1";
	String subName = "dbms";
	NotesDAO dao = new NotesDAO();
	List<Notes> notes = dao.getAllNotes(subName);
	
	for(Notes note : notes){
	%>
		<table>
			<tr>
				<td>Subject Name :</td>
				<td><%= note.getSubName()%></td>
			</tr>
			<tr>
				<td>FileName :</td>
				<td><%= note.getFileName() %></td>
			</tr>
			<tr>
				<td>Notes By :</td>
				<td><%= note.getName() %></td>
			</tr>
			<tr>
				<form method="post" action="download">
				<input type="hidden" name="subName" value="<%= note.getSubName()%>">
				<input type="hidden" name="fileName" value="<%=  note.getFileName()%>">
				<td><input type="submit" value="Download"></td>
				</form>
			</tr>
			<tr>
				<% boolean result = dao.isPinned(username, note.getFileName());
					if(result){
				%>
				<form method="post" action="pin">
				<input type="hidden" name="userName" value="<%= username%>">
				<input type="hidden" name="fileName" value="<%=  note.getFileName()%>">
				<input type="hidden" name="action" value="unpin">
				<td><input type="submit" value="Unpin"></td>
				</form>
				
				<% }
					else{
				%>
				
				<form method="post" action="pin">
				<input type="hidden" name="userName" value="<%= username%>">
				<input type="hidden" name="fileName" value="<%=  note.getFileName()%>">
				<input type="hidden" name="action" value="pin">
				<td><input type="submit" value="Pin"></td>
				</form>
				
				<% } %>
			</tr>
		</table>
	
	<% } %>
</body>
</html>