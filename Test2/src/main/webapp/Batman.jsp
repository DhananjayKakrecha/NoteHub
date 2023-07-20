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
				<input type="hidden" name="action" value="unPinLabel">
				<td><input type="submit" value="Unpin"></td>
				</form></td>
		</tr>

		<%
		}
		%>
	</table>
</body>
</html>