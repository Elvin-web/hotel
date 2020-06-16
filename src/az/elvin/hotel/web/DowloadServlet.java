package az.elvin.hotel.web;

import az.elvin.hotel.dao.ExpensesCategoryDao;
import az.elvin.hotel.dao.ExpensesDao;
import az.elvin.hotel.dao.impl.ExpensesCategoryDaoImpl;
import az.elvin.hotel.dao.impl.ExpensesDaoImpl;
import az.elvin.hotel.model.Expenses;
import az.elvin.hotel.service.ExpensesCategoryService;
import az.elvin.hotel.service.ExpensesService;
import az.elvin.hotel.service.impl.ExpensesCategoryServiceImpl;
import az.elvin.hotel.service.impl.ExpensesServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet(name = "DowloadServlet", urlPatterns = "/dos")
public class DowloadServlet extends HttpServlet {
    ExpensesDao expensesDao = new ExpensesDaoImpl();
    ExpensesService expensesService = new ExpensesServiceImpl(expensesDao);
  /*  ExpensesCategoryDao expensesCategoryDao = new ExpensesCategoryDaoImpl();
    ExpensesCategoryService expensesCategoryService = new ExpensesCategoryServiceImpl(expensesCategoryDao);*/

    /* DateFormat df = new SimpleDateFormat("dd-MM-yyyy");*/
      /* protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }*/


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        // }


        // private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /*String address1 = null;*/
        String action = null;
        /*  PrintWriter pw = response.getWriter();*/
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("dowloadExpenses")) {
                Long expensesId = Long.parseLong(request.getParameter("id"));
                Expenses expenses = expensesService.getExpensesById(expensesId);
                String filePath2 = expenses.getDocument();
                System.out.println("filePath2" + filePath2);
                File dowloadFile = new File(filePath2);
                FileInputStream inStream = new FileInputStream(dowloadFile);
                ServletContext context = getServletContext();
                String mimeType = context.getMimeType(filePath2);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                response.setContentLength((int) dowloadFile.length());
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", dowloadFile.getName());
                response.setHeader(headerKey, headerValue);
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inStream.close();
                outStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  if (address1 != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address1);
            dispatcher.forward(request, response);
        }*/
    }
}
