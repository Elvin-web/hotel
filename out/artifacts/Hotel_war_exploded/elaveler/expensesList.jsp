<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/5/2019
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<script>
    $(function () {
       $('#advBeginDateId').datepicker({
           changeMonth:true,
           changeYear:true
       });
        $('#advEndDateId').datepicker({
            changeMonth:true,
            changeYear:true
        });


    });
</script>
<style>
    .rs-select2--light
    {
        width: 202px !important;
    }
</style>
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
            <h3 class="title-5 m-b-35"> expenses data table</h3>
            <div class="table-data__tool">
                <!-- <div class="table-data__tool-left">
                    <div class="rs-select2--light rs-select2--md">
                        <select class="js-select2" name="property">
                            <option selected="selected" id="advExpensesCategoryCmbId">Select Expenses category</option>
                            <c:forEach items="${expensesCategoryList}" var="ecl">
                                <option value="${ecl.id}">${ecl.expensesType}</option>
                            </c:forEach>
                        </select>
                        <div class="dropDownSelect2"></div>
                    </div>
                    <div>
                        <input class="au-input au-input--xl" type="text" id="advNameId" placeholder="title">
                    </div>
                    <div>
                        <input class="au-input au-input--xl" type="text" id="advMinAmountId" placeholder="Min Amount"/>&nbsp;
                        <input class="au-input au-input--xl" type="text" id="advMaxAmountId" placeholder="Max Amount"/><br>
                    </div>
                    <div>
                        <input class="au-input au-input--xl"   type="date" id="advBeginDateId" placeholder="Begin date"/>&nbsp;
                        <input class="au-input au-input--xl"  type="date" id="advEndDateId" placeholder="End date"/>
                    </div>

                </div>-->
                <div class="table-data__tool-right">

                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small"   id="advSearchBtnId">Search</button>
                    <a href="es?action=addExpenses">
                        <button class="au-btn au-btn-icon au-btn--green au-btn--small">add</button>
                    </a>


                </div>
            </div>
            <div class="table-responsive table-responsive-data2">
                <table class="table table-data2">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Date</th>
                        <th>Title</th>
                        <th>Expenses category</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${expensesList}" var="el">
                        <tr class="tr-shadow">
                            <td>${el.r}</td>
                            <td>${el.dataInsert}</td>
                            <td>${el.name}</td>
                            <td>${el.expensesCategory.expensesType}</td>
                            <td>${el.amount}</td>
                            <td>
                                <div class="table-data-feature">
                                    <a href="es?action=editExpenses&id=${el.id}">
                                    <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                                        <i class="fas fa-pencil-alt"></i>
                                    </button>
                                    </a>
                                    <a href="javascript: deleteExpenses('${el.id}','${el.name}');">
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
<script src="js1/expenses.js"></script>

</body>

</html>
<!-- end document-->