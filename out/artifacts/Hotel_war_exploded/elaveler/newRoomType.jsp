<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/8/2019
  Time: 11:53 PM
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
                                    <strong>Room type Form</strong> Elements
                                </div>
                                <div class="card-body card-block">
                                    <form action="rts?action=addRoomType" method="post" enctype="multipart/form-data" class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class=" form-control-label">Room type</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <p class="form-control-static">Form</p>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="select" class=" form-control-label">Room</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="select" id="select" class="form-control">
                                                    <option value="0">Please select Room</option>
                                                    <c:forEach items="${dictionaryList}" var="dl">
                                                        <option value="${dl.id}">${dl.room.roomNumber}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input" class=" form-control-label">Title</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input" name="text-input" placeholder="Title" class="form-control">
                                                <small class="form-text text-muted">Please enter title</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input1" class=" form-control-label">Slug</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input1" name="text-input" placeholder="Slug" class="form-control">
                                                <small class="form-text text-muted">Please enter slug</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input2" class=" form-control-label">Short code</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input2" name="text-input" placeholder="Short code" class="form-control">
                                                <small class="form-text text-muted">Please enter short code</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input3" class=" form-control-label">Base occupancy</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="number" id="text-input3" name="text-input" placeholder="Base occupancy" class="form-control">
                                                <small class="form-text text-muted">Please enter base occupancy</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input4" class=" form-control-label">Higher occupancy</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="number" id="text-input4" name="text-input" placeholder="Higher occupancy" class="form-control">
                                                <small class="form-text text-muted">Please enter higher occupancy</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input5" class=" form-control-label">Kids occupancy</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="number" id="text-input5" name="text-input" placeholder="Kids occupancy" class="form-control">
                                                <small class="form-text text-muted">Please enter kids occupancy</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input6" class=" form-control-label">Base price</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input6" name="text-input" placeholder="Base price" class="form-control">
                                                <small class="form-text text-muted">Please enter base price</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input7" class=" form-control-label">Additional price</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input7" name="text-input" placeholder="Additional price" class="form-control">
                                                <small class="form-text text-muted">Please enter additional price</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input8" class=" form-control-label">Extra bed price</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input8" name="text-input" placeholder="Extra bed price" class="form-control">
                                                <small class="form-text text-muted">Please enter extra bed price</small>
                                            </div>
                                        </div>

                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="textarea-input" class=" form-control-label">Description</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <textarea name="textarea-input" id="textarea-input" rows="9" placeholder="Content..." class="form-control"></textarea>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="file-input" class=" form-control-label">Image</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="file" id="file-input" name="file-input" class="form-control-file">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class=" form-control-label">Extra bed</label>
                                            </div>
                                            <div class="col col-md-9">
                                                <div class="form-check">
                                                    <div class="checkbox">
                                                        <label for="checkbox1" class="form-check-label ">
                                                            <input type="checkbox" id="checkbox1" name="checkbox1" value="1" class="form-check-input">
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <button type="button" class="btn btn-primary btn-sm" id="roomTypeSubmitId">
                                        <i class="fa fa-dot-circle-o"></i> Save
                                    </button>
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
<script src="js1/roomType.js"></script>

</body>

</html>
<!-- end document-->

