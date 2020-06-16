<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/7/2019
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
    .svg-inline--fa
    {
        margin-right: 10px !important;
    }
    .fa-search
    {
        color: white !important;
    }
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${login.role.roleName eq 'Saff'}">

        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="#">
                    <img src="images/icon/logo.png" alt="Cool Admin"/>
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li><a href="cs?action=getDashboardList"><i class="fas fa-tachometer-alt"></i>Dashboard</a></li>
                        <li><a href="rs?action=getReservationList" id="bookingId"><i class="fas fa-list-alt"></i>Booking</a></li>
                        <li><a href="pas?action=getPaymentList" id="paymentId"><i class="fas  fa-credit-card"></i>Payment</a></li>
                        <li><a class="test" href="gs?action=getGuestList1" id="guestId1"><i  class="fas fa-users"></i>Guest</a></li>
                        <li><a href="cs?action=getMenusList"><i class="fas fa-bars"></i>Menus</a></li>
                        <li><a href="cs?action=getCalendarList"><i class="fas fa-calendar-alt"></i>Availability Calendar</a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-users"></i>HR Management<i
                                    class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="ss?action=getStaffList">Employees</a></li>
                                <li><a href="cs?action=getDepartmentsList">Departments</a></li>
                                <li><a href="ps?action=getPositionList">Designations</a></li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-suitcase"></i>CMS<i class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="cs?action=getPagesList">Pages</a></li>
                                <li><a href="cs?action=getBannersList">Banners</a></li>
                                <li><a href="cs?action=getGalleryList">Gallery</a></li>
                                <li><a href="cs?action=getMailList">Mail Templates</a></li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-cogs"></i>Administrative<i
                                    class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="hs?action=getHotelList">Settings</a></li>
                                <li><a href="cs?action=getLanguagesList">Languages</a></li>
                                <li><a href="cs?action=getCurrencyList">Currency</a></li>
                                <li><a href="cos?action=getCountryList">Country</a></li>
                                <li><a href="cis?action=getCityList">City</a></li>
                                <li><a href="ts?action=getTaxList">Tax Manager</a></li>
                                <li><a href="cs?action=getTestimonialsList">Testimonials</a></li>

                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-tags"></i>Expenses <i class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li>
                                    <a href="es?action=getExpensesList">Expenses</a>
                                </li>
                                <li>
                                    <a href="ecs?action=getExpensesCategoryList">Expenses Category</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
    </c:when>
    <c:when test="${login.role.roleName eq 'Boss'}">

    </c:when>
    <c:otherwise>
        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="#">
                    <img src="images/icon/logo.png" alt="Cool Admin"/>
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li><a href="cs?action=getDashboardList"><i class="fas fa-tachometer-alt"></i>Dashboard</a></li>
                        <li><a href="rs?action=getReservationList"><i class="fas fa-list-alt"></i>Booking</a></li>
                        <li><a href="pas?action=getPaymentList"><i class="fas  fa-credit-card"></i>Payment</a></li>
                        <li><a href="gs?action=getGuestList1" id="guestId"><i class="fas fa-users"></i>Guest</a></li>
                        <li><a href="cs?action=getMenusList"><i class="fas fa-bars"></i>Menus</a></li>
                        <li><a href="cs?action=getCalendarList"><i class="fas fa-calendar-alt"></i>Availability Calendar</a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-cubes"></i>Hotel Configuration<i
                                    class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="rts?action=getRoomTypeList">Room Types</a></li>
                                <li><a href="ds?action=getDictionaryList">Rooms</a></li>
                                <li><a href="cs?action=getPriceList">Price Manager</a></li>
                                <li><a href="ses?action=getServicesList">Paid Services</a></li>
                                <li><a href="cs?action=getCouponList">Coupon Management</a></li>
                                <li><a href="fs?action=getFloorList">Floors</a></li>
                                <li><a href="as?action=getAmenitiesList" id="amenitiesBtnId">Amenities</a></li>
                                <li><a href="hss?action=getHousekeepingStatusList">Housekeeping Status</a></li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-th-list"></i>Reports<i class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="cs?action=getOccupancyList">Occupancy</a></li>
                                <li><a href="cs?action=getGuestList">Guest</a></li>
                                <li><a href="cs?action=getFinancialList">Financial</a></li>
                                <li><a href="cs?action=getCouponList">Coupon</a></li>
                                <li><a href="cs?action=getExpensesList">Expenses</a></li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-users"></i>HR Management<i
                                    class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="ss?action=getStaffList">Employees</a></li>
                                <li><a href="cs?action=getDepartmentsList">Departments</a></li>
                                <li><a href="ps?action=getPositionList">Designations</a></li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-suitcase"></i>CMS<i class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="cs?action=getPagesList">Pages</a></li>
                                <li><a href="cs?action=getBannersList">Banners</a></li>
                                <li><a href="cs?action=getGalleryList">Gallery</a></li>
                                <li><a href="cs?action=getMailList">Mail Templates</a></li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-cogs"></i>Administrative<i
                                    class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li><a href="hs?action=getHotelList">Settings</a></li>
                                <li><a href="cs?action=getLanguagesList">Languages</a></li>
                                <li><a href="cs?action=getCurrencyList">Currency</a></li>
                                <li><a href="cos?action=getCountryList">Country</a></li>
                                <li><a href="cis?action=getCityList">City</a></li>
                                <li><a href="ts?action=getTaxList">Tax Manager</a></li>
                                <li><a href="cs?action=getTestimonialsList">Testimonials</a></li>

                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-tags"></i>Expenses <i class="fas fa-angle-left pull-right"></i></a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                                <li>
                                    <a href="es?action=getExpensesList">Expenses</a>
                                </li>
                                <li>
                                    <a href="ecs?action=getExpensesCategoryList">Expenses Category</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
    </c:otherwise>

</c:choose>

