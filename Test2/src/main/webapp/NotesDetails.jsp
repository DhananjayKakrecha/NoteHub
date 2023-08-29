<%@page import="com.note.NoteDAO"%>
<%@page import="com.note.Note"%>
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
		<br> <a href="./AddNote.jsp?username=<%=username%>">Add Note</a>
		<a href="./Inbox.jsp?username=<%=username%>">Inbox</a> <br> <br>
		<br>
		<h3>Notes</h3>
		<form method="post" action="NotesDetail.jsp?username=<%=username%>">
			<table>
				<tr>
					<td><input type="text" name="filter"></td>
					<td><input type="submit" value="Filter"></td>
				</tr>
			</table>
		</form>

		<%
		NoteDAO notesDao = new NoteDAO();
		List<Note> notes = notesDao.getNote(username);
		for (Note note : notes) {
			Integer nid = (Integer) note.getId();
		%>

		<a href="UNote.jsp?username=<%= username%>&nid=<%= nid.toString()%>" style="text-decoration:none;font-color:black;">
			<div>
				<h4><%=note.getTitle()%></h4>
				<h5><%=note.getDate()%></h5>
				<h3><%=note.getDescription()%></h3>
				<form action="pinNote?username=<%= username%>" method="post">
					<input type="hidden" name="title" value="<%= note.getTitle()%>">
					<input type="hidden" name="nid" value="<%=note.getId()%>">
					<input type="hidden" name="action" value="pin">
					<input type="submit" value="Pin">
				</form>
				<form action="Share.jsp?username=<%=username%>" method="post">
					<input type="hidden" name="nid" value="<%=note.getId()%>">
					<input type="submit" value="Share">
				</form>
				<form action="deleteNote" method="post">
					<input type="hidden" name="nid" value="<%=note.getId()%>">
					<input type="submit" value="Delete">
				</form>
				<a href="VersionControl.jsp?username=<%= username%>&nid=<%= nid.toString()%>"><button>Version Control</button></a>
			</div>
		</a>
		<hr>
		<%
		}
		%>
	</center>
</body>
</html>