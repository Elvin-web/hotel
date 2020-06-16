package az.elvin.hotel.web;

import az.elvin.hotel.dao.HotelDao;
import az.elvin.hotel.dao.impl.HotelDaoImpl;
import az.elvin.hotel.service.HotelService;
import az.elvin.hotel.service.impl.HotelServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WebSiteServlet",urlPatterns = "/ws")
public class WebSiteServlet extends HttpServlet {

    HotelDao hotelDao=new HotelDaoImpl();
    HotelService hotelService=new HotelServiceImpl(hotelDao);

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
            if (action.equalsIgnoreCase("login")) {
                address1 = "/login.jsp";

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
