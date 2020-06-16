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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReservationServlet", urlPatterns = "/rs")
public class ReservationServlet extends HttpServlet {
    BookingStatusDao bookingStatusDao = new BookingStatusDaoImpl();
    BookingStatusService bookingStatusService = new BookingStatusServiceImpl(bookingStatusDao);
    RoomDao roomDao = new RoomDaoImpl();
    RoomService roomService = new RoomServiceImpl(roomDao);
    ReservationDao reservationDao = new ReservationDaoImpl();
    ReservationService reservationService = new ReservationServiceImpl(reservationDao);
    RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
    RoomTypeService roomTypeService = new RoomTypeServiceImpl(roomTypeDao);
    GuestDao guestDao = new GuestDaoImpl();
    GuestService guestService = new GuestServiceImpl(guestDao);
    PaymentDao paymentDao = new PaymentDaoImpl();
    PaymentService paymentService = new PaymentServiceImpl(paymentDao);
    PaymentStatusDao paymentStatusDao = new PaymentStatusDaoImpl();
    PaymentStatusService paymentStatusService = new PaymentStatusServiceImpl(paymentStatusDao);
    PayTypeDao payTypeDao = new PayTypeDaoImpl();
    PayTypeService payTypeService = new PayTypeServiceImpl(payTypeDao);
    ServicesDao servicesDao=new ServicesDaoImpl();
    ServicesService servicesService=new ServicesServiceImpl(servicesDao);
    TaxDao taxDao=new TaxDaoImpl();
    TaxService taxService=new TaxServiceImpl(taxDao);
    Service_ReservationDao service_reservationDao = new Service_ReservationDaoImpl();
    Service_ReservationService service_reservationService = new Service_ReservationServiceImpl(service_reservationDao);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

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
            if (action.equalsIgnoreCase("getReservationList")) {

                List<Reservation> reservationList = reservationService.getReservationList();
                request.setAttribute("reservationList", reservationList);
                List<Guest> guestList = guestService.getGuestList();
                request.setAttribute("guestList", guestList);
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                request.setAttribute("roomTypeList", roomTypeList);
                List<BookingStatus> bookingStatusList = bookingStatusService.getBookingStatusList();
                request.setAttribute("bookingStatusList", bookingStatusList);
                List<PaymentStatus> paymentStatusList = paymentStatusService.getPaymentStatusList();
                request.setAttribute("paymentStatusList", paymentStatusList);
                address1 = "/WEB-INF/pages/reservationList.jsp";

            } else if (action.equalsIgnoreCase("editReservation")) {
                Long reservationId = Long.parseLong(request.getParameter("id"));
                Reservation reservation = reservationService.getReservationById(reservationId);
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                List<Guest> guestList = guestService.getGuestList();
                request.setAttribute("roomTypeList", roomTypeList);
                request.setAttribute("reservation", reservation);
                request.setAttribute("guestList", guestList);
                address1 = "/WEB-INF/edit/editReservation.jsp";
            } else if (action.equalsIgnoreCase("editReservationRoom")) {
                Long reservationRoomId = Long.parseLong(request.getParameter("id"));
                Reservation reservation = reservationService.getReservationById(reservationRoomId);
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                List<Guest> guestList = guestService.getGuestList();
                request.setAttribute("roomTypeList", roomTypeList);
                request.setAttribute("reservation", reservation);
                request.setAttribute("guestList", guestList);
                address1 = "/WEB-INF/edit/editReservationRoom.jsp";
            } else if (action.equalsIgnoreCase("updateReservationRoom")) {
                Long reservationRoomId = Long.parseLong(request.getParameter("reservationRoomId"));
                String room = request.getParameter("room");
                Reservation reservation = new Reservation();
                RoomType roomType = new RoomType();
                Dictionary dictionary = new Dictionary();
                Room room1 = new Room();
                room1.setId(Long.parseLong(room));
                dictionary.setRoom(room1);
                //roomType.setDictionary(dictionary);
                reservation.setRoomType(roomType);
                reservation.setId(reservationRoomId);
                boolean isUpdate = reservationService.updateReservation(reservation);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("addReservationRoom")) {
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                String room = request.getParameter("room");
                Room_Reservation room_reservation = new Room_Reservation();
                Reservation reservation = new Reservation();
                reservation.setId(reservationId);
                room_reservation.setReservation(reservation);
                Room room1 = new Room();
                room1.setId(Long.parseLong(room));
                room_reservation.setRoom(room1);
                boolean isAdded = reservationService.addRoomReservation(room_reservation);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("getReservationDetails")) {
                List<Reservation> reservationList = reservationService.getReservationList();
                request.setAttribute("reservationList", reservationList);
                address1 = "/WEB-INF/pages/reservation.jsp";

            } else if (action.equalsIgnoreCase("addReservation")) {
                List<Guest> guestList = guestService.getGuestList();
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                request.setAttribute("guestList", guestList);
                request.setAttribute("roomTypeList", roomTypeList);
                address1 = "/WEB-INF/new/newReservation.jsp";
            } else if (action.equalsIgnoreCase("addReservation1")) {
                String guest = request.getParameter("guest");
                String roomType = request.getParameter("roomType");
                String adults = request.getParameter("adults");
                String kids = request.getParameter("kids");
                String checkIn = request.getParameter("checkIn");
                String checkOut = request.getParameter("checkOut");
                String night = request.getParameter("night");
                String extraBed = request.getParameter("extraBed");
                System.out.println("night=" + night);
                Reservation reservation = new Reservation();
                Guest guest1 = new Guest();
                guest1.setId(Long.parseLong(guest));
                reservation.setGuest(guest1);
                RoomType roomType1 = new RoomType();
                roomType1.setId(Long.parseLong(roomType));
                reservation.setRoomType(roomType1);
                reservation.setChildren(Long.parseLong(kids));
                reservation.setAdults(Long.parseLong(adults));
                reservation.setCheckIn(df.parse(checkIn));
                reservation.setCheckOut(df.parse(checkOut));
                reservation.setNight(Long.parseLong(night));
                reservation.setExtaBed(Integer.parseInt(extraBed));
                boolean isAdded = reservationService.addReservation(reservation);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateReservation")) {
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                String guest = request.getParameter("guest");
                String roomType = request.getParameter("roomType");
                String adults = request.getParameter("adults");
                String kids = request.getParameter("kids");
                String checkIn = request.getParameter("checkIn");
                String checkOut = request.getParameter("checkOut");
                Reservation reservation = new Reservation();
                Guest guest1 = new Guest();
                guest1.setId(Long.parseLong(guest));
                reservation.setGuest(guest1);
                RoomType roomType1 = new RoomType();
                roomType1.setId(Long.parseLong(roomType));
                reservation.setRoomType(roomType1);
                reservation.setChildren(Long.parseLong(kids));
                reservation.setAdults(Long.parseLong(adults));
                reservation.setCheckIn(df.parse(checkIn));
                reservation.setCheckOut(df.parse(checkOut));
                reservation.setId(reservationId);
                boolean isUpdate = reservationService.updateReservation(reservation);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateReservation1")) {//bura yazilmalid
                Reservation reservation = new Reservation();
                String bookingStatus = request.getParameter("bookingStatus");
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                if (bookingStatus.equals("2")) {
                    Reservation reservation1 = reservationService.getReservationById(reservationId);
                    Long night = reservation1.getNight();
                    Long roomTypeId = reservation1.getRoomType().getId();
                    RoomType roomType = roomTypeService.getRoomTypeById(roomTypeId);
                    Double roomTypeBasePrice = roomType.getPrice().getBasePrice();
                    List<Tax> taxList=taxService.getTaxList();
                    Double cem=0.0;
                    for(Tax tax : taxList){
                       Double rate = tax.getRate();
                     cem+=rate;
                    }
                    Payment payment = new Payment();
                    Double cost = Math.abs(night * roomTypeBasePrice);
                    payment.setAllNightCost(cost);
                    payment.setPending(cost);
                    payment.setSum(cost);
                    payment.setTaxCost(Math.abs(cost/cem));
                    reservation.setId(reservationId);
                    payment.setReservation(reservation);
                    boolean isAdded = paymentService.addPayment(payment);
                }
                BookingStatus bookingStatus1 = new BookingStatus();
                bookingStatus1.setId(Long.parseLong(bookingStatus));
                reservation.setBookingStatus(bookingStatus1);
                reservation.setId(reservationId);
                boolean isUpdate = reservationService.updateReservation1(reservation);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateReservation2")) {
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                String paymentStatus = request.getParameter("paymentStatus");
                Reservation reservation = new Reservation();
                PaymentStatus paymentStatus1 = new PaymentStatus();
                paymentStatus1.setId(Long.parseLong(paymentStatus));
                reservation.setPaymentStatus(paymentStatus1);
                reservation.setId(reservationId);
                boolean isUpdate = reservationService.updateReservation2(reservation);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("moreReservation")) {
                Long reservationId = Long.parseLong(request.getParameter("id"));
                Reservation reservation = reservationService.getReservationById(reservationId);
                HttpSession session = request.getSession(true);
                session.setAttribute("reservation", reservation);
                List<BookingStatus> bookingStatusList = bookingStatusService.getBookingStatusList();
                request.setAttribute("bookingStatusList", bookingStatusList);
                List<PaymentStatus> paymentStatusList = paymentStatusService.getPaymentStatusList();
                request.setAttribute("paymentStatusList", paymentStatusList);

                Payment payment = paymentService.getPaymentByReservationId2(reservationId);
                request.setAttribute("payment", payment);

                List<Room> roomList = roomService.getRoomList();
                request.setAttribute("roomList", roomList);

                List<Room_Reservation> room_reservation = reservationService.getReservationRoomById(reservationId);
                request.setAttribute("room_reservation", room_reservation);

                List<Services> servicesList = servicesService.getServicesList();
                request.setAttribute("servicesList", servicesList);

                List<Service_Reservation> service_reservationList = service_reservationService.getService_ReservationListByreservationId(reservationId);
                request.setAttribute("service_reservationList", service_reservationList);

                List<Tax> taxList=taxService.getTaxList();
                request.setAttribute("taxList", taxList);
                System.out.println("taxList="+taxList);
                 /*request.setAttribute("reservation", reservation);
                   HttpSession session1 = request.getSession(true);
                session1.setAttribute("payment", payment);
                List<Payment> paymentList1 = paymentService.getPaymentListByReservationId(reservationId);
                request.setAttribute("paymentList1", paymentList1);
                List<Payment> paymentList = paymentService.getPaymentList();
                request.setAttribute("paymentList", paymentList);
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                request.setAttribute("roomTypeList", roomTypeList);
                List<Reservation> reservationList = reservationService.getReservationList();
                request.setAttribute("reservationList", reservationList);*/
                address1 = "/WEB-INF/pages/reservation1.jsp";
            } else if (action.equalsIgnoreCase("deleteReservation")) {
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                boolean isDelete = reservationService.deleteReservation(reservationId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }

            } else if (action.equalsIgnoreCase("advancedSearchReservationData")) {
                String roomTypeCombo = request.getParameter("roomTypeCombo");
                String paymentStatusCombo = request.getParameter("paymentStatusCombo");
                String bookingStatusCombo = request.getParameter("bookingStatusCombo");
                String guestCombo = request.getParameter("guestCombo");
                String beginDate = request.getParameter("beginDate");
                String endDate = request.getParameter("endDate");
                AdvancedSearch advancedSearch = new AdvancedSearch();
                advancedSearch.setRoomTypeId(Long.parseLong(roomTypeCombo));
                advancedSearch.setPaymentStatusId(Long.parseLong(paymentStatusCombo));
                advancedSearch.setBookingStatusId(Long.parseLong(bookingStatusCombo));
                advancedSearch.setGuestId(Long.parseLong(guestCombo));
                advancedSearch.setBeginDate(beginDate);
                advancedSearch.setEndDate(endDate);
                List<Reservation> reservationList = reservationService.advancedSearchReservationData(advancedSearch);
                request.setAttribute("reservationList", reservationList);

                List<Guest> guestList = guestService.getGuestList();
                request.setAttribute("guestList", guestList);
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                request.setAttribute("roomTypeList", roomTypeList);
                List<BookingStatus> bookingStatusList = bookingStatusService.getBookingStatusList();
                request.setAttribute("bookingStatusList", bookingStatusList);
                List<PaymentStatus> paymentStatusList = paymentStatusService.getPaymentStatusList();
                request.setAttribute("paymentStatusList", paymentStatusList);
                address1 = "/WEB-INF/search/reservationData.jsp";
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
