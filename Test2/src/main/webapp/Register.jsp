<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration Form</title>


<link rel="stylesheet" href="./index_1.css">
</head>
<body>
	<section>
      <div class="form-box">
        <div class="form-value">
          <form action="uregister" method="post">
            <h2>Register here</h2>
            <div class="inputbox">
              <ion-icon name="mail-outline"></ion-icon>
              <input type="email" id="email" name="email" required />
              <label for="">Email</label>
            </div>
            <div class="inputbox">
              <ion-icon name="body-outline"></ion-icon>
              <input type="text" id="fullname" name="fullname" required />
              <label for="fullname">Full Name:</label>
            </div>
            <div class="inputbox">
              <ion-icon name="caret-forward-circle-outline"></ion-icon>
              <input
                type="text"
                class="form-control"
                id="username"
                name="username"
                required
              />
              <label for="Username">Username</label>
            </div>
            <div class="inputbox">
              <ion-icon name="lock-closed-outline"></ion-icon>
              <input
                type="password"
                class="form-control"
                id="password"
                name="password"
                required
              />
              <label for="">Password</label>
            </div>
            <button type="submit" name="type" value="user">Submit</button>
          </form>
        </div>
      </div>
    </section>
    <script
      type="module"
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
    ></script>
    <script
      nomodule
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
    ></script></body>
</html>
    