<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/5/2019
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    .au-btn--blue {
        background-color: #e2eda4 !important;
    }
</style>
<style>
    .au-btn--green{
        background-color: #eda663 !important;
    }
</style>
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

    <!--: : before -->

    <jsp:include page="/static2/menu.jsp"></jsp:include>

    <div id="content-wrapper" class="content-wrapper">

        <div class="container-fluid">


            <!-- DataTables Example -->

            <div class="card mb-3" style="border: none">

                    <div class="card-header">Expenses &nbsp;<small>List</small>
                    <div class="btn-group fa-pull-right">
                        <a href="es?action=addExpenses">
                            <button class="btn btn-success"><strong>+</strong> Add</button>
                        </a>
                    </div>
                </div>
                <div class="card-body">
                    <div style="margin-bottom: 20px;">Expenses</div>
                    <div class="table-responsive">
                        <table class="table table-borderless" id="dataTable" style="width: 100%;" cellspacing="0">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Date</th>
                                <th>Title</th>
                                <th>Expenses category</th>
                                <th>Amount</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${expensesList}" var="el">
                                <tr role="row" class="odd">
                                    <td>${el.id}</td>
                                    <td>${el.dataInsert}</td>
                                    <td>${el.name}</td>
                                    <td>${el.expensesCategory.expensesType}</td>
                                    <td>${el.amount}</td>
                                    <td>
                                        <div class="btn-group" style="float: right">
                                            <a class="btn au-btn--green"
                                               href="es?action=viewExpenses&id=${el.id}">
                                                <i class="fas fa-eye"></i>View
                                            </a>
                                            <a class="btn btn-primary"
                                               href="es?action=editExpenses&id=${el.id}">
                                                <i class="fas fa-pencil-alt"></i>Edit
                                            </a>

                                            <a class="btn btn-danger"
                                               href="javascript: deleteExpenses('${el.id}','${el.name}');">
                                                <i class="fas fa-trash"></i>Delete
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->


    </div>
    <!-- /.content-wrapper -->
        <!--: : after -->

</div>
<!-- Sticky Footer -->
<footer class="footer " style="background-color: #418bd6 ">
    <jsp:include page="/static2/footer.jsp"></jsp:include>
</footer>

<!-- /#wrapper -->

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

<script src="js1/expenses.js"></script>
</body>

</html>