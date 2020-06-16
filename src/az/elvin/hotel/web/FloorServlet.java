package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.FloorDao;
import az.elvin.hotel.dao.impl.FloorDaoImpl;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.model.Floor;
import az.elvin.hotel.service.FloorService;
import az.elvin.hotel.service.impl.FloorServiceImpl;

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

@WebServlet(name = "FloorServlet", urlPatterns = "/fs")
public class FloorServlet extends HttpServlet {
    FloorDao floorDao = new FloorDaoImpl();
    FloorService floorService = new FloorServiceImpl(floorDao);
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
             if (action.equalsIgnoreCase("getFloorList")) {
                    List<Floor> floorList = floorService.getFloorList();
                    request.setAttribute("floorList", floorList);
                    address1 = "/WEB-INF/pages/floorList.jsp";

            } else if (action.equalsIgnoreCase("addFloor")) {
                address1 = "/views/newFloor.jsp";
            } else if (action.equalsIgnoreCase("addFloor1")) {
               String name = request.getParameter("name");
                String floorNumber = request.getParameter("floorNumber");
                String description = request.getParameter("description");
                int action1 = Integer.parseInt(request.getParameter("action1"));
                Floor floor = new Floor();
                floor.setName(name);
                floor.setFloorNumber(floorNumber);
                floor.setDescription(description);
                floor.setAction(action1);
                boolean isAdded = floorService.addFloor(floor);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editFloor")) {
                Long floorId = Long.parseLong(request.getParameter("id"));
                Floor floor = floorService.getFloorById(floorId);
                request.setAttribute("floor", floor);
                address1 = "/WEB-INF/edit/editFloor.jsp";

            } else if (action.equalsIgnoreCase("updateFloor")) {
                 Long floorId = Long.parseLong(request.getParameter("floorId"));
                String name = request.getParameter("name");
                String floorNumber = request.getParameter("floorNumber");
                String description = request.getParameter("description");
                int action1 = Integer.parseInt(request.getParameter("action1"));
                Floor floor = new Floor();
                floor.setName(name);
                floor.setFloorNumber(floorNumber);
                floor.setDescription(description);
                floor.setAction(action1);
                floor.setId(floorId);
                boolean isUpdate = floorService.updateFloor(floor);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }else if (action.equalsIgnoreCase("deleteFloor")) {
                 Long floorId = Long.parseLong(request.getParameter("floorId"));
                 boolean isDelete = floorService.deleteFloor(floorId);
                 response.setContentType("text/html");
                 if (isDelete) {
                     pw.write("success");
                 } else {
                     pw.write("fail");
                 }

             } else if (action.equalsIgnoreCase("viewFloor")) {
                 Long floorId = Long.parseLong(request.getParameter("id"));
                 Floor floor = floorService.getFloorById(floorId);
                 request.setAttribute("floor", floor);
                 address1 = "/WEB-INF/pages/viewFloor.jsp";

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
