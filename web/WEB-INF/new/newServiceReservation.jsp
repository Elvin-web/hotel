<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/8/2019
  Time: 11:54 PM
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

<body id="page-top">


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
                                <div class="card">
                                    <div class="card-header">
                                        <strong>Services Form</strong> new
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="ses?action=addServices1" method="post"
                                              enctype="multipart/form-data" class="form-horizontal">
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input"
                                                           class=" form-control-label"><strong>Title</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input"
                                                           name="text-input" placeholder="Title" class="form-control">
                                                    <small class="form-text text-muted">Please enter title</small>
                                                </div>
                                            </div>

                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="select"
                                                           class=" form-control-label"><strong>Room type</strong></label>
                                                    <select name="multiple-select"  style="width: 400px;" id="multiple-select" multiple="" class="form-control">
                                                        <c:forEach items="${roomTypeList}" var="rtl">
                                                            <option value="${rtl.id}">${rtl.roomType}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="select"
                                                           class=" form-control-label"><strong>Price type</strong></label>
                                                    <select name="select" style="width: 400px;" id="select"
                                                            class="form-control">
                                                        <option value="0">Please select Price type</option>
                                                        <c:forEach items="${priceTypeList}" var="ptl">
                                                            <option value="${ptl.id}">${ptl.priceType}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input1"
                                                           class=" form-control-label"><strong>Price</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input1"
                                                           name="text-input1" placeholder="Price" class="form-control">
                                                    <small class="form-text text-muted">Please enter price</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="textarea-input"
                                                           class=" form-control-label"><strong>Description</strong></label>
                                                    <textarea name="textarea-input" style="width: 400px;" id="textarea-input" rows="9"
                                                              placeholder="Content..." class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label class=" form-control-label"><strong>Active</strong></label>
                                                    <div class="form-check">
                                                        <div class="checkbox">
                                                            <label for="checkbox1" class="form-check-label ">
                                                                <input type="checkbox" id="checkbox1" name="checkbox1" value="1" class="form-check-input">
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="button" class="btn btn-primary btn-sm"
                                                    id="servicesSubmitId"> Save
                                            </button>
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

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
            <jsp:include page="/static2/footer.jsp"></jsp:include>
        </footer>

    </div>
    <!-- /.content-wrapper -->

</div>
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




<!-- Bootstrap core JavaScript-->
<script src="newLayout/vendor/jquery/jquery.js"></script>
<script src="newLayout/vendor/js/bootstrap.bundle.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="newLayout/vendor/js/sb-admin.min.js"></script>
<script src="newLayout/vendor/js/sb-admin.js"></script>
<!-- Jquery JS-->
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/jquery-ui.js"></script>
<script src="js1/services.js"></script>


</body>

</html>










