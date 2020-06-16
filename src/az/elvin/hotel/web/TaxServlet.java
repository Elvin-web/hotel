package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.TaxDao;
import az.elvin.hotel.dao.TaxTypeDao;
import az.elvin.hotel.dao.impl.TaxDaoImpl;
import az.elvin.hotel.dao.impl.TaxTypeDaoImpl;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.model.Tax;
import az.elvin.hotel.model.TaxType;
import az.elvin.hotel.service.TaxService;
import az.elvin.hotel.service.TaxTypeService;
import az.elvin.hotel.service.impl.TaxServiceImpl;
import az.elvin.hotel.service.impl.TaxTypeServiceImpl;

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

@WebServlet(name = "TaxServlet", urlPatterns = "/ts")
public class TaxServlet extends HttpServlet {
    TaxDao taxDao = new TaxDaoImpl();
    TaxService taxService = new TaxServiceImpl(taxDao);
    TaxTypeDao taxTypeDao = new TaxTypeDaoImpl();
    TaxTypeService taxTypeService = new TaxTypeServiceImpl(taxTypeDao);

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
            if (action.equalsIgnoreCase("getTaxList")) {
                    List<Tax> taxList = taxService.getTaxList();
                    request.setAttribute("taxList", taxList);
                    address1 = "/WEB-INF/pages/taxList.jsp";

            } else if (action.equalsIgnoreCase("addTax")) {
                List<TaxType> taxTypeList = taxTypeService.getTaxTypeList();
                request.setAttribute("taxTypeList", taxTypeList);
                address1 = "/WEB-INF/new/newTax.jsp";
            } else if (action.equalsIgnoreCase("addTax1")) {
                String name = request.getParameter("name");
                String code = request.getParameter("code");
                String taxType = request.getParameter("taxType");
                Double rate = Double.valueOf(request.getParameter("rate"));
                Tax tax = new Tax();
                tax.setName(name);
                tax.setCode(code);
                TaxType taxType1 = new TaxType();
                taxType1.setId(Long.parseLong(taxType));
                tax.setTaxType(taxType1);
                tax.setRate(rate);
                boolean isAdded = taxService.addTax(tax);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editTax")) {
                Long taxId = Long.parseLong(request.getParameter("id"));
                Tax tax = taxService.getTaxById(taxId);
                List<TaxType> taxTypeList = taxTypeService.getTaxTypeList();
                request.setAttribute("taxTypeList", taxTypeList);
                request.setAttribute("tax", tax);
                address1 = "/WEB-INF/edit/editTax.jsp";

            } else if (action.equalsIgnoreCase("updateTax")) {
                Long taxId = Long.parseLong(request.getParameter("taxId"));
                String name = request.getParameter("name");
                String code = request.getParameter("code");
                String taxType = request.getParameter("taxType");
                Double rate = Double.valueOf(request.getParameter("rate"));
                Tax tax = new Tax();
                tax.setName(name);
                tax.setCode(code);
                TaxType taxType1 = new TaxType();
                taxType1.setId(Long.parseLong(taxType));
                tax.setTaxType(taxType1);
                tax.setRate(rate);
                tax.setId(taxId);
                boolean isUpdate = taxService.updateTax(tax);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }else if (action.equalsIgnoreCase("deleteTax")) {
                Long taxId = Long.parseLong(request.getParameter("taxId"));
                boolean isDelete = taxService.deleteTax(taxId);
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
