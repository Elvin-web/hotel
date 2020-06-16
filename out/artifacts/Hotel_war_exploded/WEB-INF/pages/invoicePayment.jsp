<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/26/2019
  Time: 2:23 AM
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
            <div class="main-content" id="main">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card" style="border: none">
                                    <div class="card-header">
                                        <strong>Invoice</strong> <br>
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="pas?action=updatePayment" method="post"
                                              enctype="multipart/form-data"
                                              class="form-horizontal">
                                            <table style="width:100%; border:0">
                                                <tbody>
                                                <tr>
                                                    <td>
                                                        <table style="width:100%; border-bottom:1px solid #CCCCCC; padding-bottom:20px;">
                                                            <tbody>
                                                            <tr>
                                                                <td align="right">
                                                                    <b>Invoice number : ${payment.id}</b><br>
                                                                    <b>Added date:</b>${payment.addedDate}<br>
                                                                    <b>Payment method:</b>${payment.payType.payType}
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
                                                                <td align="left" width="45%">
                                                                    <b>Payment to</b><br>
                                                                    <address>${payment.reservation.hotel.hotelName}<br>
                                                                        ${payment.reservation.hotel.hotelAddress}<br>
                                                                        ${payment.reservation.hotel.hotelPhone}<br>
                                                                        ${payment.reservation.hotel.hotelEmail}
                                                                    </address>
                                                                </td>
                                                                <td width="10%"></td>
                                                                <td align="right" width="45%" colspan="1">
                                                                    <b>Bill to</b><br>
                                                                    <address>${payment.reservation.guest.guestName} ${payment.reservation.guest.guestSurname}<br>
                                                                        ${payment.reservation.guest.phone}<br>
                                                                        ${payment.reservation.guest.email}
                                                                    </address>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left" style="padding-top: 10px;">
                                                                    Invoice Entries
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table style="width:200%; border:1px solid #CCCCCC;">
                                                                        <tbody>
                                                                        <tr>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:10%; align:left">
                                                                                <b>#</b>
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:75%; align:left">
                                                                                <b>Detail</b>
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:15%; align:left">
                                                                                <b>Amount</b>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:10%; align:left">
                                                                                <b>${payment.id}</b>
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:75%; align:left">
                                                                                Payment
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:15%; align:left">
                                                                                ${payment.paymentAmount}
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:10%; align:left">
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:75%; align:left">
                                                                                Total Amount
                                                                            </td>
                                                                            <td style="border-bottom:1px solid #CCCCCC;border-right: 1px solid #CCCCCC; width:15%; align:left">
                                                                                <b>${payment.paymentAmount}</b>
                                                                            </td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>

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
                                                                    <button type="button" class="btn btn-primary btn-sm"
                                                                            id="paymentSubmitIdU" onclick="window.print()">
                                                                        <i class="fa  fa-print"></i> Print
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
                                    <div class="card-footer" align="right">
                                        <a href="gs?action=getGuestList1">
                                            <button type="button" class="btn btn-secondary">
                                                 Close
                                            </button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- END MAIN CONTENT-->
        </div>
        <!-- /.container-fluid -->


    </div>
    <!-- /.content-wrapper -->

</div>
<!-- /#wrapper -->
<!-- Sticky Footer -->
<footer class="footer " style="background-color: #418bd6 ">
    <jsp:include page="/static2/footer.jsp"></jsp:include>
</footer>
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
<script src="js1/payment.js"></script>
</body>

</html>
<!-- end document-->
