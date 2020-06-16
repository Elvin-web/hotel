<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/16/2019
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Forget Password</title>

    <!-- Custom fonts for this template-->
    <link href="site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="site/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Forgot password</div>
        <div class="card-body">
            <form action="ls?action=forgot" method="post">
                <div class="row form-group">
                    <div class="col col-md-3">
                        <input type="email" style="width: 360px;"
                               name="email" placeholder=" Email"
                               class="form-control">
                    </div>
                </div>
                <c:if test="${not empty success}">
                    <label style="color: green">${success}</label>
                </c:if>
                <c:if test="${not empty invalid}">
                    <label style="color: red">${invalid}</label>
                </c:if>
                <button class="btn btn-primary btn-block" type="submit">Send</button>
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


















