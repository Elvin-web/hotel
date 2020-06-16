<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/30/2019
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<form action="pcs?action=addAmenities1" method="post" enctype="multipart/form-data">
    <div class="row form-group">
        <div class="col col-md-3">
            <label class=" form-control-label">Image</label>
        </div>

        <div class="col-12 col-md-9">
            <input type="file" name="fileName" class="form-control-file">
        </div>
    </div>
    <br>
    <div class="row form-group">
    <div class="col col-md-3">
        <label class=" form-control-label">name</label>
    </div>
    <div class="col-12 col-md-9">
        <input type="text" name="studentName" class="form-control-file">
    </div>
    </div>
    <input type="submit" value="Upload">
</form>
</body>
</html>
