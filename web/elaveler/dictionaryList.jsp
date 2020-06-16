<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/5/2019
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<script type="text/javascript">

$(function () {
$('#roomTableId').DataTable();
});
</script>
<table class="display" id="roomTableId">
<thead>
<tr>
<th>#</th>
<th>Room number</th>
<th>Floor number</th>
</tr>
</thead>
<tbody>
<c:forEach items="${dictionaryList}" var="dl">
    <tr>
    <td>${dl.r}</td>
    <td>${dl.room.roomNumber}</td>
    <td>${dl.room.floor.name}${dl.room.floor.floorNumber}</td>
    </tr>
</c:forEach>
</tbody>
</table>
-->

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
        <div class="main-content">
            <h3 class="title-5 m-b-35"> room data table</h3>
            <div class="table-data__tool">
                <div class="table-data__tool-left">
                    <div class="rs-select2--light rs-select2--md">
                        <select class="js-select2" name="property">
                            <option selected="selected" id="advExpensesCategoryCmbId">Select Floor</option>
                            <c:forEach items="${floorList}" var="fl">
                                <option value="${fl.id}">${fl.name}</option>
                            </c:forEach>
                        </select>
                        <div class="dropDownSelect2"></div>
                    </div>
                    <div class="rs-select2--light rs-select2--md">
                        <select class="js-select2" name="property">
                            <option selected="selected" id="advExpensesCategoryCmbId">Select Amenities</option>
                            <c:forEach items="${dictionaryList}" var="dl">
                                <option value="${dl.id}">${dl.amenities.name}</option>
                            </c:forEach>
                        </select>
                        <div class="dropDownSelect2"></div>
                    </div>
                    <div>
                        <input class="au-input au-input--xl" type="text" id="advRoomNumberId"
                               placeholder="Room number"/>
                    </div>
                </div>
                <div class="table-data__tool-right">
                    <!--    <button class="au-btn au-btn-icon au-btn--green au-btn--small">
                     <i class="zmdi zmdi-plus"></i>add</button>-->
                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small" id="advSearchBtnId">Search</button>
                    <a href="ds?action=addDictionary">
                        <button class="au-btn au-btn-icon au-btn--green au-btn--small">add</button>
                    </a>

                    <div class="rs-select2--dark rs-select2--sm rs-select2--dark2">
                        <select class="js-select2" name="type">
                            <option selected="selected">Export</option>
                            <option value="">Option 1</option>
                            <option value="">Option 2</option>
                        </select>
                        <div class="dropDownSelect2"></div>
                    </div>
                </div>
            </div>
            <div class="table-responsive table-responsive-data2">
                <table class="table table-data2">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Room number</th>
                        <th>Floor number</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dictionaryList}" var="dl">
                        <tr class="tr-shadow">
                            <td>${dl.r}</td>
                            <td>${dl.room.roomNumber}</td>
                            <td>${dl.room.floor.name}${dl.room.floor.floorNumber}</td>
                            <td>
                                <div class="table-data-feature">
                                    <a href="ds?action=viewDictionary&id=${dl.id}">
                                        <button class="item" data-toggle="tooltip" data-placement="top" title="View">
                                            <i class="fas  fas fa-eye"></i></button></a>
                                    <a href="hos?action=housekeepingList&id=${dl.id}">
                                    <button class="item" data-toggle="tooltip" data-placement="top"
                                            title="Housekeeping"><i class="fas  fa-home"></i>
                                    </button></a>
                                    <a href="ds?action=editDictionary&id=${dl.id}">
                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                                            <i class="fas fa-pencil-alt"></i>
                                        </button>
                                    </a>
                                    <a href="javascript: deleteDictionary('${dl.id}','${dl.room.roomNumber}');">
                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </a>

                                </div>
                            </td>
                        </tr>
                        <tr class="spacer"></tr>
                    </c:forEach>
                    </tbody>
                </table>

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
<script src="js1/dictionary.js"></script>

</body>

</html>
<!-- end document-->