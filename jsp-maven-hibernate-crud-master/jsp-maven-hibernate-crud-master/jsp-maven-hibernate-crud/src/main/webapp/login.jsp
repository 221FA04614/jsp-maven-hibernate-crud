<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>


<style>
<styles>
  body{
  background-color: blue;
  text-align: center;
}
button{
  height: 40px;
  font-size: 20px;
  background-color: bisque;
  
}

  </styles>

.form-signin {
    width: 100%;
    max-width: 330px;
    padding: 15px;
    margin: auto;
}

.form-signin .checkbox {
    font-weight: 400;
}

.form-signin .form-floating:focus-within {
    z-index: 2;
}

.form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}

@media (min-width: 768px) {
    .bd-placeholder-img-lg {
        font-size: 3.5rem;
    }
}
</style>
</head>
<body>
    ${failureMessage}
    ${sessionMessage}
    <main class="form-signin text-center mt-5">
        <form action="LoginServlet" method="post">
            <div class="form-floating mt-4">
                <input type="email" class="form-control" id="floatingEmail"
                    name="email" placeholder="name@example.com" required>
                <label for="floatingEmail">Email</label>
            </div>

            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword"
                    name="password" placeholder="Password" required>
                <label for="floatingPassword">Password</label>
            </div>

            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me">
                    Remember me
                </label>
            </div>

            <input class="w-100 btn btn-lg btn-primary" type="submit"
                name="submit" value="Login">

            <p class="mt-4 text-muted">
                Don't have an account? <a href="register.jsp">Sign up</a>
            </p>
            <p class="mt-5 mb-3 text-muted">&copy; 2024</p>
        </form>
    </main>
</body>
</html>
