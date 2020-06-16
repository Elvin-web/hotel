package az.elvin.hotel.web;

import az.elvin.hotel.dao.*;
import az.elvin.hotel.dao.impl.*;
import az.elvin.hotel.model.*;
import az.elvin.hotel.service.*;
import az.elvin.hotel.service.impl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ControllerServlet", urlPatterns = "/cs")
public class ControllerServlet extends HttpServlet {
    AmenitiesDao amenitiesDao = new AmenitiesDaoImpl();
    AmenitiesService amenitiesService = new AmenitiesServiceImpl(amenitiesDao);
    CityDao cityDao = new CityDaoImpl();
    CityService cityService = new CityServiceImpl(cityDao);
    CountryDao countryDao = new CountryDaoImpl();
    CountryService countryService = new CountryServiceImpl(countryDao);
    DictionaryDao dictionaryDao = new DictionaryDaoImpl();
    DictionaryService dictionaryService = new DictionaryServiceImpl(dictionaryDao);
    ExpensesCategoryDao expensesCategoryDao = new ExpensesCategoryDaoImpl();
    ExpensesCategoryService expensesCategoryService = new ExpensesCategoryServiceImpl(expensesCategoryDao);
    ExpensesDao expensesDao = new ExpensesDaoImpl();
    ExpensesService expensesService = new ExpensesServiceImpl(expensesDao);
    FloorDao floorDao = new FloorDaoImpl();
    FloorService floorService = new FloorServiceImpl(floorDao);
    GuestDao guestDao = new GuestDaoImpl();
    GuestService guestService = new GuestServiceImpl(guestDao);
    HotelDao hotelDao = new HotelDaoImpl();
    HotelService hotelService = new HotelServiceImpl(hotelDao);
    HousekeepingStatusDao housekeepingStatusDao = new HousekeepingStatusDaoImpl();
    HousekeepingStatusService housekeepingStatusService = new HousekeepingStatusServiceImpl(housekeepingStatusDao);
    PassportDao passportDao = new PassportDaoImpl();
    PassportService passportService = new PassportServiceImpl(passportDao);
    PositionDao positionDao = new PositionDaoImpl();
    PositionService positionService = new PositionServiceImpl(positionDao);
    PriceDao priceDao = new PriceDaoImpl();
    PriceService priceService = new PriceServiceImpl(priceDao);
    PriceTypeDao priceTypeDao = new PriceTypeDaoImpl();
    PriceTypeService priceTypeService = new PriceTypeServiceImpl(priceTypeDao);
    ReservationDao reservationDao = new ReservationDaoImpl();
    ReservationService reservationService = new ReservationServiceImpl(reservationDao);
    RoomDao roomDao = new RoomDaoImpl();
    RoomService roomService = new RoomServiceImpl(roomDao);
    RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
    RoomTypeService roomTypeService = new RoomTypeServiceImpl(roomTypeDao);
    ServicesDao servicesDao = new ServicesDaoImpl();
    ServicesService servicesService = new ServicesServiceImpl(servicesDao);
    Services_RoomTypeDao services_roomTypeDao = new Services_RoomTypeDaoImpl();
    Services_RoomTypeService services_roomTypeService = new Services_RoomTypeServiceImpl(services_roomTypeDao);
    StaffDao staffDao = new StaffDaoImpl();
    StaffService staffService = new StaffServiceImpl(staffDao);
    StarDao starDao = new StarDaoImpl();
    StarService starService = new StarServiceImpl(starDao);
    TaxDao taxDao = new TaxDaoImpl();
    TaxService taxService = new TaxServiceImpl(taxDao);
    TaxTypeDao taxTypeDao = new TaxTypeDaoImpl();
    TaxTypeService taxTypeService = new TaxTypeServiceImpl(taxTypeDao);
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat df2 = new SimpleDateFormat("HH:mm a");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address1 = null;
        String action = null;
        PrintWriter pw = response.getWriter();
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("getDashboardList")) {
                Connection c = DbHelper.getConnection();
                if (c != null) {
                    address1 = "/WEB-INF/pages/dashboard.jsp";
                }

            } else if (action.equalsIgnoreCase("getMenusList")) {
                Connection c = DbHelper.getConnection();
                if (c != null) {
                    List<Guest> guestList = guestService.getGuestList();
                    List<City> cityList = cityService.getCityList();
                    List<Passport> passportList = passportService.getPassportList();
                    request.setAttribute("guestList", guestList);
                    request.setAttribute("cityList", cityList);
                    request.setAttribute("passportList", passportList);
                    address1 = "/WEB-INF/pages/guestList.jsp";
                }

            } else if (action.equalsIgnoreCase("getCalendarList")) {
                Connection c = DbHelper.getConnection();
                if (c != null) {
                    address1 = "/WEB-INF/pages/calendar.jsp";
                }

            } else if (action.equalsIgnoreCase("searchAmenitiesData")) {
                String keyword = request.getParameter("keyword");
                List<Amenities> amenitiesList = amenitiesService.searchAmenitiesData(keyword);
                request.setAttribute("amenitiesList", amenitiesList);
                address1 = "/WEB-INF/pages/amenitiesList.jsp";
            } else if (action.equalsIgnoreCase("getExpensesListByExpensesCategoryId")) {
                Long expensesCategoryId = Long.parseLong(request.getParameter("expensesCategoryId"));
                List<Expenses> expensesList = expensesService.getExpensesListByExpensesCategoryId(expensesCategoryId);
                request.setAttribute("expensesList", expensesList);
                address1 = "/WEB-INF/pages/expensesCategoryCombo.jsp";
            } else if (action.equalsIgnoreCase("getCityListByCountryId")) {
                Long countryId = Long.parseLong(request.getParameter("countryId"));
                List<City> cityList = cityService.getCityListByCountryId(countryId);
                request.setAttribute("cityList", cityList);
                address1 = "/WEB-INF/pages/cityCombo.jsp";
            } else if (action.equalsIgnoreCase("advancedSearchGuestData")) {
                Long countryCombo = Long.parseLong(request.getParameter("countryCombo"));
                Long cityCombo = Long.parseLong(request.getParameter("cityCombo"));
                Long passportCombo = Long.parseLong(request.getParameter("passportCombo"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String ID = request.getParameter("ID");
                String beginDate = request.getParameter("beginDate");
                String endDate = request.getParameter("endDate");
                AdvancedSearch advancedSearch = new AdvancedSearch();
               /* advancedSearch.setCountryId(countryCombo);
                advancedSearch.setCityId(cityCombo);
                advancedSearch.setPassportId(passportCombo);
                advancedSearch.setName(name);
                advancedSearch.setSurname(surname);
                advancedSearch.setPhone(phone);
                advancedSearch.setEmail(email);*/
               // advancedSearch.setID(ID);
                advancedSearch.setBeginDate(beginDate);
                advancedSearch.setEndDate(endDate);
                List<Guest> guestList = guestService.advancedSearchGuestData(advancedSearch);
                request.setAttribute("guestList", guestList);
                address1 = "/WEB-INF/pages/guestData.jsp";
            } else if (action.equalsIgnoreCase("searchGuestData")) {
                String keyword = request.getParameter("keyword");
                List<Guest> guestList = guestService.searchGuestData(keyword);
                request.setAttribute("guestList", guestList);
                address1 = "/WEB-INF/pages/guestList1.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (address1 != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address1);
            dispatcher.forward(request, response);
        }
    }
}