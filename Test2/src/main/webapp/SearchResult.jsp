<%@page import="com.download.Practicals"%>
<%@page import="com.download.PracticalsDAO"%>
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
	String username = request.getParameter("username");
	String search = request.getParameter("search");
	NotesDAO dao = new NotesDAO();
	List<Notes> notes = dao.getNotes(search);
	
	PracticalsDAO pracDao = new PracticalsDAO();
	List<Practicals> pracs = pracDao.getPracticals(search);
	%>

	<h1><%= username%></h1>
	<h2>Notes</h2>
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
			<td><%=note.getTopic() %></td>
			<td><%=note.getFileName()%></td>
			<td><%=note.getSubName()%></td>
			<td><%=note.getUnit() %></td>
			<td><%=note.getName()%></td>
			<td><form method="post" action="download">
					<input type="hidden" name="subName" value="<%=note.getSubName()%>">
					<input type="hidden" name="fileName"
						value="<%=note.getFileName()%>"> <input type="submit"
						value="download">
				</form></td>
				<td>
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
	</td>
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
			<td><%=prac.getTopic() %></td>
			<td><%=prac.getFileName()%></td>
			<td><%=prac.getSubName()%></td>
			<td><%=prac.getUnit() %></td>
			<td><%=prac.getName()%></td>
			<td><form method="post" action="download">
					<input type="hidden" name="subName" value="<%=prac.getSubName()%>">
					<input type="hidden" name="fileName"
						value="<%=prac.getFileName()%>"> <input type="submit"
						value="download">
				</form></td>
				<td>
				<% boolean result = dao.isPinned(username, prac.getFileName());
					if(result){
				%>
				<form method="post" action="pin">
				<input type="hidden" name="userName" value="<%= username%>">
				<input type="hidden" name="fileName" value="<%=  prac.getFileName()%>">
				<input type="hidden" name="action" value="unpinprac">
				<td><input type="submit" value="Unpin"></td>
				</form>
				
				<% }
					else{
				%>
				
				<form method="post" action="pin">
				<input type="hidden" name="userName" value="<%= username%>">
				<input type="hidden" name="fileName" value="<%=  prac.getFileName()%>">
				<input type="hidden" name="action" value="pinprac">
				<td><input type="submit" value="Pin"></td>
				</form>
				
				<% } %>
	</td>
		</tr>

		<%
		}
		%>
	</table>
</body>
</html>