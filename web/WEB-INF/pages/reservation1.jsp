<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/21/2019
  Time: 2:29 AM
  To change this template use File | Settings | File Templates.
--%>

<style>
    .nav-tabs {
        position: relative;
        background-color: #3c8dbc;
        border-top: 1px solid #3c8dbc;
        border-right: 1px solid #3c8dbc;
        border-left: 1px solid #3c8dbc;
        border-bottom: 4px solid #3c8dbc;
        border-radius: 4px;
    }

    .custom-tab {
        border: 1px solid #3c8dbc;
    }

    .nav-link {
        color: #f1f4f7;
    !important;
        text-decoration: none;
        background-color: transparent;
    }

</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="js/all.js"></script>
    <script src="js/all.min.js"></script>
    <link href="css/all.css" rel="stylesheet" media="all">
    <link href="css/all.min.css" rel="stylesheet" media="all">
    <link href="css/jquery-ui.css" rel="stylesheet" media="all">


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hotel</title>

    <!-- Custom fonts for this template-->
    <link href="newLayout/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="newLayout/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="newLayout/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="newLayout/vendor/fontawesome-free/css/sb-admin.css" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/sb-admin.min.css" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_cards.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_footer.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_global.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_login.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_mixins.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_navbar.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_utilities.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_variables.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/sb-admin.scss" rel="stylesheet">


    <link href="newLayout/vendor/fontawesome-free/css/sb-admin.css" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/sb-admin.min.css" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_cards.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_footer.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_global.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_login.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_mixins.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_navbar.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_utilities.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/_variables.scss" rel="stylesheet">
    <link href="newLayout/vendor/fontawesome-free/css/sb-admin.scss" rel="stylesheet">
</head>

<body id="page-top" style="background-color: rgba(0,0,0,.03);">


<jsp:include page="/static2/header.jsp"></jsp:include>

