<%@ page import="az.elvin.hotel.model.LoginUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/6/2019
  Time: 12:11 AM
  To change this template use File | Settings | File Templates.
--%>
<style>
    .au-btn--green {
        background-color: #eda663 !important;
    }
</style>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Main CSS-->
    <link href="css/theme.css" rel="stylesheet" media="all">

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


    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script>
        window.onload = function () {

            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                exportEnabled: true,
                theme: "light1", // "light1", "light2", "dark1", "dark2"
                title: {
                    text: "Revenue:" +
                        new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getUTCFullYear() + '/' + (new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getMonth() + 1) + '/' + new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getDate()
                        + "-" + new Date().getUTCFullYear() + "/" + (new Date().getUTCMonth() + 1) + "/" + new Date().getUTCDate()
                },
                 axisX: {
                     valueFormatString: "YYYY/MM/DD"
                 },
                axisY: {
                    // title: "Revenue in USD",
                    valueFormatString: "#0,##0,.",

                    // suffix: "mn",
                    // prefix: "$"
                },
                data: [{
                    type: "splineArea",
                    color: "rgba(54,158,173,.7)",
                    markerSize: 5,
                    xValueFormatString: "YYYY/MM/DD",
                    yValueFormatString: "$#,##0.##",
                    dataPoints: [

                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 7)).getDate()), y: 5647000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 6)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 6)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 6)).getDate()), y: 2798000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 5)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 5)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 5)).getDate()), y: 3386000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 4)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 4)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 4)).getDate()), y: 6704000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 3)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 3)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 3)).getDate()), y: 6026000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 2)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 2)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 2)).getDate()), y: 2394000},
                        {x: new Date(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 1)).getUTCFullYear(),(new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 1)).getMonth() + 1),new Date(new Date().getTime() - (1000 * 60 * 60 * 24 * 1)).getDate()), y: 1872000},
                        {x: new Date(new Date().getUTCFullYear(),(new Date().getUTCMonth() + 1),new Date().getUTCDate()), y: 2140000}

                    ]

                }]
            });
            chart.render();

        }
    </script>


</head>

<body id="page-top">


<%
    LoginUser loginUser = (LoginUser) request.getSession(false).getAttribute("login");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
    }
%>




<jsp:include page="/static2/header.jsp"></jsp:include>

<div id="wrapper">

    <jsp:include page="/static2/menu.jsp"></jsp:include>

    <div id="content-wrapper">

        <div class="container-fluid">

            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="cs?action=getDashboardList">Dashboard</a>
                </li>
                <li class="breadcrumb-item active">Overview</li>
            </ol>

            <!-- Icon Cards-->
            <div class="row">
                <div class="col-xl-3 col-sm-6 mb-3">
                    <div class="card text-white bg-success o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-shopping-cart"></i>
                            </div>
                            <div class="mr-5">${reservationTodayAcount} New Orders!</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="rs?action=getReservationList">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>
                <div class="col-xl-4 col-sm-6 mb-3">
                    <div class="card text-white bg-primary o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-money-bill"></i>
                            </div>
                            <div class="mr-5">${ToDayRevenueCount}AZN&nbsp;Today revenue</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="#">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 mb-3">
                    <div class="card text-white bg-warning o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-home"></i>
                            </div>
                            <div class="mr-5">${roomAcount} Rooms</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="#">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>

                <div class="col-xl-2 col-sm-6 mb-3">
                    <div class="card text-white bg-danger o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-fw fa-users"></i>
                            </div>
                            <div class="mr-5">${reservationGuestAcount} Guests</div>
                        </div>
                        <a class="card-footer text-white clearfix small z-1" href="#">
                            <span class="float-left">View Details</span>
                            <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Area Chart Example-->
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Weekly Revenue Report
                </div>
                <div class="card-body">
                    <!--   <canvas id="chartContainer" width="100%" height="30"></canvas>-->
                    <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

                </div>
            </div>

            <!-- Area Chart Example-->
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Area Chart Example
                </div>
                <div class="card-body">
                    <canvas id="myAreaChart1" width="100%" height="30"></canvas>
                </div>
            </div>


            <!-- DataTables Example -->
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Data Table Example
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Position</th>
                                <th>Office</th>
                                <th>Age</th>
                                <th>Start date</th>
                                <th>Salary</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Tiger Nixon</td>
                                <td>System Architect</td>
                                <td>Edinburgh</td>
                                <td>61</td>
                                <td>2011/04/25</td>
                                <td>$320,800</td>
                            </tr>
                            <tr>
                                <td>Garrett Winters</td>
                                <td>Accountant</td>
                                <td>Tokyo</td>
                                <td>63</td>
                                <td>2011/07/25</td>
                                <td>$170,750</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
            </div>

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
                <a class="btn btn-primary" href="login.jsp">Logout</a>
            </div>
        </div>
    </div>
</div>


<!-- Jquery JS-->

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery-ui.js"></script>
<!-- Bootstrap core JavaScript-->
<script src="newLayout/vendor/jquery/jquery.js"></script>
<script src="newLayout/vendor/jquery/jquery.min.js"></script>
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


</body>

</html>
