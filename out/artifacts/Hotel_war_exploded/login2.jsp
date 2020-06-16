<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 4/22/2020
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <script type="text/javascript">
        history.pushState(null, null, 'login.jsp');
        window.addEventListener('popstate', function (event) {
            history.pushState(null, null, 'login.jsp');
        });

    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <!-- Custom fonts for this template-->
    <link href="site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="site/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <form action="ls?action=login" method="post">
                <div class="row form-group">
                    <div class="col col-md-3">
                        <input type="text" style="width: 300px;"
                               name="username" placeholder=" username"
                               class="form-control">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col col-md-3">
                        <input type="password" style="width: 300px;"
                               name="password" placeholder=" password"
                               class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me">
                            Remember Password
                        </label>
                    </div>
                </div>
                <c:if test="${not empty success}">
                    <label style="color: green">${success}</label>
                </c:if>
                <c:if test="${not empty invalid}">
                    <label style="color: red">${invalid}</label>
                </c:if>
                <button class="btn btn-primary btn-block" type="submit">sign in</button>
                <div class="text-center">
                    <a class="d-block small mt-3" href="register.html">Register an Account</a>
                    <a class="d-block small" href="ls?action=forgotPassword">Forgot Password?</a>
                </div>
            </form>


        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="site/vendor/jquery/jquery.min.js"></script>
<script src="site/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="site/vendor/jquery-easing/jquery.easing.min.js"></script>

</body>

</html>



<!--  <form class="login100-form validate-form" action="ls?action=login">
<span class="login100-form-title">
Member Login
</span>

<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
<input class="input100" type="text" name="username" placeholder="Email">
<span class="focus-input100"></span>
<span class="symbol-input100">
<i class="fa fa-envelope" aria-hidden="true"></i>
</span>
</div>

<div class="wrap-input100 validate-input" data-validate = "Password is required">
<input class="input100" type="password" name="password" placeholder="Password">
<span class="focus-input100"></span>
<span class="symbol-input100">
<i class="fa fa-lock" aria-hidden="true"></i>
</span>
</div>
<c:if test="${not empty success}">
    <label style="color: green">${success}</label>
</c:if>
<c:if test="${not empty invalid}">
    <label style="color: red">${invalid}</label>
</c:if>
<div class="container-login100-form-btn">
<button class="login100-form-btn">
Login
</button>
</div>

<div class="text-center p-t-12">
<span class="txt1">
Forgot
</span>
<a class="txt2" href="ls?action=forgotPassword">
Username / Password?
</a>
</div>

<div class="text-center p-t-136">
<a class="txt2" href="#">
Create your Account
<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
</a>
</div>
</form>-->