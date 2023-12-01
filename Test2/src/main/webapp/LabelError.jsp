<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>NoteHub</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
<link rel="stylesheet" href="./login.css" />
</head>
<body>
	<div class="container login-box">
		<div class="login">
			<div class="logo">
				<div class="logo-img">
					<img src="./resources/NoteHubIcon1.png" height="60px" />
				</div>
				<p>NoteHub</p>
			</div>
			<p>OOPS!!!..Got an Error</p>
			<p id="sub-head">Label Already Exists!!...</p>
			<div class="error-buttons">
				<button class="btn btn-warning">
					<a href="./Label.jsp">Please..Try Again!!</a>
				</button>
			</div>
		</div>
	</div>
</body>
</html>