<div id="wrapper">

    <jsp:include page="/static2/menu.jsp"></jsp:include>

    <div id="content-wrapper">

        <div class="container-fluid">


            <!-- DataTables Example -->
            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card" style="border: none">
                                    <div class="card-header">
                                        <h4>Reservation</h4>
                                    </div>
                                    <div class="row form-group pull-right">
                                        <form><label for="select1U"
                                                     class=" form-control-label"><strong>Booking Status</strong></label>
                                            <select name="select1U" style="width: 200px;" id="select1U"
                                                    class="form-control">
                                                <c:forEach items="${bookingStatusList}" var="bl">
                                                    <option value="${bl.id}">${bl.bookingStatus}</option>
                                                </c:forEach>
                                            </select></form>&nbsp;
                                        <label for="select2U"
                                               class=" form-control-label"><strong>Payment Status</strong></label>
                                        <select name="select2U" style="width: 200px;" id="select2U"
                                                class="form-control">
                                            <c:forEach items="${paymentStatusList}" var="cl">
                                                <option value="${cl.id}">${cl.paymentStatus}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="card-body">
                                        <div class="custom-tab">
                                            <nav>
                                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                                    <a href="rs?action=moreReservation"> <a
                                                            class="nav-item nav-link active" id="custom-nav-home-tab"
                                                            data-toggle="tab" href="#custom-nav-home" role="tab"
                                                            aria-controls="custom-nav-home"
                                                            aria-selected="true"><b>Details</b></a></a>
                                                    <a class="nav-item nav-link" id="custom-nav-profile-tab"
                                                       data-toggle="tab" href="#custom-nav-profile" role="tab"
                                                       aria-controls="custom-nav-profile"
                                                       aria-selected="false"><b>Payments</b></a>
                                                    <a class="nav-item nav-link" id="custom-nav-profile-tab1"
                                                       data-toggle="tab" href="#custom-nav-profile1" role="tab"
                                                       aria-controls="custom-nav-profile"
                                                       aria-selected="false"><b>Service</b></a>
                                                    <a href="rs?action=getReservationRoom"><a class="nav-item nav-link"
                                                                                              id="custom-nav-contact-tab"
                                                                                              data-toggle="tab"
                                                                                              href="#custom-nav-contact"
                                                                                              role="tab"
                                                                                              aria-controls="custom-nav-contact"
                                                                                              aria-selected="false"><b>Room</b></a></a>
                                                </div>
                                            </nav>
                                            <div class="tab-content pl-3 pt-2" id="nav-tabContent">
                                                <div class="tab-pane fade show active" id="custom-nav-home"
                                                     role="tabpanel"
                                                     aria-labelledby="custom-nav-home-tab">
                                                    <!-- MAIN CONTENT-->

                                                    <div class="card-body card-block">
                                                        <form action="rs?action=moreReservation" method="post"
                                                              enctype="multipart/form-data"
                                                              class="form-horizontal">
                                                            <table style="width:100%; border:0">
                                                                <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <table style="width:100%; border-bottom:1px solid #CCCCCC; padding-bottom:20px;">
                                                                            <tbody>
                                                                            <tr>
                                                                                <td align="left">
                                                                                    <b>${reservation.hotel.hotelName}&nbsp;Hotel</b>
                                                                                </td>
                                                                                <td align="right">
                                                                                    Booking date : ${reservation.date}
                                                                                </td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <table style="width:100%; border-bottom:1px solid #CCCCCC; padding-bottom:30px;">
                                                                            <tbody>
                                                                            <tr>
                                                                                <td align="left" width="30%">
                                                                                    <b>Hotel Details</b><br>
                                                                                    <address>${reservation.hotel.hotelName}<br>
                                                                                        ${reservation.hotel.hotelAddress}<br>
                                                                                        ${reservation.hotel.hotelPhone}<br>
                                                                                        ${reservation.hotel.hotelEmail}
                                                                                    </address>
                                                                                </td>
                                                                                <td width="5%"></td>
                                                                                <td align="center" width="30%">
                                                                                    <b>Guest Details</b><br>
                                                                                    <address>${reservation.guest.guestName}&nbsp;${reservation.guest.guestSurname}<br>
                                                                                        ${reservation.guest.city.country.name}, ${reservation.guest.city.name}<br>
                                                                                        <b>Phone
                                                                                            :</b> ${reservation.guest.phone}<br>
                                                                                        <b>Email
                                                                                            :</b> ${reservation.guest.email}
                                                                                    </address>
                                                                                </td>
                                                                                <td width="5%"></td>
                                                                                <td align="right" width="30%"
                                                                                    colspan="1">
                                                                                    <address>
                                                                                        <b>Room
                                                                                            :</b> ${reservation.roomType.roomType}
                                                                                        <br>
                                                                                        <b>Booking number
                                                                                            :</b> ${reservation.id}<br>
                                                                                        <b>Check in
                                                                                            :</b> ${reservation.checkIn}<br>
                                                                                        <b>Check out
                                                                                            :</b> ${reservation.checkOut}<br>
                                                                                        <b>Payment status
                                                                                            :</b> ${reservation.paymentStatus.paymentStatus}<br>
                                                                                        <b>Booking status
                                                                                            :</b> ${reservation.bookingStatus.bookingStatus}<br>
                                                                                        <b>Adults
                                                                                            :</b> ${reservation.adults}<br>
                                                                                        <b>Kids
                                                                                            :</b> ${reservation.children}<br>
                                                                                        <b>Night
                                                                                            :</b> ${reservation.night}
                                                                                    </address>
                                                                                </td>
                                                                            </tr>
                                                                            <div class="table-responsive">
                                                                                <table class="table table-borderless"
                                                                                       id="dataTable2"
                                                                                       style="width: 100%;"
                                                                                       cellspacing="0">
                                                                                    <thead>
                                                                                    <tr>
                                                                                        <th>#</th>
                                                                                        <th>Night</th>
                                                                                        <th>Price</th>
                                                                                        <th></th>
                                                                                    </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                    <tr role="row" class="odd">
                                                                                        <td>${reservation.id}</td>
                                                                                        <td> ${reservation.night}</td>
                                                                                        <td>${payment.allNightCost}</td>
                                                                                    </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                            <p class="lead">Taxes</p>
                                                                            <table class="table" border="0">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td colspan="2">
                                                                                        <table width="100%">
                                                                                            <tbody>
                                                                                            <!-- bura deyiwmeldi-->
                                                                                            <c:forEach
                                                                                                    items="${taxList}"
                                                                                                    var="tl">
                                                                                                <tr>
                                                                                                    <td>${tl.id}</td>
                                                                                                    <td>${tl.name}</td>
                                                                                                    <td>${tl.rate}%</td>
                                                                                                </tr>
                                                                                            </c:forEach>
                                                                                            <tr>
                                                                                                <td></td>
                                                                                                <td colspan="1" align>
                                                                                                    <b>Total Paid For
                                                                                                        Tax</b>
                                                                                                    <span class="pull-right">=</span>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <b>
                                                                                                        ${payment.taxCost}
                                                                                                    </b>
                                                                                                </td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>


                                                                            <p class="lead">Paid Services</p>
                                                                            <table class="table" border="0">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td colspan="2">
                                                                                        <table width="100%">
                                                                                            <tbody>
                                                                                            <!-- bura deyiwmeldi-->
                                                                                            <c:forEach
                                                                                                    items="${service_reservationList}"
                                                                                                    var="srl">
                                                                                                <tr>
                                                                                                    <td>${srl.id}</td>
                                                                                                    <td>${srl.services.name}</td>
                                                                                                    <td>${srl.services.amount}</td>
                                                                                                    <td>${srl.services.priceType.priceType}</td>
                                                                                                </tr>
                                                                                            </c:forEach>
                                                                                            <tr>
                                                                                                <td></td>
                                                                                                <td colspan="1" align>
                                                                                                    <b>Total Paid For
                                                                                                        Services</b>
                                                                                                    <span class="pull-right">=</span>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <b>
                                                                                                        ${payment.servicesCost}
                                                                                                    </b>
                                                                                                </td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>


                                                                            <table class="table" border="0">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td colspan="2">
                                                                                        <table width="100%">
                                                                                            <tbody>

                                                                                            <tr>
                                                                                                <td></td>
                                                                                                <td colspan="1" align>
                                                                                                    <b>Total
                                                                                                        amount: </b>

                                                                                                </td>
                                                                                                <td>
                                                                                                    <b>
                                                                                                        ${payment.sum}
                                                                                                    </b>
                                                                                                </td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>

                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <table style="width:100%; border-bottom:1px solid #CCCCCC; padding-bottom:20px;">
                                                                            <tbody>
                                                                            <tr>
                                                                                <a href="pas?action=getPaymentList">
                                                                                    <button type="button"
                                                                                            class="btn btn-primary btn-sm"
                                                                                            id="paymentSubmitIdU"
                                                                                            onclick="window.print()">
                                                                                        <i class="fa  fa-print"></i>
                                                                                        Print
                                                                                    </button>
                                                                                </a>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </form>
                                                    </div>
                                                    <!-- END MAIN CONTENT-->
                                                </div>


                                                <div class="tab-pane fade" id="custom-nav-profile" role="tabpanel"
                                                     aria-labelledby="custom-nav-profile-tab">
                                                    <!-- MAIN CONTENT-->
                                                    <!-- DataTables Example -->
                                                    <div class="btn-group fa-pull-right">
                                                        <a href="pas?action=addPayment&id=${reservation.id}">
                                                            <button class="btn btn-success"><strong>+</strong> Add
                                                            </button>
                                                        </a>
                                                    </div>
                                                    <div class="card-body">
                                                        <div style="margin-bottom: 20px;">Total
                                                            Paid:${payment.paymentAmount} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
                                                                    style="color: red">Pending: ${payment.pending}</span>
                                                        </div>
                                                        <div class="table-responsive">
                                                            <table class="table table-borderless" id="dataTable"
                                                                   style="width: 100%;" cellspacing="0">
                                                                <thead>
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>Added date</th>
                                                                    <th>Invoice number</th>
                                                                    <th>Amount</th>
                                                                    <th></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <tr role="row" class="odd">
                                                                    <td>${payment.id}</td>
                                                                    <td>${payment.addedDate}</td>
                                                                    <td>${payment.id}</td>
                                                                    <td>${payment.paymentAmount}</td>
                                                                    <td>
                                                                        <div class="btn-group" style="float: right">
                                                                            <a class="btn btn-secondary"
                                                                               href="pas?action=viewPayment&id=${payment.id}">
                                                                                <i class="fas fa-eye"></i>View
                                                                            </a>
                                                                            <a class="btn btn-light"
                                                                               href="pas?action=invoicePayment&id=${payment.id}">
                                                                                <i class="fas fa-bars"></i>Invoice
                                                                            </a>
                                                                            <a class="btn btn-primary"
                                                                               href="pas?action=editPayment&id=${payment.id}">
                                                                                <i class="fas fa-pencil-alt"></i>Edit
                                                                            </a>

                                                                        </div>
                                                                    </td>
                                                                </tr>


                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- END MAIN CONTENT-->
                                                </div>


                                                <div class="tab-pane fade" id="custom-nav-profile1" role="tabpanel"
                                                     aria-labelledby="custom-nav-profile-tab1">
                                                    <!-- MAIN CONTENT-->
                                                    <!-- DataTables Example -->
                                                    <div class="table-data__tool">
                                                        <form action="rs?action=updateReservationRoom" method="post"
                                                              enctype="multipart/form-data"
                                                              class="form-horizontal">
                                                            <div class="row form-group">
                                                                <div class="col col-md-3">
                                                                    <label for="selectU" class=" form-control-label"><b>service</b></label>
                                                                </div>
                                                                <div class="col-12 col-md-9">
                                                                    <select name="select3U" id="select3U"
                                                                            style="width: 400px;" class="form-control">
                                                                        <option value="0">Please select service</option>
                                                                        <c:forEach items="${servicesList}" var="dl">
                                                                            <option value="${dl.id}">${dl.name}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <a href="javascript: addServiceReservation('${reservation.id}');">
                                                                    <button type="button"
                                                                            class="btn btn-success"
                                                                    >Save
                                                                    </button>
                                                                </a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="card-body">
                                                        <div class="table-responsive">
                                                            <table class="table table-borderless" id="dataTable3"
                                                                   style="width: 100%;" cellspacing="0">
                                                                <thead>
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>Service name</th>
                                                                    <th>Price</th>
                                                                    <th>Service time</th>

                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <c:forEach items="${service_reservationList}" var="srl">
                                                                    <tr role="row" class="odd">
                                                                        <td>${srl.id}</td>
                                                                        <td>${srl.services.name}</td>
                                                                        <td>${srl.services.amount}</td>
                                                                        <td>${srl.date}</td>
                                                                    </tr>
                                                                </c:forEach>

                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- END MAIN CONTENT-->
                                                </div>


                                                <div class="tab-pane fade" id="custom-nav-contact" role="tabpanel"
                                                     aria-labelledby="custom-nav-contact-tab">
                                                    <!-- MAIN CONTENT-->
                                                    <!-- DataTables Example -->
                                                    <div class="table-data__tool">
                                                        <form action="rs?action=updateReservationRoom" method="post"
                                                              enctype="multipart/form-data"
                                                              class="form-horizontal">
                                                            <div class="row form-group">
                                                                <div class="col col-md-3">
                                                                    <label for="selectU" class=" form-control-label"><b>Room
                                                                        number</b></label>
                                                                </div>
                                                                <div class="col-12 col-md-9">
                                                                    <select name="selectU" id="selectU"
                                                                            style="width: 400px;" class="form-control">
                                                                        <option value="0">Please select Room</option>
                                                                        <c:forEach items="${roomList}" var="dl">
                                                                            <option value="${dl.id}">${dl.roomNumber}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <!-- <a href="javascript: updateReservationRoom('${reservationRoomId.id}');">
                                                                    <button type="button"
                                                                            class="btn btn-success"
                                                                           >Save
                                                                    </button>
                                                                </a>-->
                                                                <a href="javascript: addReservationRoom('${reservation.id}');">
                                                                    <button type="button"
                                                                            class="btn btn-success"
                                                                    >Save
                                                                    </button>
                                                                </a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="card-body">
                                                        <div style="margin-bottom: 20px;">Total
                                                            paid: ${payment.allNightCost}</div>
                                                        <div class="table-responsive">
                                                            <table class="table table-borderless" id="dataTable1"
                                                                   style="width: 100%;" cellspacing="0">
                                                                <thead>
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>Night</th>
                                                                    <th>Room</th>
                                                                    <th>Floor</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <c:forEach items="${room_reservation}" var="rl">
                                                                    <tr role="row" class="odd">
                                                                        <td>${rl.id}</td>
                                                                        <td>${rl.reservation.night}</td>
                                                                        <td>${rl.room.roomNumber}</td>
                                                                        <td>${rl.room.floor.name}</td>
                                                                    </tr>
                                                                </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- END MAIN CONTENT-->

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /# column -->
                    </div>
                </div>
            </div>

        </div>
        <!-- END MAIN CONTENT-->
    </div>
    <!-- /.container-fluid -->

