<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/17/2019
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>
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

    <!-- Fontfaces CSS-->
    <link href="css/font-face.css" rel="stylesheet" media="all">
    <link href="css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="css/jquery-ui.css" rel="stylesheet" media="all">
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
    <link href="css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <!-- Main CSS-->
    <link href="css/theme.css" rel="stylesheet" media="all">
</head>
<body class="animsition">
<div class="page-wrapper">
    <!-- PAGE CONTAINER-->
    <div class="page-container">

        <!-- HEADER DESKTOP-->
        <jsp:include page="/static/header.jsp"></jsp:include>
        <!-- HEADER DESKTOP--> <!-- MENU SIDEBAR-->

        <jsp:include page="/static/menu.jsp"></jsp:include>
        <!-- END MENU SIDEBAR-->

        <!-- MAIN CONTENT-->
        <div class="main-content" id="main" >
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <strong>Guest Form</strong> edit
                                </div>
                                <div class="card-body card-block">
                                    <form action="gs?action=updateGuest" method="post" enctype="multipart/form-data" class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-inputU" class=" form-control-label">Name</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-inputU" value="${guest.guestName}" name="text-input" placeholder="Name" class="form-control">
                                                <small class="form-text text-muted">Please enter name</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input1U" class=" form-control-label">Surname</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input1U" value="${guest.guestSurname}" name="text-input" placeholder="Surname" class="form-control">
                                                <small class="form-text text-muted">Please enter surname</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class=" form-control-label">Gender</label>
                                            </div>
                                            <div class="col col-md-9">
                                                <div class="form-check-inline form-check">
                                                    <label for="inline-radio1U" class="form-check-label ">
                                                        <input type="radio" id="inline-radio1U" <c:if test="${guest.gender eq 'male'}">checked </c:if>  name="inline-radios" value="male" class="form-check-input">Male
                                                    </label>
                                                    <label for="inline-radio2" class="form-check-label ">
                                                        <input type="radio" id="inline-radio2" <c:if test="${guest.gender eq 'female'}">checked </c:if> name="inline-radios" value="female" class="form-check-input">Female
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input4U" class=" form-control-label">Birthday</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input4U" value="${guest.dob}" name="text-input" placeholder="Birthday" class="form-control">
                                                <small class="form-text text-muted">Please enter birthday</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input2U" class=" form-control-label">Phone</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input2U" value="${guest.phone}" name="text-input" placeholder="Phone" class="form-control">
                                                <small class="form-text text-muted">Please enter phone</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="email-inputU" class=" form-control-label">Email</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="email" id="email-inputU" value="${guest.email}" name="email-input" placeholder="Enter Email" class="form-control">
                                                <small class="help-block form-text">Please enter email</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="selectU" class=" form-control-label">Country</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="selectU" id="selectU" class="form-control">
                                                    <option value="0">Please select Country</option>
                                                    <c:forEach items="${cityList}" var="cl">
                                                        <option value="${cl.id}">${cl.country.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="select1U" class=" form-control-label">City</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="select1U" id="select1U" class="form-control">
                                                    <option value="0">Please select City</option>
                                                    <c:forEach items="${cityList}" var="cl">
                                                        <option value="${cl.id}">${cl.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="select2U" class=" form-control-label">ID</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="select2U" id="select2U" class="form-control">
                                                    <option value="0">Please select ID</option>
                                                    <c:forEach items="${passportList}" var="pl">
                                                        <option value="${pl.id}">${pl.passportType}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input3U" class=" form-control-label">ID</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input3U" value="${guest.passportNumber}" name="text-input" placeholder="ID" class="form-control">
                                                <small class="form-text text-muted">Please enter ID</small>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <a href="javascript: updateGuest('${guest.id}');">
                                    <button type="button" class="btn btn-primary btn-sm">
                                        <i class="fa fa-dot-circle"></i> Update
                                    </button>
                                    </a>
                                    <button type="reset" class="btn btn-danger btn-sm">
                                        <i class="fa fa-ban"></i> Reset
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- END MAIN CONTENT-->
        <jsp:include page="/static/footer.jsp"></jsp:include>
    </div>

    <!-- END PAGE CONTAINER-->
</div>

<!-- Jquery JS-->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery-ui.js"></script>
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

<!-- Main JS-->
<script src="js/main.js"></script>
<script src="js1/guest.js"></script>
<script type="text/javascript">
    $(function () {
        $('#text-input4U').datepicker({
            changeMonth: true,
            changeYear: true
        });
                $('#selectU').val('${guest.city.country.id}');
                $('#select1U').val('${guest.city.id}');
                $('#select2U').val('${guest.passport.id}');
    });
</script>
</body>

</html>
<!-- end document-->
