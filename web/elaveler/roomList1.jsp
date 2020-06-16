<%@ page import="az.elvin.hotel.model.Room" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 11/10/2019
  Time: 3:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel</title>
    <link rel="stylesheet" type="text/css" href="../css/layout.css"/>
    <script type="text/javascript" src="../js/main.js"></script>

</head>
<body>
<% List<Room> roomList= (List<Room>) request.getAttribute("roomList"); %>
<div id="container">
    <jsp:include page="/static/header.jsp"></jsp:include>
    <jsp:include page="/static/menu.jsp"></jsp:include>
    <jsp:include page="/static/footer.jsp"></jsp:include>
    <div id="content">
        <table border="1" id="roomTableId">
            <thead>
            <tr>
                <th>#</th>
                <th>Room number</th>
                <th>Room floor</th>
            </tr>
            </thead>
            <tbody>
            <% for(Room room: roomList) { %>


            <tr>
                <td><%=room.getR()%></td>
                <td><%=room.getRoomNumber()%>%</td>
                <td><%=room.getFloorNumber()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>

    </div>

</div>

</body>
</html>

