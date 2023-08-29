<%@page import="com.download.Practicals"%>
<%@page import="com.download.PracticalsDAO"%>
<%@page import="com.note.NoteDAO"%>
<%@page import="com.note.Note"%>
<%@page import="com.download.NotesDAO"%>
<%@page import="com.download.Notes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String label = request.getParameter("label");
	String uname = request.getParameter("username");
	NotesDAO dao = new NotesDAO();
	List<Notes> notes = dao.getAllLabelNotes(uname, label);
	
	NoteDAO noteDao = new NoteDAO();
	List<Note> unotes = noteDao.getAllLabelNotes(uname, label);
	
	PracticalsDAO pracDao = new PracticalsDAO();
	List<Practicals> pracs = pracDao.getLabelledPracticals(uname, label);
%>

<h1><%= uname%></h1>
	<h2>Notes</h2>
	<table>
		<th>
		<tr>
			<td>File Name :</td>
			<td>Subject Name :</td>
			<td>Provide by :</td>
			<td>Download</td>
		</tr>
		</th>

		<%
		for (Notes note : notes) {
		%>

		<tr>
			<td><%=note.getFileName()%></td>
			<td><%=note.getSubName()%></td>
			<td><%=note.getName()%></td>
			<td><form method="post" action="download">
					<input type="hidden" name="subName" value="<%=note.getSubName()%>">
					<input type="hidden" name="fileName"
						value="<%=note.getFileName()%>"> <input type="submit"
						value="download">
				</form></td>
			<td><form method="post" action="pin">
				<input type="hidden" name="userName" value="<%= uname%>">
				<input type="hidden" name="fileName" value="<%=  note.getFileName()%>">
				<input type="hidden" name="label" value="<%= label%>">
				<input type="hidden" name="action" value="unpinlabel">
				<td><input type="submit" value="Unpin"></td>
				</form></td>
		</tr>

		<%
		}
		%>
	</table>
	
	<h3>Note</h3>
	<table>
		<th>
		<tr>
			<td>Title :</td>
			<td>Description :</td>
			<td>Date :</td>
		</tr>
		</th>

		<%
		for (Note unote : unotes) {
		%>

		<tr>
			<td><%=unote.getTitle() %></td>
			<td><%=unote.getDescription()%></td>
			<td><%=unote.getDate()%></td>
			<td><form method="post" action="pinNote">
				<input type="hidden" name="username" value="<%= uname%>">
				<input type="hidden" name="nid" value="<%= unote.getId()%>">
				<input type="hidden" name="label" value="<%= label%>">
				<input type="hidden" name="action" value="unPinLabel">
				<input type="submit" value="Unpin">
				</form></td>
			<td></td>
		</tr>

		<%
		}
		%>
	</table>
	<h3>Practicals </h3>
	<table>
		<th>
		<tr>
			<td>File Name :</td>
			<td>Subject Name :</td>
			<td>Provide by :</td>
			<td>Download</td>
		</tr>
		</th>

		<%
		for (Practicals prac : pracs) {
		%>

		<tr>
			<td><%=prac.getFileName()%></td>
			<td><%=prac.getSubName()%></td>
			<td><%=prac.getName()%></td>
			<td><form method="post" action="download">
					<input type="hidden" name="subName" value="<%=prac.getSubName()%>">
					<input type="hidden" name="fileName"
						value="<%=prac.getFileName()%>"> <input type="submit"
						value="download">
				</form></td>
			<td><form method="post" action="pin">
				<input type="hidden" name="userName" value="<%= uname%>">
				<input type="hidden" name="fileName" value="<%=  prac.getFileName()%>">
				<input type="hidden" name="label" value="<%= label%>">
				<input type="hidden" name="action" value="unpinlabel">
				<td><input type="submit" value="Unpin"></td>
				</form></td>
		</tr>

		<%
		}
		%>
	</table>
</body>
</html>