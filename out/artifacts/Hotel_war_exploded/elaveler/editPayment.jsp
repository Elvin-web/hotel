<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/26/2019
  Time: 1:17 AM
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
                                    <strong>Payment Form</strong> edit
                                </div>
                                <div class="card-body card-block">
                                    <form action="pas?action=updatePayment" method="post" enctype="multipart/form-data" class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="select" class=" form-control-label">Reservation</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="select" id="select" class="form-control">
                                                    <option value="0">Please select Reservation</option>
                                                    <c:forEach items="${reservationList}" var="rl">
                                                        <option value="${rl.id}">${rl.id}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input" class=" form-control-label">Invoice number</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input" value="${payment.invoiceNumber}" name="text-input" placeholder="invoice number" class="form-control">
                                                <small class="form-text text-muted">Please enter invoice number</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input1" class=" form-control-label">Added date</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input1" value="${payment.addedDate}" name="text-input1" placeholder="added date" class="form-control">
                                                <small class="form-text text-muted">Please enter added date</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="select1" class=" form-control-label">Payment method</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="select1" id="select1" class="form-control">
                                                    <option value="0">Please select Payment method</option>
                                                    <c:forEach items="${payTypeList}" var="pl">
                                                        <option value="${pl.id}">${pl.payType}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input3" class=" form-control-label">Amount</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="number" id="text-input3" value="${payment.paymentAmount}" name="text-input" placeholder="amount" class="form-control">
                                                <small class="form-text text-muted">Please enter amount</small>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <a href="javascript: updatePayment('${payment.id}');">
                                    <button type="button" class="btn btn-primary btn-sm" id="paymentSubmitIdU">
                                        <i class="fa fa-dot-circle"></i> Update
                                    </button></a>
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
<script src="js1/payment.js"></script>
<script type="text/javascript">
    $(function () {
        $('#text-input1').datepicker({
            changeMonth: true,
            changeYear: true
        });
        $('#select').val('${payment.reservation.id}');
        $('#select1').val('${payment.payType.id}');
    });
</script>
</body>

</html>
<!-- end document-->

