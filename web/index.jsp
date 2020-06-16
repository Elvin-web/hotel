<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 10/27/2019
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hotel</title>
    <script type="text/javascript" src="js/jquery/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.layout-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
</head>
<a class="navbar navbar"></a>
<body>
 <div class="ui-layout-north">
    <h1 style="text-align: center">Hotel</h1>
</div>
<div class="ui-layout-center">

</div>
<div class="ui-layout-west">
    <input type="button" class="btnDesign" value="Guest" id="guestDataBtnId" <%--onclick="getGuestList()"--%>/><br>
    <input type="button" class="btnDesign" value="Hotel" id="hotelDataBtnId" onclick="getHotelList()"/><br>
    <input type="button" class="btnDesign" value="Reservation" id="reservationBtnId" onclick="getReservationList()"/><br>
    <input type="button" class="btnDesign" value="Fainancial" id="fainancialBtnId"/><br>
    <input type="button" class="btnDesign" value="Rooms" id="roomsBtnId" onclick="getDictionaryList()"/><br>
    <input type="button" class="btnDesign" value="Room types" id="roomTypesBtnId" onclick="getRoomTypeList()"/><br>
    <input type="button" class="btnDesign" value="Price manager" id="priceManagerBtnId" onclick="getPriceList()"/><br>
    <input type="button" class="btnDesign" value="Paid services" id="paidServicesBtnId" onclick="getServicesList()"/><br>
    <input type="button" class="btnDesign" value="Floors" id="floorsBtnId" onclick="getFloorList()"/><br>
    <input type="button" class="btnDesign" value="Amenities" id="amenitiesBtnId" onclick="getAmenitiesList()"/><br>
    <input type="button" class="btnDesign" value="Housekeeping status" id="housekeepingSatusBtnId" onclick="getHousekeepingStatusList()"/><br>
    <input type="button" class="btnDesign" value="Employees" id="employeesBtnId" onclick="getStaffList()"/><br>
    <input type="button" class="btnDesign" value="Position" id="positionBtnId" onclick="getPositionList()"/><br>
    <input type="button" class="btnDesign" value="City" id="cityBtnId" onclick="getCityList()"/><br>
    <input type="button" class="btnDesign" value="Country" id="countryBtnId" onclick="getCountryList()"/><br>
    <input type="button" class="btnDesign" value="Tax Manager" id="taxManagerBtnId" onclick="getTaxList()"/><br>
    <input type="button" class="btnDesign" value="Expenses" id="expensesBtnId" onclick="getExpensesList()"/><br>
    <input type="button" class="btnDesign" value="Expenses category" id="expensesCategoryBtnId" onclick="getExpensesCategoryList()"/><br>

</div>
<div class="ui-layout-east">
   <input type="button" class="btnDesign" value="New" id="newBtnId"/>
</div>
<div class="ui-layout-south">
   <div style="text-align: center">Copyright © Elvin Yusifli</div>
</div>

<div id="newGuestDialogId"></div>
<div id="newHotelDialogId"></div>
</body>
</html>
