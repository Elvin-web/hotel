package az.elvin.hotel.web;

import az.elvin.hotel.dao.LoginDao;
import az.elvin.hotel.dao.PaymentDao;
import az.elvin.hotel.dao.ReservationDao;
import az.elvin.hotel.dao.RoomDao;
import az.elvin.hotel.dao.impl.LoginDaoImpl;
import az.elvin.hotel.dao.impl.PaymentDaoImpl;
import az.elvin.hotel.dao.impl.ReservationDaoImpl;
import az.elvin.hotel.dao.impl.RoomDaoImpl;
import az.elvin.hotel.model.LoginUser;
import az.elvin.hotel.model.Payment;
import az.elvin.hotel.service.LoginService;
import az.elvin.hotel.service.PaymentService;
import az.elvin.hotel.service.ReservationService;
import az.elvin.hotel.service.RoomService;
import az.elvin.hotel.service.impl.LoginServiceImpl;
import az.elvin.hotel.service.impl.PaymentServiceImpl;
import az.elvin.hotel.service.impl.ReservationServiceImpl;
import az.elvin.hotel.service.impl.RoomServiceImpl;
import az.elvin.hotel.util.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/ls")
public class LoginServlet extends HttpServlet {

    RoomDao roomDao = new RoomDaoImpl();
    RoomService roomService = new RoomServiceImpl(roomDao);
    ReservationDao reservationDao = new ReservationDaoImpl();
    ReservationService reservationService = new ReservationServiceImpl(reservationDao);
    PaymentDao paymentDao = new PaymentDaoImpl();
    PaymentService paymentService = new PaymentServiceImpl(paymentDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);
        String address = null;
        String action = null;
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    LoginUser loginUser = loginService.login(username, password);
                    if (loginUser != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("login", loginUser);
                        Long roomAcount = roomService.roomAcount();
                        session.setAttribute("roomAcount", roomAcount);
                        Long reservationGuestAcount = reservationService.reservationGuestAcount();
                        session.setAttribute("reservationGuestAcount", reservationGuestAcount);
                        Long reservationTodayAcount = reservationService.reservationTodayAcount();
                        session.setAttribute("reservationTodayAcount", reservationTodayAcount);
                        Long ToDayRevenueCount = paymentService.ToDayRevenueCount();
                        session.setAttribute("ToDayRevenueCount", ToDayRevenueCount);
                        List<Payment> paymentList = paymentService.getPaymentList();
                        session.setAttribute("paymentList", paymentList);
                        Map<Date,Payment>map=new HashMap<>();
                        for (Payment payment : paymentList) {
                           map.put(new Date() ,payment);
                        }
                        session.setAttribute("map", map);
                        address = "index3.jsp";
                    } else {
                        address = "login.jsp";
                        request.setAttribute("invalid", "Username or password is invalid!");
                    }
                } else {
                    request.setAttribute("invalid", "Username or password is empty!");
                    address = "login.jsp";
                }
            } else if (action.equalsIgnoreCase("forgotPassword")) {
                address = "WEB-INF/pages/forgotPassword.jsp";
            } else if (action.equalsIgnoreCase("forgot")) {
                String token = UUID.randomUUID().toString();
                String email = request.getParameter("email");
                if (email != null && !email.isEmpty()) {
                    LoginUser loginUser = loginService.checkEmail(email);
                    if (loginUser != null) {
                        boolean isUpdate = loginService.updateTokenById(token, loginUser.getId_hotel_login());
                        if (isUpdate) {
                            String text = "Change you password with this link: http://localhost:8081/hotel/ls?action=changePassword&token=" + token;

                            boolean isSend = Utility.sendMail(email, "Forgot Password", text);
                            if (isSend) {
                                request.setAttribute("success", "Mail has been successfully sended");
                            } else {
                                request.setAttribute("invalid", "Problem! Mail has not been successfully sended");

                            }
                        } else {
                            request.setAttribute("invalid", "Problem! Update token is failed!");
                        }
                    } else {
                        request.setAttribute("invalid", "Problem! Email not found!");

                    }
                } else {
                    request.setAttribute("invalid", "Problem! Email is empty!");

                }
                address = "WEB-INF/pages/forgotPassword.jsp";
            } else if (action.equalsIgnoreCase("changePassword")) {
                String token = request.getParameter("token");
                request.setAttribute("token", token);
                address = "WEB-INF/pages/changePassword.jsp";
            } else if (action.equalsIgnoreCase("change")) {
                String token = request.getParameter("token");
                String password = request.getParameter("password");
                String repeatpassword = request.getParameter("repeatpassword");
                if (password.equals(repeatpassword)) {
                    boolean isChange = loginService.changePassword(password, token);
                    if (isChange) {
                        boolean isChangeToken = loginService.updateToken(token);
                        if (isChangeToken) {
                            request.setAttribute("success", "Your password has been successfully changed");
                            address = "login.jsp";
                        } else {
                            request.setAttribute("invalid", "Poblem! Update token is failed!");
                            address = "changePassword.jsp";
                        }
                    } else {
                        request.setAttribute("invalid", "Poblem! Password  has not been successfully changed!");
                        address = "changePassword.jsp";
                    }
                } else {
                    request.setAttribute("invalid", "Password are not equals!");
                    address = "changePassword.jsp";
                }
            }
            if (address != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
