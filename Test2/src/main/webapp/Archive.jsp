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
	%>

	<h2>Notes</h2>
	<h3><%=uname%></h3>
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
				<input type="hidden" name="action" value="unpin">
				<input type="submit" value="Unpin">
				</form></td>
			<td><form method="post" action="labelPin">
					<input type="hidden" name="userName" value="<%=uname%>"> <input
						type="hidden" name="fileName" value="<%=note.getFileName()%>">
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
			<td><a href="Share.jsp?username=<%= uname%>&fileName=<%= note.getFileName()%>">Share</a></td>
		</tr>

		<%
		}
		%>
	</table>

</body>
</html>