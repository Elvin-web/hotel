<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/29/2019
  Time: 8:13 PM
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
                                        <p class="lead" style="text-align: right">Royal Hotel</p>
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="ds?action=getDictionaryList" method="post"
                                              enctype="multipart/form-data" class="form-horizontal">
                                            <table class="table" style="width: 100%; border-block:none" >
                                                <tbody>
                                                <tr style="border: none">
                                                    <td colspan="2"  style="border: none;background-color: #CCCCCC">
                                                        <p class="lead" style="text-align: center"><b>Employee Information</b></p>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <div class="row">
                                                <div class="table-responsive col-md-6">
                                                    <table class="table" style="width: 100%; border-block:none" >
                                                        <tbody>

                                                        <tr>
                                                            <td style="border: none">
                                                                Full name  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.surname}&nbsp;${staff.name}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Address   :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${staff.address}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Phone   : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.phone}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Email   :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${staff.email}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Birth date  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.dob}
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none"> Gender  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <label for="inline-radio1U" class="form-check-label ">
                                                                    <input type="radio" id="inline-radio1U"
                                                                           <c:if test="${staff.gender eq 'male'}">checked </c:if>
                                                                           name="male"
                                                                           value="male" class="form-check-input">Male
                                                                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <label for="inline-radio2U" class="form-check-label ">
                                                                    <input type="radio" id="inline-radio2U"
                                                                           <c:if test="${staff.gender eq 'female'}">checked </c:if>
                                                                           name="female"
                                                                           value="female" class="form-check-input">Female
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="border: none">
                                                                Username  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${staff.username}
                                                            </td>
                                                            <td style="border: none">
                                                                Passowrd  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${staff.password}
                                                            </td>
                                                        </tr>

                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="table-responsive col-md-6">
                                                    <table class="table" style="width: 100%; border-block:none" >
                                                        <tbody>
                                                        <tr>
                                                            <td style="border: none"> <a class="item-gallery-sidebar wrap-pic-w" href="${filePath2}"
                                                                                         data-lightbox="gallery-footer" >
                                                                <img src="${filePath2}" alt="GALLERY" style="width: 60%;height: 25%;border-radius: 50%">
                                                            </a>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <table class="table" style="width: 100%; border-block:none" >
                                                <tbody>
                                                <tr>
                                                    <td colspan="2"  style="border: none;background-color: #CCCCCC">
                                                        <p class="lead" style="text-align: center" ><b>Job Information</b></p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="border: none">
                                                        Title :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${staff.position.positionValue}
                                                    </td>
                                                    <td style="border: none">
                                                        Employee ID :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${staff.ID}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="border: none">
                                                        Work location :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.hotel.hotelAddress}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="border: none">
                                                        Work phone :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.hotel.hotelPhone}
                                                    </td>
                                                    <td style="border: none">
                                                        Work Email :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.hotel.hotelEmail}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="border: none">
                                                        Start date :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.date}
                                                    </td>
                                                    <td style="border: none">
                                                        Salary : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.salary}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="border: none">
                                                        Job start time :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.jobStart}
                                                    </td>
                                                    <td style="border: none">
                                                        Job end time :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${staff.jobEnd}
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <button type="button"class="btn btn-primary btn-sm"
                                            onclick="window.print()">
                                            <i class="fa  fa-print"></i>
                                            Print
                                            </button>
                                            <a href="ss?action=getStaffList">
                                                <button type="button" class="btn btn-secondary">
                                                    Close
                                                </button>
                                            </a>
                                        </form>
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

<!-- Bootstrap core JavaScript-->
<script src="newLayout/vendor/jquery/jquery.js"></script>
<script src="newLayout/vendor/js/bootstrap.bundle.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="newLayout/vendor/js/sb-admin.min.js"></script>
<script src="newLayout/vendor/js/sb-admin.js"></script>
<!-- Jquery JS-->
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/jquery-ui.js"></script>
<script src="./js1/guest.js"></script>
</body>

</html>
<!-- end document-->
