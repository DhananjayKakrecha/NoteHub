<%@page import="com.note.VersionControl"%>
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
	String uname = request.getParameter("username");
	int nid = Integer.parseInt(request.getParameter("nid"));
	int vid = Integer.parseInt(request.getParameter("vid"));
	NoteDAO noteDao = new NoteDAO();
	VersionControl vcnote = noteDao.getNoteVC(nid, vid, uname);
	%>
	
	<h5><%= vcnote.getDate() %></h5>
	<h3>Title : <%= vcnote.getTitle()%></h3>
	<h4>Description : <%= vcnote.getDescription() %></h4>
		
</body>
</html>