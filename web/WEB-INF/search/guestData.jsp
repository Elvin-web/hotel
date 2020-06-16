<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/13/2019
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function () {
       $('#guestTableId').DataTable();
    });
</script>

<div class="table-responsive table-responsive-data2">
    <table class="table table-data2">
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
        <c:forEach items="${guestList1}" var="gl">
            <tr class="tr-shadow">

            <tr>
                <td>${gl.r}</td>
                <td>${gl.guestName}</td>
                <td>${gl.country.name}</td>
                <td><span class="block-email">${gl.email}</span></td>
                <td class="desc">${gl.phone}</td>
                <td>
                    <div class="table-data-feature">
                        <button class="item" data-toggle="tooltip" data-placement="top" title="Send">
                            <i class="zmdi zmdi-mail-send"></i>
                        </button>
                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit">
                            <i class="zmdi zmdi-edit"></i>
                        </button>
                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
                            <i class="zmdi zmdi-delete"></i>
                        </button>
                        <button class="item" data-toggle="tooltip" data-placement="top" title="More">
                            <i class="zmdi zmdi-more"></i>
                        </button>
                    </div>
                </td>
            </tr>
            <tr class="spacer"></tr>

        </c:forEach>

        </tbody>
    </table>

</div>

