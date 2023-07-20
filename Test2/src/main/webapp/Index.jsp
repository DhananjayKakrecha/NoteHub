<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form method="post" action="SearchResult.jsp">
			<table>
				<tr>
					<td><input type="text" name="search"></td>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>
	</center>
	<h1>File :</h1>
	<form action="download" method="post">
		File Name : <input type="text" name="fileName"> <br>
		<input type="submit">
	</form>
</body>
</html>