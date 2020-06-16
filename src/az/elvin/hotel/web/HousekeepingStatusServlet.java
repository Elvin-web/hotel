package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.HousekeepingStatusDao;
import az.elvin.hotel.dao.impl.HousekeepingStatusDaoImpl;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.model.HousekeepingStatus;
import az.elvin.hotel.service.HousekeepingStatusService;
import az.elvin.hotel.service.impl.HousekeepingStatusServiceImpl;

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

@WebServlet(name = "HousekeepingStatusServlet", urlPatterns = "/hss")
public class HousekeepingStatusServlet extends HttpServlet {
    HousekeepingStatusDao housekeepingStatusDao = new HousekeepingStatusDaoImpl();
    HousekeepingStatusService housekeepingStatusService = new HousekeepingStatusServiceImpl(housekeepingStatusDao);

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
            if (action.equalsIgnoreCase("getHousekeepingStatusList")) {

                    List<HousekeepingStatus> housekeepingStatusList = housekeepingStatusService.getHousekeepingStatusList();
                    request.setAttribute("housekeepingStatusList", housekeepingStatusList);
                    address1 = "/WEB-INF/pages/housekeepingStatusList.jsp";
            } else if (action.equalsIgnoreCase("addHousekeepingStatus")) {
                address1 = "/views/newHousekeepingStatus.jsp";
            } else if (action.equalsIgnoreCase("addHousekeepingStatus1")) {
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int action1 = Integer.parseInt(request.getParameter("action1"));
                HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                housekeepingStatus.setName(name);
                housekeepingStatus.setDescription(description);
                housekeepingStatus.setAction(action1);
                boolean isAdded = housekeepingStatusService.addHousekeepingStatus(housekeepingStatus);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editHousekeepingStatus")) {
                Long housekeepingStatusId = Long.parseLong(request.getParameter("id"));
                HousekeepingStatus housekeepingStatus = housekeepingStatusService.getHousekeepingStatusById(housekeepingStatusId);
                request.setAttribute("housekeepingStatus", housekeepingStatus);
                address1 = "/WEB-INF/edit/editHousekeepingStatus.jsp";

            } else if (action.equalsIgnoreCase("updateHousekeepingStatus")) {
                Long housekeepingStatusId = Long.parseLong(request.getParameter("housekeepingStatusId"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int action1 = Integer.parseInt(request.getParameter("action1"));
                HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                housekeepingStatus.setName(name);
                housekeepingStatus.setDescription(description);
                housekeepingStatus.setAction(action1);
                housekeepingStatus.setId(housekeepingStatusId);
                boolean isUpdate = housekeepingStatusService.updateHousekeepingStatus(housekeepingStatus);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }
            else if (action.equalsIgnoreCase("deleteHousekeepingStatus")) {
                Long housekeepingStatusId = Long.parseLong(request.getParameter("housekeepingStatusId"));
                boolean isDelete = housekeepingStatusService.deleteHousekeepingStatus(housekeepingStatusId);
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
