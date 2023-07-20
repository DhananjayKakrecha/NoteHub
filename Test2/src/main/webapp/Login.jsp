<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<title>Login</title>
<link rel="stylesheet" href="./index.css">
</head>
<body>

	<div class="container col-xl-10 col-xxl-8 px-4 py-5 mt-5">
		<div class="row align-items-center g-lg-5 py-5">
			<div class="col-md-10 mx-auto col-lg-5 py-5">
				<div class="form-box">
					<div class="form-value">
						<form action="login" method="post">
							<h2>Login</h2>
							<div class="inputbox">
								<ion-icon name="body-outline"></ion-icon>
								<input type="text" id="username" name="username" required /> <label
									for="Username">Username</label>
							</div>
							<div class="inputbox">
								<ion-icon name="lock-closed-outline"></ion-icon>
								<input type="password" class="form-control" id="password"
									name="password" required /> <label for="">Password</label>
							</div>
							<button type="submit">Log in</button>
							<div class="register">
								<p>
									Don't have a account <a href="UserRegistration.jsp">Register</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>