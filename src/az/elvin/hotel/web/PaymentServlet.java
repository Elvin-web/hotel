package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PayTypeDao;
import az.elvin.hotel.dao.PaymentDao;
import az.elvin.hotel.dao.ReservationDao;
import az.elvin.hotel.dao.impl.PayTypeDaoImpl;
import az.elvin.hotel.dao.impl.PaymentDaoImpl;
import az.elvin.hotel.dao.impl.ReservationDaoImpl;
import az.elvin.hotel.model.PayType;
import az.elvin.hotel.model.Payment;
import az.elvin.hotel.model.Reservation;
import az.elvin.hotel.service.PayTypeService;
import az.elvin.hotel.service.PaymentService;
import az.elvin.hotel.service.ReservationService;
import az.elvin.hotel.service.impl.PayTypeServiceImpl;
import az.elvin.hotel.service.impl.PaymentServiceImpl;
import az.elvin.hotel.service.impl.ReservationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "PaymentServlet", urlPatterns = "/pas")
public class PaymentServlet extends HttpServlet {
    PaymentDao paymentDao = new PaymentDaoImpl();
    PaymentService paymentService = new PaymentServiceImpl(paymentDao);
    PayTypeDao payTypeDao = new PayTypeDaoImpl();
    PayTypeService payTypeService = new PayTypeServiceImpl(payTypeDao);
    ReservationDao reservationDao = new ReservationDaoImpl();
    ReservationService reservationService = new ReservationServiceImpl(reservationDao);
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
            if (action.equalsIgnoreCase("getPaymentList")) {

                List<Payment> paymentList = paymentService.getPaymentList();
                request.setAttribute("paymentList", paymentList);
                address1 = "/WEB-INF/pages/paymentList.jsp";

            } else if (action.equalsIgnoreCase("addPayment")) {
                /*Long reservationId = Long.parseLong(request.getParameter("id"));
                Reservation reservationList = reservationService.getReservationById(reservationId);
                request.setAttribute("reservationList", reservationList);
                Long paymentId = Long.parseLong(request.getParameter("id"));
                Payment payment = paymentService.getPaymentById(paymentId);
                request.setAttribute("payment", payment);*/
                Long reservationId = Long.parseLong(request.getParameter("id"));
                Payment payment = paymentService.getPaymentByReservationId1(reservationId);
                request.setAttribute("payment", payment);
                List<PayType> payTypeList = payTypeService.getPayTypeList();
                request.setAttribute("payTypeList", payTypeList);
                address1 = "/WEB-INF/new/newPayment.jsp";
            } else if (action.equalsIgnoreCase("addPayment1")) {
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                Payment payment1 = paymentService.getPaymentByReservationId1(reservationId);
                Long paymentId=Long.parseLong(request.getParameter("paymentId"));
                String addedDate = request.getParameter("addedDate");
                String paymentMethod = request.getParameter("paymentMethod");
                String amount = request.getParameter("amount");
                Payment payment = new Payment();
                Reservation reservation1 = new Reservation();
                reservation1.setId(reservationId);
                payment.setReservation(reservation1);
                payment.setAddedDate(df.parse(addedDate));
                PayType payType = new PayType();
                payType.setId(Long.parseLong(paymentMethod));
                payment.setPayType(payType);
                payment.setPaymentAmount(Double.valueOf(amount));
                payment.setPending(Math.abs(payment1.getPending()-Double.valueOf(amount)));
                payment.setId(paymentId);
                boolean isUpdate = paymentService.updatePayment(payment);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
                /* String reservation = request.getParameter("select");
                String invoiceNumber = request.getParameter("text-input");
                String addedDate = request.getParameter("text-input1");
                String paymentMethod = request.getParameter("select1");
                String amount = request.getParameter("text-input3");*/
            } else if (action.equalsIgnoreCase("editPayment")) {
                Long paymentId = Long.parseLong(request.getParameter("id"));
                Payment payment = paymentService.getPaymentById(paymentId);
                request.setAttribute("payment", payment);
                List<Reservation> reservationList = reservationService.getReservationList();
                request.setAttribute("reservationList", reservationList);
                List<PayType> payTypeList = payTypeService.getPayTypeList();
                request.setAttribute("payTypeList", payTypeList);
                address1 = "/WEB-INF/edit/editPayment.jsp";
            } else if (action.equalsIgnoreCase("viewPayment")) {
                Long paymentId = Long.parseLong(request.getParameter("id"));
                Payment payment = paymentService.getPaymentById(paymentId);
                List<Reservation> reservationList = reservationService.getReservationList();
                request.setAttribute("reservationList", reservationList);
                List<PayType> payTypeList = payTypeService.getPayTypeList();
                request.setAttribute("payTypeList", payTypeList);
                request.setAttribute("payment", payment);
                address1 = "/WEB-INF/pages/viewPayment.jsp";
            } else if (action.equalsIgnoreCase("invoicePayment")) {
                Long paymentId = Long.parseLong(request.getParameter("id"));
                Payment payment = paymentService.getPaymentById(paymentId);
                List<Reservation> reservationList = reservationService.getReservationList();
                request.setAttribute("reservationList", reservationList);
                List<PayType> payTypeList = payTypeService.getPayTypeList();
                request.setAttribute("payTypeList", payTypeList);
                request.setAttribute("payment", payment);
                address1 = "/WEB-INF/pages/invoicePayment.jsp";
            } else if (action.equalsIgnoreCase("updatePayment")) {
                Long paymentId = Long.parseLong(request.getParameter("paymentId"));
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                Payment payment1 = paymentService.getPaymentByReservationId1(reservationId);
                String addedDate = request.getParameter("addedDate");
                String paymentMethod = request.getParameter("paymentMethod");
                String amount = request.getParameter("amount");
                Payment payment = new Payment();
                Reservation reservation1 = new Reservation();
                reservation1.setId(reservationId);
                payment.setReservation(reservation1);
                payment.setAddedDate(df.parse(addedDate));
                PayType payType = new PayType();
                payType.setId(Long.parseLong(paymentMethod));
                payment.setPayType(payType);
                payment.setPaymentAmount(Double.valueOf(amount));
                payment.setPending(Math.abs((payment1.getPending()+payment1.getPaymentAmount())-Double.valueOf(amount)));
                payment.setId(paymentId);
                boolean isUpdate = paymentService.updatePayment(payment);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteAmenities")) {
              /*  Long amenitiesId = Long.parseLong(request.getParameter("amenitiesId"));
                boolean isDelete = amenitiesService.deleteAmenities(amenitiesId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
*/
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
