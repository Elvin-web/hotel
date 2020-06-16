<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="az.elvin.hotel.model.Guest" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 11/10/2019
  Time: 2:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <script src="../js/all.js"></script>
    <script src="../js/all.min.js"></script>
    <link href="../css/all.css" rel="stylesheet" media="all">
    <link href="../css/all.min.css" rel="stylesheet" media="all">
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Hotel</title>

    <!-- Fontfaces CSS-->

    <link href="../css/font-face.css" rel="stylesheet" media="all">
    <link href="../css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="../css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="../css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="../css/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="../css/animsition.min.css" rel="stylesheet" media="all">
    <link href="../css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="../css/animate.css" rel="stylesheet" media="all">
    <link href="../css/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="../css/slick.css" rel="stylesheet" media="all">
    <link href="../css/select2.min.css" rel="stylesheet" media="all">
    <link href="../css/perfect-scrollbar.css" rel="stylesheet" media="all">
    <link href="../css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <!-- Main CSS-->
    <link href="../css/theme.css" rel="stylesheet" media="all">

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
          <!--     <h3 class="title-5 m-b-35"> guest data table</h3>
              <div class="table-data__tool">
                  <div class="table-data__tool-right">
                      <button class="au-btn au-btn-icon au-btn--blue au-btn--small" id="advSearchBtnId">Search</button>
                      <a href="cis?action=addCity">
                          <button class="au-btn au-btn-icon au-btn--green au-btn--small">add</button>
                      </a>
                  </div>
              </div>
              <div class="table-responsive table-responsive-data2">-->
                <table class="display" id="guestTableId">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>name</th>
                        <th>country</th>
                        <th>email</th>
                        <th>mobile</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${guestList}" var="gl">
                      <!--  <tr class="tr-shadow">-->

                        <tr>
                            <td>${gl.r}</td>
                            <td>${gl.guestName}</td>
                            <td>${gl.city.country.name}</td>
                            <td><span class="block-email">${gl.email}</span></td>
                            <td class="desc">${gl.phone}</td>
                          <!--  <td>
                                <div class="table-data-feature">
                                    <button class="item" data-toggle="tooltip" data-placement="top" title="View">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                    <a href="gs?action=editGuest&id=${gl.id}">
                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                                            <i class="fas fa-pencil-alt"></i>
                                        </button>
                                    </a>
                                    <a href="javascript: deleteGuest('${gl.id}','${gl.guestName}');">
                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </a>
                                </div>
                            </td>-->
                        </tr>
                     <!--   <tr class="spacer"></tr>-->

                    </c:forEach>

                    </tbody>
                </table>
           </div>
      <!--   </div>-->
        <!-- END MAIN CONTENT-->
        <jsp:include page="/static/footer.jsp"></jsp:include>
    </div>

    <!-- END PAGE CONTAINER-->
</div>

<!-- Jquery JS-->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js1/jquery/jquery.dataTables.min.js"></script>
<link href="../css/jquery.dataTables.min.css" rel="stylesheet" media="all">
<!-- Bootstrap JS-->
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="../js/slick.min.js"></script>
<script src="../js/wow.min.js"></script>
<script src="../js/animsition.min.js"></script>
<script src="../js/bootstrap-progressbar.min.js"></script>
<script src="../js/jquery.waypoints.min.js"></script>
<script src="../js/jquery.counterup.min.js"></script>
<script src="../js/circle-progress.min.js"></script>
<script src="../js/perfect-scrollbar.js"></script>
<script src="../js/Chart.bundle.min.js"></script>
<script src="../js/select2.min.js"></script>

<!-- Main JS-->
<script src="../js/main.js"></script>
<script src="../js1/city.js"></script>
<script type="text/javascript">

    $(function () {
        $('#guestTableId').DataTable();
    });
</script>
</body>

</html>
<!-- end document-->