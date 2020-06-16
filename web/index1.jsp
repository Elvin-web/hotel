<%@ page import="az.elvin.hotel.model.LoginUser" %><%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/7/2019
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript">
        history.pushState(null,null,'index1.jsp');
        window.addEventListener('popstate',function (event) {
            history.pushState(null,null,'index1.jsp');
        });

    </script>
    <script src="js/all.js"></script>
    <script src="js/all.min.js"></script>
    <link href="css/all.css" rel="stylesheet" media="all">
    <link href="css/all.min.css" rel="stylesheet" media="all">

    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">
    <!-- Title Page-->
    <title>Hotel</title>


   <!-- <link href="css/1/brands.css" rel="stylesheet" media="all">
    <link href="css/1/brands.min.css" rel="stylesheet" media="all">
    <link href="css/1/fontawesome.css" rel="stylesheet" media="all">
    <link href="css/1/fontawesome.min.css" rel="stylesheet" media="all">
    <link href="css/1/regular.css" rel="stylesheet" media="all">
    <link href="css/1/regular.min.css" rel="stylesheet" media="all">
    <link href="css/1/solid.css" rel="stylesheet" media="all">
    <link href="css/1/solid.min.css" rel="stylesheet" media="all">
    <link href="css/1/svg-with-js.css" rel="stylesheet" media="all">
    <link href="css/1/svg-with-js.min.css" rel="stylesheet" media="all">
    <link href="css/1/v4-shims.css" rel="stylesheet" media="all">
    <link href="css/1/v4-shims.min.css" rel="stylesheet" media="all">
-->

    <!-- Fontfaces CSS-->
    <link href="css/font-face.css" rel="stylesheet" media="all">
    <link href="css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <!-- Bootstrap CSS-->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="all">
    <!-- Vendor CSS-->
    <link href="css/animsition.min.css" rel="stylesheet" media="all">
    <link href="css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="css/animate.css" rel="stylesheet" media="all">
    <link href="css/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="css/slick.css" rel="stylesheet" media="all">
    <link href="css/select2.min.css" rel="stylesheet" media="all">
    <link href="css/perfect-scrollbar.css" rel="stylesheet" media="all">
    <!-- Main CSS-->
    <link href="css/theme.css" rel="stylesheet" media="all">
</head>
<body class="animsition">
<%
    LoginUser loginUser= (LoginUser) request.getSession(false).getAttribute("login");
if(loginUser==null){
    response.sendRedirect("login.jsp");
}
%>
<div class="page-wrapper">
    <!-- PAGE CONTAINER-->
    <div class="page-container">
        <!-- HEADER DESKTOP-->
        <jsp:include page="static/header.jsp"></jsp:include>
        <!-- MENU SIDEBAR-->
        <jsp:include page="static/menu.jsp"></jsp:include>
        <!-- END MENU SIDEBAR-->
        <!-- MAIN CONTENT-->
        <!-- END MAIN CONTENT-->
        <jsp:include page="static/footer.jsp"></jsp:include>
    </div>
    <!-- END PAGE CONTAINER-->
</div>
<!-- Jquery JS-->
<script src="js/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="js/slick.min.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/animsition.min.js"></script>
<script src="js/bootstrap-progressbar.min.js"></script>
<script src="js/jquery.waypoints.min.js"></script>
<script src="js/jquery.counterup.min.js"></script>
<script src="js/circle-progress.min.js"></script>
<script src="js/perfect-scrollbar.js"></script>
<script src="js/Chart.bundle.min.js"></script>
<script src="js/select2.min.js"></script>


<!--<script src="js/1/brands.js"></script>
<script src="js/1/brands.min.js"></script>
<script src="js/1/conflict-detection.js"></script>
<script src="js/1/conflict-detection.min.js"></script>
<script src="js/1/fontawesome.js"></script>
<script src="js/1/fontawesome.min.js"></script>
<script src="js/1/regular.js"></script>
<script src="js/1/regular.min.js"></script>
<script src="js/1/solid.js"></script>
<script src="js/1/solid.min.js"></script>
<script src="js/1/v4-shims.js"></script>
<script src="js/1/v4-shims.min.js"></script>
-->
<!-- Main JS-->
<script src="js/main.js"></script>
<script src="js1/main.js"></script>
<div id="editAmenitiesDialogId"></div>
</body>
</html>
<!-- end document-->