</div>

<!-- Sticky Footer -->
<footer class="footer " style="background-color: #418bd6 ">
    <jsp:include page="/static2/footer.jsp"></jsp:include>
</footer>
<!-- /.content-wrapper -->
<!-- /#wrapper -->
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="newLayout/vendor/html/login.html">Logout</a>
            </div>
        </div>
    </div>
</div>
<!-- Jquery JS-->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery-ui.js"></script>
<!-- Bootstrap core JavaScript-->
<script src="newLayout/vendor/jquery/jquery.min.js"></script>
<script src="newLayout/vendor/jquery/jquery.js"></script>
<script src="newLayout/vendor/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="newLayout/vendor/jquery/jquery.easing.min.js"></script>
<script src="newLayout/vendor/jquery/jquery.easing.compatibility.js"></script>
<script src="newLayout/vendor/jquery/jquery.easing.js"></script>
<!-- Page level plugin JavaScript-->
<script src="newLayout/vendor/datatables/jquery.dataTables.js"></script>
<script src="newLayout/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="newLayout/vendor/datatables/dataTables.bootstrap4.js"></script>
<script src="newLayout/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="newLayout/vendor/js/sb-admin.js"></script>
<!-- Demo scripts for this page-->
<script src="newLayout/vendor/js/demo/datatables-demo.js"></script>
<script src="js1/reservation.js"></script>
<script type="text/javascript">
    $(function () {
        $('#select1U').val('${reservation.bookingStatus.id}');
        $('#select1U').change(function () {
            updateReservation1('${reservation.id}');
        });
    })
</script>
<script type="text/javascript">
    $(function () {
        $('#select2U').val('${reservation.paymentStatus.id}');
        $('#select2U').change(function () {
            updateReservation2('${reservation.id}');
        });
    })
</script>
</body>
</html>