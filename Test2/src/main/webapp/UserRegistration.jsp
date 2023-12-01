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
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="login.css" />
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
        <p>Sign-Up</p>
        <p id="sub-head">Get Your Notes Here!!</p>
        <form action="uregister" method="post">
          <div class="login-inputs col-12">
            <input type="text" placeholder="Name" name="fullname" required />
          </div>
          <div class="login-inputs col-12">
            <input type="email" placeholder="E-Mail" name="email" required />
          </div>
          <div class="login-inputs col-12">
            <input
              type="text"
              placeholder="Username"
              name="username"
              required
            />
          </div>
          <div class="login-inputs col-12">
            <input
              type="password"
              placeholder="Password"
              name="password"
              required
            />
          </div>
          <div class="sign-button col-12">
            <button class="btn btn-warning" type="submit">Sign Up</button>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
