package az.elvin.hotel.web;

import az.elvin.hotel.dao.ExpensesCategoryDao;
import az.elvin.hotel.dao.ExpensesDao;
import az.elvin.hotel.dao.impl.ExpensesCategoryDaoImpl;
import az.elvin.hotel.dao.impl.ExpensesDaoImpl;
import az.elvin.hotel.model.Expenses;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.service.ExpensesCategoryService;
import az.elvin.hotel.service.ExpensesService;
import az.elvin.hotel.service.impl.ExpensesCategoryServiceImpl;
import az.elvin.hotel.service.impl.ExpensesServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ExpensesServlet", urlPatterns = "/es")
public class ExpensesServlet extends HttpServlet {
    ExpensesCategoryDao expensesCategoryDao = new ExpensesCategoryDaoImpl();
    ExpensesCategoryService expensesCategoryService = new ExpensesCategoryServiceImpl(expensesCategoryDao);
    ExpensesDao expensesDao = new ExpensesDaoImpl();
    ExpensesService expensesService = new ExpensesServiceImpl(expensesDao);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;

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
            if (action.equalsIgnoreCase("getExpensesList")) {
                List<Expenses> expensesList = expensesService.getExpensesList();
                List<ExpensesCategory> expensesCategoryList = expensesCategoryService.getExpensesCategoryList();
                request.setAttribute("expensesList", expensesList);
                request.setAttribute("expensesCategoryList", expensesCategoryList);
                address1 = "/WEB-INF/pages/expensesList.jsp";
            } else if (action.equalsIgnoreCase("addExpenses")) {
                List<ExpensesCategory> expensesCategoryList = expensesCategoryService.getExpensesCategoryList();
                request.setAttribute("expensesCategoryList", expensesCategoryList);
                address1 = "/WEB-INF/new/newExpenses.jsp";
            } else if (action.equalsIgnoreCase("addExpenses1")) {
                Expenses expenses = new Expenses();
                String filePath = "";
                String fileName = "";
                String newFileName = "";
                if (!ServletFileUpload.isMultipartContent(request)) {
                    response.getWriter().println("Does not support!");
                    return;
                }
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(THRESHOLD_SIZE);
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(MAX_FILE_SIZE);
                upload.setSizeMax(REQUEST_SIZE);
                System.out.println(getServletContext().getRealPath(""));
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                try {
                    List formItems = upload.parseRequest(request);
                    Iterator iter = formItems.iterator();
                    UUID uuid = UUID.randomUUID();
                    while (iter.hasNext()) {
                        FileItem item = (FileItem) iter.next();
                        if (!item.isFormField()) {
                            fileName = new File(item.getName()).getName();
                            newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                            filePath = uploadPath + File.separator + newFileName;
                            System.out.println("filePath=" + filePath);
                            expenses.setDocument(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("dataInsert")) {
                                String dataInsert = item.getString();
                                expenses.setDataInsert(df.parse(dataInsert));
                                System.out.println("dataInsert=" + dataInsert);
                            } else if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                expenses.setName(name);
                            } else if (item.getFieldName().equalsIgnoreCase("expensesType")) {
                                String expensesType = item.getString();
                                ExpensesCategory expensesCategory = new ExpensesCategory();
                                expensesCategory.setId(Long.parseLong(expensesType));
                                expenses.setExpensesCategory(expensesCategory);
                            } else if (item.getFieldName().equalsIgnoreCase("amount")) {
                                String amount = item.getString();
                                expenses.setAmount(Double.valueOf(amount));
                            }
                        }

                    }
                    boolean isAdded = expensesService.addExpenses(expenses);
                    address1 = "/WEB-INF/pages/expensesList.jsp";
                    response.setContentType("text/html");
                    if (isAdded) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                } catch (Exception ex) {
                    request.setAttribute("message", "There was an error:" + ex.getMessage());
                    ex.printStackTrace();
                }

            } else if (action.equalsIgnoreCase("editExpenses")) {
                Long expensesId = Long.parseLong(request.getParameter("id"));
                Expenses expenses = expensesService.getExpensesById(expensesId);
                String filePath = expenses.getDocument().substring(expenses.getDocument().lastIndexOf("\\"));
                String filePath2 = "upload" +  File.separator + filePath;
                request.setAttribute("filePath2",filePath2);
                request.setAttribute("expenses", expenses);
                List<ExpensesCategory> expensesCategoryList = expensesCategoryService.getExpensesCategoryList();
                request.setAttribute("expensesCategoryList", expensesCategoryList);
                address1 = "/WEB-INF/edit/editExpenses.jsp";
            }
            else if (action.equalsIgnoreCase("viewExpenses")) {
                Long expensesId = Long.parseLong(request.getParameter("id"));
                Expenses expenses = expensesService.getExpensesById(expensesId);
                String filePath = expenses.getDocument().substring(expenses.getDocument().lastIndexOf("\\"));
                String filePath2 = "upload" +  File.separator + filePath;
                request.setAttribute("filePath2",filePath2);
                request.setAttribute("expenses", expenses);
                List<ExpensesCategory> expensesCategoryList = expensesCategoryService.getExpensesCategoryList();
                request.setAttribute("expensesCategoryList", expensesCategoryList);
                address1 = "/WEB-INF/pages/viewExpenses.jsp";
            }
            else if (action.equalsIgnoreCase("updateExpenses")) {
               // Long expensesId = Long.parseLong(request.getParameter("id"));
                Expenses expenses = new Expenses();
                String filePath = "";
                String fileName = "";
                String newFileName = "";
                if (!ServletFileUpload.isMultipartContent(request)) {
                    response.getWriter().println("Does not support!");
                    return;
                }
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(THRESHOLD_SIZE);
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(MAX_FILE_SIZE);
                upload.setSizeMax(REQUEST_SIZE);
                System.out.println(getServletContext().getRealPath(""));
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                try {
                    List formItems = upload.parseRequest(request);
                    Iterator iter = formItems.iterator();
                    UUID uuid = UUID.randomUUID();
                    while (iter.hasNext()) {
                        FileItem item = (FileItem) iter.next();
                        if (!item.isFormField()) {
                            fileName = new File(item.getName()).getName();
                            newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                            filePath = uploadPath + File.separator + newFileName;
                            System.out.println("filePath=" + filePath);
                            expenses.setDocument(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("dataInsert")) {
                                String dataInsert = item.getString();
                                expenses.setDataInsert(df.parse(dataInsert));
                                System.out.println("dataInsert=" + dataInsert);
                            } else if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                expenses.setName(name);
                            } else if (item.getFieldName().equalsIgnoreCase("expensesType")) {
                                String expensesType = item.getString();
                                ExpensesCategory expensesCategory = new ExpensesCategory();
                                expensesCategory.setId(Long.parseLong(expensesType));
                                expenses.setExpensesCategory(expensesCategory);
                            } else if (item.getFieldName().equalsIgnoreCase("amount")) {
                                String amount = item.getString();
                                expenses.setAmount(Double.valueOf(amount));
                            } else if (item.getFieldName().equalsIgnoreCase("id")) {
                                String expensesId = item.getString();
                                expenses.setId(Long.parseLong(expensesId));
                            }
                        }

                    }

                    boolean isUpdate = expensesService.updateExpenses(expenses);
                    response.setContentType("text/html");
                    address1 = "/WEB-INF/pages/expensesList.jsp";
                    if (isUpdate) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                } catch (Exception ex) {
                    request.setAttribute("message", "There was an error:" + ex.getMessage());
                    ex.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("deleteExpenses")) {
                Long expensesId = Long.parseLong(request.getParameter("expensesId"));
                boolean isDelete = expensesService.deleteExpenses(expensesId);
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
