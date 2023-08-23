<%@page import="com.note.Note"%>
<%@page import="java.util.List"%>
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
<table>


<% String username = request.getParameter("username"); 
   NoteDAO dao = new NoteDAO();
   List<Note> notes = dao.getSharedNote(username);
   
   for(Note note : notes){
%>
<tr>
	<td><%= note.getTitle()%></td>
	<td><%= note.getSender() %></td>
	<td><%= note.getDate() %></td>
</tr>
<% } %>
</table>
</body>
</html>