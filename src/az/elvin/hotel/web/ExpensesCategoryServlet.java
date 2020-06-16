package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.ExpensesCategoryDao;
import az.elvin.hotel.dao.impl.ExpensesCategoryDaoImpl;
import az.elvin.hotel.model.AdvancedSearchExpensesCategory;
import az.elvin.hotel.model.Expenses;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.service.ExpensesCategoryService;
import az.elvin.hotel.service.impl.ExpensesCategoryServiceImpl;

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

@WebServlet(name = "ExpensesCategoryServlet", urlPatterns = "/ecs")
public class ExpensesCategoryServlet extends HttpServlet {
    ExpensesCategoryDao expensesCategoryDao = new ExpensesCategoryDaoImpl();
    ExpensesCategoryService expensesCategoryService = new ExpensesCategoryServiceImpl(expensesCategoryDao);

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
            if (action.equalsIgnoreCase("getExpensesCategoryList")) {

                    List<ExpensesCategory> expensesCategoryList = expensesCategoryService.getExpensesCategoryList();
                    request.setAttribute("expensesCategoryList", expensesCategoryList);
                    address1 = "/WEB-INF/pages/expensesCategoryList.jsp";

            } else if (action.equalsIgnoreCase("addExpensesCategory")) {
                address1 = "/views/newExpensesCategory.jsp";
            } else if (action.equalsIgnoreCase("addExpensesCategory1")) {
                String name = request.getParameter("name");
                ExpensesCategory expensesCategory = new ExpensesCategory();
                expensesCategory.setExpensesType(name);
                boolean isAdded = expensesCategoryService.addExpensesCategory(expensesCategory);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editExpensesCategory")) {
                Long expensesCategoryId = Long.parseLong(request.getParameter("id"));
                ExpensesCategory expensesCategory = expensesCategoryService.getExpensesCategoryById(expensesCategoryId);
                request.setAttribute("expensesCategory", expensesCategory);
                address1 = "/WEB-INF/edit/editExpensesCategory.jsp";

            } else if (action.equalsIgnoreCase("updateExpensesCategory")) {
                Long expensesCategoryId = Long.parseLong(request.getParameter("expensesCategoryId"));
                String name = request.getParameter("name");
                ExpensesCategory expensesCategory = new ExpensesCategory();
                expensesCategory.setExpensesType(name);
                expensesCategory.setId(expensesCategoryId);
                boolean isUpdate = expensesCategoryService.updateExpensesCategory(expensesCategory);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }else if (action.equalsIgnoreCase("deleteExpensesCategory")) {
                Long expensesCategoryId = Long.parseLong(request.getParameter("expensesCategoryId"));
                boolean isDelete = expensesCategoryService.deleteExpensesCategory(expensesCategoryId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }

            }else if (action.equalsIgnoreCase("advancedSearchExpensesCategoryData")) {
                String expensesCategory = request.getParameter("expensesCategory");
                AdvancedSearchExpensesCategory advancedSearchExpensesCategory = new AdvancedSearchExpensesCategory();
                advancedSearchExpensesCategory.setExpensesType(expensesCategory);
                List<ExpensesCategory> expensesCategoryList = expensesCategoryService.advancedSearchExpensesCategoryData(advancedSearchExpensesCategory);
                request.setAttribute("expensesCategoryList", expensesCategoryList);
                address1 = "/WEB-INF/search/expensesCategoryData.jsp";
            }else if (action.equalsIgnoreCase("searchExpensesCategory")) {
                String keyword = request.getParameter("keyword");
                List<ExpensesCategory> expensesCategoryList = expensesCategoryService.searchExpensesCategoryData(keyword);
                request.setAttribute("expensesCategoryList", expensesCategoryList);
                address1 = "/WEB-INF/pages/expensesCategoryList.jsp";
               // address1 = "/WEB-INF/search/expensesCategoryData.jsp";
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
