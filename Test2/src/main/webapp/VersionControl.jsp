<%@page import="com.note.VersionControl"%>
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
<% String uname = request.getParameter("username"); 
	int nid = Integer.parseInt(request.getParameter("nid"));
	Integer notesId = (Integer) nid;
	NoteDAO noteDao = new NoteDAO();
	List<VersionControl> versions = noteDao.getVids(nid, uname);
%>
<h3><%= nid%></h3>
<table>
	<tr>
		<td>Date :</td>
	</tr>
	
	<% for(VersionControl version : versions){ 
		Integer vid = (Integer) version.getVid();
	%>
	
		<tr>
			<td><%= version.getDate()%></td>
			<td><a href="./VersionNote.jsp?username=<%= uname%>&vid=<%= vid.toString()%>&nid=<%= notesId.toString()%>">Get</a></td>
		</tr>
	<% } %>
</table>

</body>
</html>