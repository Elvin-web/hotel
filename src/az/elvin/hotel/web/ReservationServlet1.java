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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "ReservationServlet1" , urlPatterns = "/rs1")
public class ReservationServlet1 extends HttpServlet {
    DictionaryDao dictionaryDao = new DictionaryDaoImpl();
    DictionaryService dictionaryService = new DictionaryServiceImpl(dictionaryDao);
    ReservationDao reservationDao = new ReservationDaoImpl();
    ReservationService reservationService = new ReservationServiceImpl(reservationDao);
    RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
    RoomTypeService roomTypeService = new RoomTypeServiceImpl(roomTypeDao);
    GuestDao guestDao = new GuestDaoImpl();
    GuestService guestService = new GuestServiceImpl(guestDao);
    PaymentDao paymentDao = new PaymentDaoImpl();
    PaymentService paymentService = new PaymentServiceImpl(paymentDao);
    PayTypeDao payTypeDao = new PayTypeDaoImpl();
    PayTypeService payTypeService = new PayTypeServiceImpl(payTypeDao);
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

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
            } else if (action.equalsIgnoreCase("moreReservation")) {
                Long reservationId = Long.parseLong(request.getParameter("id"));
                Reservation reservation = reservationService.getReservationById(reservationId);
                request.setAttribute("reservation", reservation);
                List<Payment> paymentList = paymentService.getPaymentList();
                request.setAttribute("paymentList", paymentList);
                List<Reservation> reservationList = reservationService.getReservationList();
                request.setAttribute("reservationList", reservationList);
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                request.setAttribute("roomTypeList", roomTypeList);
                address1 = "/WEB-INF/pages/reservation.jsp";
            } else if (action.equalsIgnoreCase("deleteReservation")) {
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                boolean isDelete = reservationService.deleteReservation(reservationId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }

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
