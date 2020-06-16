package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PositionDao;
import az.elvin.hotel.dao.impl.PositionDaoImpl;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.model.Position;
import az.elvin.hotel.service.PositionService;
import az.elvin.hotel.service.impl.PositionServiceImpl;

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

@WebServlet(name = "PositionServlet", urlPatterns = "/ps")

public class PositionServlet extends HttpServlet {
    PositionDao positionDao = new PositionDaoImpl();
    PositionService positionService = new PositionServiceImpl(positionDao);

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
            if (action.equalsIgnoreCase("getPositionList")) {
                    List<Position> positionList = positionService.getPositionList();
                    request.setAttribute("positionList", positionList);
                    address1 = "/WEB-INF/pages/positionList.jsp";

            } else if (action.equalsIgnoreCase("addPosition")) {
                address1 = "/views/newPosition.jsp";
            } else if (action.equalsIgnoreCase("addPosition1")) {
                String name = request.getParameter("name");
                Position position = new Position();
                position.setPositionValue(name);
                boolean isAdded = positionService.addPosition(position);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("editPosition")) {
                Long positionId = Long.parseLong(request.getParameter("id"));
                Position position = positionService.getPositionById(positionId);
                request.setAttribute("position", position);
                address1 = "/WEB-INF/edit/editPosition.jsp";

            } else if (action.equalsIgnoreCase("updatePosition")) {
                Long positionId = Long.parseLong(request.getParameter("positionId"));
                String name = request.getParameter("name");
                Position position = new Position();
                position.setPositionValue(name);
                position.setId(positionId);
                boolean isUpdate = positionService.updatePosition(position);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }
            else if (action.equalsIgnoreCase("deletePosition")) {
                Long positionId = Long.parseLong(request.getParameter("positionId"));
                boolean isDelete = positionService.deletePosition(positionId);
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
