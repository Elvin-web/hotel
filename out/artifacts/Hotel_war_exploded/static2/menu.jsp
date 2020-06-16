<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/7/2019
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    .svg-inline--fa {
        margin-right: 10px !important;
    }

    .fa-fw fa-tachometer-alt {
        color: #777777 !important;
    }

    .dropdown-menu {
        background-color: rgba(119, 119, 119, 0.75);
    }

</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${login.role.roleName eq 'saff'}">
        <!-- Sidebar -->
        <ul class="sidebar navbar-nav">
            <li class="header" style="color: #ebebeb">MAIN NAVIGATION</li>
            <li class="nav-item active">
                <a class="nav-link" href="cs?action=getDashboardList">
                    <i class="fas fa-tachometer-alt"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="rs?action=getReservationList" id="bookingId1">
                    <i class="fas fa-list-alt"></i>
                    <span>Booking</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="gs?action=getGuestList1" id="guestId1"><i class="fas fa-users"></i>
                    <span>Guest</span>
                </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown21" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-cubes"></i>
                    <span>Hotel Configuration</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="rts?action=getRoomTypeList">Room Types</a>
                    <a class="dropdown-item" href="ds?action=getDictionaryList">Rooms</a>
                    <a class="dropdown-item" href="prs?action=getPriceList">Price Manager</a>
                    <a class="dropdown-item" href="ses?action=getServicesList">Paid Services</a>
                    <a class="dropdown-item" href="fs?action=getFloorList">Floors</a>
                    <a class="dropdown-item" href="as?action=getAmenitiesList" id="amenitiesBtnId1">Amenities</a>
                    <a class="dropdown-item" href="hss?action=getHousekeepingStatusList">Housekeeping Status</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown22" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-users"></i>
                    <span>HR Management</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="ss?action=getStaffList">Employees</a>
                    <a class="dropdown-item" href="ps?action=getPositionList">Designations</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown23" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-tags"></i>
                    <span>Expenses</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="es?action=getExpensesList">Expenses</a>
                    <a class="dropdown-item" href="ecs?action=getExpensesCategoryList">Expenses Category</a>
                </div>
            </li>
        </ul>
    </c:when>
    <c:when test="${login.role.roleName eq 'admin'}">
        <!-- Sidebar -->
        <section class="sidebar" style="height: auto">
            <div class="user-panel">
                <!--   <div class="pull-left image">
                       <img src="" class="img-circle" alt="User Image">
                   </div>-->
                <div class="nav-item" style="margin-top: 20px;margin-left: 20px;">
                    <h5><p style="color: #ebebeb">${login.staff.name} ${login.staff.surname} </p></h5>
                    <i class="fa fa-circle text-success" style="display: inline-block"></i>
                    <p style="color: #ebebeb;display: inline">Online</p>
                </div>
            </div>
            <ul class="sidebar navbar-nav">
                <li class="nav-item ">
                    <a class="nav-link" href="index3.jsp">
                        <i class="fas fa-tachometer-alt"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="rs?action=getReservationList" id="bookingId">
                        <i class="fas fa-list-alt"></i>
                        <span>Booking</span>
                    </a>
                </li>

                <li class="nav-item ">
                    <a class="nav-link" href="gs?action=getGuestList1" id="guestId"><i class="fas fa-users"></i>
                        <span>Guest</span>
                    </a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-cubes"></i>
                        <span>Hotel Configuration</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <a class="dropdown-item" href="rts?action=getRoomTypeList">Room Types</a>
                        <a class="dropdown-item" href="ds?action=getDictionaryList">Rooms</a>
                        <a class="dropdown-item" href="prs?action=getPriceList">Price Manager</a>
                        <a class="dropdown-item" href="ses?action=getServicesList">Paid Services</a>
                        <a class="dropdown-item" href="fs?action=getFloorList">Floors</a>
                        <a class="dropdown-item" href="as?action=getAmenitiesList" id="amenitiesBtnId">Amenities</a>
                        <a class="dropdown-item" href="hss?action=getHousekeepingStatusList">Housekeeping Status</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown4" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-th-list"></i>
                        <span>Reports</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <a class="dropdown-item" href="cs?action=getOccupancyList">Occupancy</a>
                        <a class="dropdown-item" href="cs?action=getGuestList">Guest</a>
                        <a class="dropdown-item" href="cs?action=getFinancialList">Financial</a>
                        <a class="dropdown-item" href="cs?action=getCouponList">Coupon</a>
                        <a class="dropdown-item" href="cs?action=getExpensesList">Expenses</a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown1" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-users"></i>
                        <span>HR Management</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <a class="dropdown-item" href="ss?action=getStaffList">Employees</a>
                        <a class="dropdown-item" href="ps?action=getPositionList">Designations</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown2" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-cogs"></i>
                        <span>Administrative</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <a class="dropdown-item" href="hs?action=getHotelList">Settings</a>
                        <a class="dropdown-item" href="cos?action=getCountryList">Country</a>
                        <a class="dropdown-item" href="cis?action=getCityList">City</a>
                        <a class="dropdown-item" href="ts?action=getTaxList">Tax Manager</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-tags"></i>
                        <span>Expenses</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <a class="dropdown-item" href="es?action=getExpensesList">Expenses</a>
                        <a class="dropdown-item" href="ecs?action=getExpensesCategoryList">Expenses Category</a>
                    </div>
                </li>
            </ul>
        </section>
    </c:when>
    <c:otherwise>
        <!-- Sidebar -->
        <ul class="sidebar navbar-nav">
            <li class="header" style="color: #ebebeb">MAIN NAVIGATION</li>
            <li class="nav-item active">
                <a class="nav-link" href="cs?action=getDashboardList">
                    <i class="fas fa-tachometer-alt"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="rs?action=getReservationList" id="bookingId2">
                    <i class="fas fa-list-alt"></i>
                    <span>Booking</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="gs?action=getGuestList1" id="guestId2"><i class="fas fa-users"></i>
                    <span>Guest</span>
                </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown10" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-cubes"></i>
                    <span>Hotel Configuration</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="rts?action=getRoomTypeList">Room Types</a>
                    <a class="dropdown-item" href="ds?action=getDictionaryList">Rooms</a>
                    <a class="dropdown-item" href="prs?action=getPriceList">Price Manager</a>
                    <a class="dropdown-item" href="ses?action=getServicesList">Paid Services</a>
                    <a class="dropdown-item" href="fs?action=getFloorList">Floors</a>
                    <a class="dropdown-item" href="as?action=getAmenitiesList" id="amenitiesBtnId2">Amenities</a>
                    <a class="dropdown-item" href="hss?action=getHousekeepingStatusList">Housekeeping Status</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown11" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-users"></i>
                    <span>HR Management</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="ss?action=getStaffList">Employees</a>
                    <a class="dropdown-item" href="ps?action=getPositionList">Designations</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown12" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-cogs"></i>
                    <span>Administrative</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="hs?action=getHotelList">Settings</a>
                    <a class="dropdown-item" href="cos?action=getCountryList">Country</a>
                    <a class="dropdown-item" href="cis?action=getCityList">City</a>
                    <a class="dropdown-item" href="ts?action=getTaxList">Tax Manager</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown13" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-tags"></i>
                    <span>Expenses</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="es?action=getExpensesList">Expenses</a>
                    <a class="dropdown-item" href="ecs?action=getExpensesCategoryList">Expenses Category</a>
                </div>
            </li>
        </ul>
    </c:otherwise>

</c:choose>
