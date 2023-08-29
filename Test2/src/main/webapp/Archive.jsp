<%@page import="com.download.Practicals"%>
<%@page import="com.download.PracticalsDAO"%>
<%@page import="com.note.NoteDAO"%>
<%@page import="com.note.Note"%>
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
	<%
	String uname = request.getParameter("username");
	NotesDAO dao = new NotesDAO();
	List<Notes> notes = dao.getPinnedNotes(uname);
	List<String> lists = dao.getLabels(uname);

	NoteDAO noteDao = new NoteDAO();
	List<Note> unotes = noteDao.getPinnedNotes(uname);

	PracticalsDAO pracDao = new PracticalsDAO();
	List<Practicals> pracs = pracDao.getPinnedPracticals(uname);
	%>

	<h2>Notes</h2>
	<h3><%=uname%></h3>
	<form method="post" action="Archivee.jsp?username=<%=uname%>">
		<table>
			<tr>
				<td><input type="text" name="filter"></td>
				<td><input type="submit" value="Filter"></td>
			</tr>
		</table>
	</form>
	<table>
		<th>
		<tr>
			<td>Topic :</td>
			<td>File Name :</td>
			<td>Subject Name :</td>
			<td>Unit :</td>
			<td>Provide by :</td>
			<td>Download</td>
		</tr>
		</th>

		<%
		for (Notes note : notes) {
		%>

		<tr>
			<td><%=note.getTopic()%></td>
			<td><%=note.getFileName()%></td>
			<td><%=note.getSubName()%></td>
			<td><%=note.getUnit()%></td>
			<td><%=note.getName()%></td>
			<td><form method="post" action="download">
					<input type="hidden" name="subName" value="<%=note.getSubName()%>">
					<input type="hidden" name="fileName"
						value="<%=note.getFileName()%>"> <input type="submit"
						value="download">
				</form></td>
			<td><form method="post" action="pin">
					<input type="hidden" name="userName" value="<%=uname%>"> <input
						type="hidden" name="fileName" value="<%=note.getFileName()%>">
					<input type="hidden" name="action" value="unpin"> <input
						type="submit" value="Unpin">
				</form></td>
			<td><form method="post" action="pin">
					<input type="hidden" name="userName" value="<%=uname%>"> <input
						type="hidden" name="fileName" value="<%=note.getFileName()%>">
					<input type="hidden" name="action" value="pinlabel">
					<select name="label">
						<%
						for (String list : lists) {
							boolean result = dao.isLabelled(uname, note.getFileName(), list);

							if (!result) {
						%>
						<option value="<%=list%>"><%=list%></option>
						<%
						}
						}
						%>
					</select> <input type="submit" value="Add">
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
			<td><%=unote.getTitle()%></td>
			<td><%=unote.getDescription()%></td>
			<td><%=unote.getDate()%></td>
			<td><form method="post" action="pinNote">
					<input type="hidden" name="username" value="<%=uname%>"> <input
						type="hidden" name="nid" value="<%=unote.getId()%>"> <input
						type="hidden" name="action" value="unpin"> <input
						type="submit" value="Unpin">
				</form></td>
			<td><form method="post" action="pinNote">
					<input type="hidden" name="username" value="<%=uname%>"> <input
						type="hidden" name="nid" value="<%=unote.getId()%>"> <input
						type="hidden" name="title" value="<%=unote.getTitle()%>">
					<input type="hidden" name="action" value="pinLabel"> <select
						name="label">
						<%
						for (String list : lists) {
							boolean result = noteDao.isLabelled(uname, unote.getId(), list);

							if (!result) {
						%>
						<option value="<%=list%>"><%=list%></option>
						<%
						}
						}
						%>
					</select> <input type="submit" value="Add">
				</form></td>
		</tr>

		<%
		}
		%>
	</table>

	<h3>Practicals</h3>
	<table>
		<th>
		<tr>
			<td>Topic :</td>
			<td>File Name :</td>
			<td>Subject Name :</td>
			<td>Unit :</td>
			<td>Provide by :</td>
			<td>Download</td>
		</tr>
		</th>

		<%
		for (Practicals prac : pracs) {
		%>

		<tr>
			<td><%=prac.getTopic()%></td>
			<td><%=prac.getFileName()%></td>
			<td><%=prac.getSubName()%></td>
			<td><%=prac.getUnit()%></td>
			<td><%=prac.getName()%></td>
			<td><form method="post" action="download">
					<input type="hidden" name="subName" value="<%=prac.getSubName()%>">
					<input type="hidden" name="fileName"
						value="<%=prac.getFileName()%>"> <input type="submit"
						value="download">
				</form></td>
			<td><form method="post" action="pin">
					<input type="hidden" name="userName" value="<%=uname%>"> <input
						type="hidden" name="fileName" value="<%=prac.getFileName()%>">
					<input type="hidden" name="action" value="unpinprac"> <input
						type="submit" value="Unpin">
				</form></td>
			<td><form method="post" action="pin">
					<input type="hidden" name="userName" value="<%=uname%>"> <input
						type="hidden" name="fileName" value="<%=prac.getFileName()%>">
					<input type="hidden" name="action" value="pinlabel">
					<select name="label">
						<%
						for (String list : lists) {
							boolean result = dao.isLabelled(uname, prac.getFileName(), list);

							if (!result) {
						%>
						<option value="<%=list%>"><%=list%></option>
						<%
						}
						}
						%>
					</select> <input type="submit" value="Add">
				</form></td>
		</tr>

		<%
		}
		%>
	</table>
</body>
</html>