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
                                        <strong>Room Form</strong> edit
                                    </div>
                                    <div class="card-body card-block">
                                        <form action="ds?action=updateDictionary" method="post"
                                              enctype="multipart/form-data" class="form-horizontal">
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-inputU"
                                                           class=" form-control-label"><strong>ID</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-input5U"
                                                           value="${room.id}"
                                                           name="roomId" placeholder="ID"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter room number</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="select1U"
                                                           class=" form-control-label"><strong>Room
                                                        type</strong></label>
                                                    <select name="roomType" style="width: 400px;" id="select1U"
                                                            class="form-control">
                                                        <option value="0">Please select room type</option>
                                                        <c:forEach items="${roomTypeList}" var="rtl">
                                                            <option value="${rtl.id}">${rtl.roomType}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-inputU"
                                                           class=" form-control-label"><strong>Room
                                                        number</strong></label>
                                                    <input type="text" style="width: 400px;" id="text-inputU"
                                                           value="${room.roomNumber}"
                                                           name="room" placeholder="Room number"
                                                           class="form-control">
                                                    <small class="form-text text-muted">Please enter room number</small>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="selectU"
                                                           class=" form-control-label"><strong>Floor</strong></label>
                                                    <select name="floor" style="width: 400px;" id="selectU"
                                                            class="form-control">
                                                        <option value="0">Please select floor</option>
                                                        <c:forEach items="${floorList}" var="fl">
                                                            <option value="${fl.id}">${fl.name} ${fl.floorNumber}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="multiple-selectU"
                                                           class=" form-control-label"><strong>Amenities</strong></label>
                                                    <select name="amenities" style="width: 400px;"
                                                            id="multiple-selectU" multiple class="form-control">
                                                        <c:forEach items="${amenitiesList}" var="al">
                                                            <c:set var="isSelected" value="false"/>
                                                            <c:forEach items="${dictionaryList}" var="rolUsu">
                                                                <c:if test="${rolUsu.amenities.id==al.id}">


                                                                    <c:if test="${rolUsu.active==1}">
                                                                        <c:set var="isSelected" value="true"/>
                                                                    </c:if>

                                                                </c:if>
                                                            </c:forEach>
                                                            <c:choose>
                                                                <c:when test="${isSelected}">
                                                                    <option value="${al.id}"
                                                                            selected="selected">${al.name}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${al.id}">${al.name}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <!--<button type="submit" class="btn btn-primary btn-sm">
                                                Update
                                            </button>-->
                                            <button type="button" class="btn btn-primary btn-sm"
                                                    id="dictionarySubmitIdU"> Save
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

<script src="js1/dictionary.js"></script>
<script type="text/javascript">
    $(function () {

        $(function () {
            $('#select1U').val('${room.roomType.id}');
            $('#selectU').val('${room.floor.id}');
        });
    });
</script>
</body>

</html>

