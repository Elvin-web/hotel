<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/17/2019
  Time: 4:22 PM
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
        <div class="main-content" id="main">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <strong>Expenses Form</strong> edit
                                </div>
                                <div class="card-body card-block">
                                    <form action="es?action=updateExpenses" method="post" enctype="multipart/form-data"
                                          class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-inputU" class=" form-control-label">Date</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-inputU" value="${expenses.dataInsert}"
                                                       name="text-inputU" placeholder="Date" class="form-control">
                                                <small class="form-text text-muted">Please enter date</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input1U" class=" form-control-label">Title</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input1U" value="${expenses.name}"
                                                       name="text-input"
                                                       placeholder="Title" class="form-control">
                                                <small class="form-text text-muted">Please enter title</small>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="selectU" class=" form-control-label">Expenses
                                                    category</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="selectU" id="selectU" class="form-control">
                                                    <option value="0">Please select Expenses category</option>
                                                    <c:forEach items="${expensesCategoryList}" var="ecl">
                                                        <option value="${ecl.id}">${ecl.expensesType}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="text-input2U" class=" form-control-label">Amount</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="text-input2U" value="${expenses.amount}"
                                                       name="text-input"
                                                       placeholder="Amount" class="form-control">
                                                <small class="form-text text-muted">This is a help text</small>
                                            </div>
                                        </div>

                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="file-inputU" class=" form-control-label">Attachment</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="file" id="file-inputU" name="file-input"
                                                       class="form-control-file">
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div class="card-footer">
                                    <a href="javascript: updateExpenses('${expenses.id}');">
                                    <button type="button" class="btn btn-primary btn-sm" id="expensesSubmitIdU">
                                        <i class="fa fa-dot-circle"></i> Update
                                    </button>
                                    </a>
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
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js1/expenses.js"></script>
<script type="text/javascript">
    $(function () {
        $('#text-inputU').datepicker({
            changeMonth: true,
            changeYear: true
        });
        $(function () {
            $('#selectU').val('${expenses.expensesCategory.id}');
        });
    });
</script>
</body>

</html>
<!-- end document-->
