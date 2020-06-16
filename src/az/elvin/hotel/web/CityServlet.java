package az.elvin.hotel.web;

import az.elvin.hotel.dao.CityDao;
import az.elvin.hotel.dao.CountryDao;
import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.impl.CityDaoImpl;
import az.elvin.hotel.dao.impl.CountryDaoImpl;
import az.elvin.hotel.model.City;
import az.elvin.hotel.model.Country;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.service.CityService;
import az.elvin.hotel.service.CountryService;
import az.elvin.hotel.service.impl.CityServiceImpl;
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

@WebServlet(name = "CityServlet", urlPatterns = "/cis")
public class CityServlet extends HttpServlet {
    CityDao cityDao = new CityDaoImpl();
    CityService cityService = new CityServiceImpl(cityDao);
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
            if (action.equalsIgnoreCase("getCityList")) {

                    List<City> cityList = cityService.getCityList();
                    List<Country> countryList = countryService.getCountryList();
                    request.setAttribute("cityList", cityList);
                    request.setAttribute("countryList", countryList);
                    address1 = "/WEB-INF/pages/cityList.jsp";

            } else if (action.equalsIgnoreCase("addCity")) {
                List<Country> countryList = countryService.getCountryList();
                request.setAttribute("countryList", countryList);
                address1 = "/WEB-INF/new/newCity.jsp";
            } else if (action.equalsIgnoreCase("addCity1")) {
                String name = request.getParameter("name");
                String nameCountry = request.getParameter("country");
                City city = new City();
                city.setName(name);
                Country country = new Country();
                country.setId(Long.parseLong(nameCountry));
                city.setCountry(country);
                boolean isAdded = cityService.addCity(city);
                address1 = "/WEB-INF/pages/cityList.jsp";
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editCity")) {
                Long cityId = Long.parseLong(request.getParameter("id"));
                City city = cityService.getCityById(cityId);
                List<Country> countryList = countryService.getCountryList();
                request.setAttribute("countryList", countryList);
                request.setAttribute("city", city);
                address1 = "/WEB-INF/edit/editCity.jsp";
            } else if (action.equalsIgnoreCase("updateCity")) {
                Long cityId = Long.parseLong(request.getParameter("cityId"));
                String name = request.getParameter("name");
                String nameCountry = request.getParameter("country");
                City city = new City();
                city.setName(name);
                Country country = new Country();
                country.setId(Long.parseLong(nameCountry));
                city.setCountry(country);
                city.setId(cityId);
                boolean isUpdate = cityService.updateCity(city);
                address1 = "/WEB-INF/pages/cityList.jsp";
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }
            else if (action.equalsIgnoreCase("deleteCity")) {
                Long cityId = Long.parseLong(request.getParameter("cityId"));
                boolean isDelete = cityService.deleteCity(cityId);
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
