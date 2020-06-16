package az.elvin.hotel.web;

import az.elvin.hotel.dao.PriceDao;
import az.elvin.hotel.dao.PriceTypeDao;
import az.elvin.hotel.dao.impl.PriceDaoImpl;
import az.elvin.hotel.dao.impl.PriceTypeDaoImpl;
import az.elvin.hotel.model.Price;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.service.PriceService;
import az.elvin.hotel.service.PriceTypeService;
import az.elvin.hotel.service.impl.PriceServiceImpl;
import az.elvin.hotel.service.impl.PriceTypeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PriceServlet", urlPatterns = "/prs")
public class PriceServlet extends HttpServlet {
    PriceDao priceDao = new PriceDaoImpl();
    PriceService priceService = new PriceServiceImpl(priceDao);
    PriceTypeDao priceTypeDao = new PriceTypeDaoImpl();
    PriceTypeService priceTypeService = new PriceTypeServiceImpl(priceTypeDao);

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
            if (action.equalsIgnoreCase("getPriceList")) {
                List<RoomType> priceList = priceService.getPriceList();
                request.setAttribute("priceList", priceList);
                address1 = "/WEB-INF/pages/priceList.jsp";

            } else if (action.equalsIgnoreCase("addPrice")) {
                address1 = "/views/newPrice.jsp";
            } else if (action.equalsIgnoreCase("addPrice1")) {
                /*String name = request.getParameter("name");
                String nameCountry = request.getParameter("country");
                City city = new City();
                city.setName(name);
                Country country = new Country();
                country.setId(Long.parseLong(nameCountry));
                city.setCountry(country);
                boolean isAdded = cityService.addCity(city);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }*/
            } else if (action.equalsIgnoreCase("editPrice")) {
                Long priceId = Long.parseLong(request.getParameter("id"));
                Price price = priceService.getPriceById(priceId);
                request.setAttribute("price", price);
                address1 = "/WEB-INF/edit/editPrice.jsp";
            } else if (action.equalsIgnoreCase("updatePrice")) {
              /*  Long cityId = Long.parseLong(request.getParameter("cityId"));
                String name = request.getParameter("name");
                String nameCountry = request.getParameter("country");
                City city = new City();
                city.setName(name);
                Country country = new Country();
                country.setId(Long.parseLong(nameCountry));
                city.setCountry(country);
                city.setId(cityId);
                boolean isUpdate = cityService.updateCity(city);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }*/
            }
            else if (action.equalsIgnoreCase("deletePrice")) {
                Long priceId = Long.parseLong(request.getParameter("priceId"));
                boolean isDelete = priceService.deletePrice(priceId);
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
