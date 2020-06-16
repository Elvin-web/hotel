package az.elvin.hotel.web;

import az.elvin.hotel.dao.CountryDao;
import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.impl.CountryDaoImpl;
import az.elvin.hotel.model.Country;
import az.elvin.hotel.service.CountryService;
import az.elvin.hotel.service.impl.CountryServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "CountryServlet", urlPatterns = "/cos")
public class CountryServlet extends HttpServlet {
    CountryDao countryDao = new CountryDaoImpl();
    CountryService countryService = new CountryServiceImpl(countryDao);

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
            if (action.equalsIgnoreCase("getCountryList")) {

                    List<Country> countryList = countryService.getCountryList();
                    request.setAttribute("countryList", countryList);
                    address1 = "/WEB-INF/pages/countryList.jsp";


            } else if (action.equalsIgnoreCase("addCountry")) {
                address1 = "/views/newCountry.jsp";
            } else if (action.equalsIgnoreCase("addCountry1")) {
                String name = request.getParameter("name");
                String sortName = request.getParameter("sortName");
                Country country = new Country();
                country.setName(name);
                country.setSortName(sortName);
                boolean isAdded = countryService.addCountry(country);
                address1 = "/WEB-INF/pages/countryList.jsp";
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editCountry")) {
                Long countryId = Long.parseLong(request.getParameter("id"));
                Country country = countryService.getCountryById(countryId);
                request.setAttribute("country", country);
                address1 = "/WEB-INF/edit/editCountry.jsp";

            } else if (action.equalsIgnoreCase("updateCountry")) {
                Country country = new Country();
                Long countryId = Long.parseLong(request.getParameter("countryId"));
                String name = request.getParameter("name");
                String sortName = request.getParameter("sortName");
                country.setName(name);
                country.setSortName(sortName);
                country.setId(countryId);
                boolean isUpdate = countryService.updateCountry(country);
                address1 = "/WEB-INF/pages/countryList.jsp";
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }
            else if (action.equalsIgnoreCase("deleteCountry")) {
                Long countryId = Long.parseLong(request.getParameter("countryId"));
                boolean isDelete = countryService.deleteCountry(countryId);
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
