package az.elvin.hotel.web;

import az.elvin.hotel.dao.*;
import az.elvin.hotel.dao.impl.*;
import az.elvin.hotel.model.*;
import az.elvin.hotel.service.*;
import az.elvin.hotel.service.impl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "HousekeepingServlet", urlPatterns = "/hos")
public class HousekeepingServlet extends HttpServlet {
    HousekeepingDao housekeepingDao = new HousekeepingDaoImpl();
    HousekeepingService housekeepingService = new HousekeepingServiceImpl(housekeepingDao);
    HousekeepingStatusDao housekeepingStatusDao = new HousekeepingStatusDaoImpl();
    HousekeepingStatusService housekeepingStatusService = new HousekeepingStatusServiceImpl(housekeepingStatusDao);
    StaffDao staffDao = new StaffDaoImpl();
    StaffService staffService = new StaffServiceImpl(staffDao);
    DictionaryDao dictionaryDao = new DictionaryDaoImpl();
    DictionaryService dictionaryService = new DictionaryServiceImpl(dictionaryDao);
    RoomDao roomDao = new RoomDaoImpl();
    RoomService roomService = new RoomServiceImpl(roomDao);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

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
            if (action.equalsIgnoreCase("housekeepingList")) {
                Long roomId = Long.parseLong(request.getParameter("id"));
                List<Housekeeping> housekeepingList = housekeepingService.getHousekeepingListByRoomId1(roomId);
                request.setAttribute("housekeepingList", housekeepingList);
                request.setAttribute("roomId", roomId);
                //HttpSession session = request.getSession(true);
                //session.setAttribute("housekeepingList", housekeepingList);
                address1 = "/WEB-INF/pages/housekeepingList.jsp";
            } else if (action.equalsIgnoreCase("viewHousekeeping")) {
                Long housekeepingId = Long.parseLong(request.getParameter("id"));
                Housekeeping housekeeping = housekeepingService.getHousekeepingById(housekeepingId);
                request.setAttribute("housekeeping", housekeeping);
                address1 = "/WEB-INF/pages/viewHousekeeping.jsp";


            } else if (action.equalsIgnoreCase("editHousekeeping")) {
                List<HousekeepingStatus> housekeepingStatusList = housekeepingStatusService.getHousekeepingStatusList();
                request.setAttribute("housekeepingStatusList", housekeepingStatusList);
                List<Staff> staffList = staffService.getStaffList();
                request.setAttribute("staffList", staffList);
                List<Dictionary> dictionaryList = dictionaryService.getDictionaryList();
                request.setAttribute("dictionaryList", dictionaryList);
                List<Room> roomList = roomService.getRoomList();
                request.setAttribute("roomList", roomList);
                Long housekeepingId = Long.parseLong(request.getParameter("id"));
                Housekeeping housekeeping = housekeepingService.getHousekeepingById(housekeepingId);
                request.setAttribute("housekeeping", housekeeping);
                address1 = "/WEB-INF/edit/editHousekeeping.jsp";

            } else if (action.equalsIgnoreCase("addHousekeeping")) {
               Long roomId = Long.parseLong(request.getParameter("id"));
                System.out.println("roomId="+roomId);
                List<HousekeepingStatus> housekeepingStatusList = housekeepingStatusService.getHousekeepingStatusList();
                request.setAttribute("housekeepingStatusList", housekeepingStatusList);
                List<Staff> staffList = staffService.getStaffList();
                request.setAttribute("staffList", staffList);
                request.setAttribute("roomId", roomId);
                address1 = "/WEB-INF/new/newHousekeeping.jsp";
            } else if (action.equalsIgnoreCase("addHousekeeping1")) {
                Long roomId = Long.parseLong(request.getParameter("roomId"));
                System.out.println("roomId="+roomId);
                String remark = request.getParameter("remark");
                String cleanDate = request.getParameter("cleanDate");
                String housekeepingStatus = request.getParameter("housekeepingStatus");
                String staff = request.getParameter("staff");
                Housekeeping housekeeping1 = new Housekeeping();
                housekeeping1.setRemark(remark);
                housekeeping1.setCleanDate(df.parse(cleanDate));
                Room room=new Room();
                room.setId(roomId);
                housekeeping1.setRoom(room);
                Staff staff1 = new Staff();
                staff1.setId(Long.parseLong(staff));
                housekeeping1.setStaff(staff1);
                HousekeepingStatus housekeepingStatus1 = new HousekeepingStatus();
                housekeepingStatus1.setId(Long.parseLong(housekeepingStatus));
                housekeeping1.setHousekeepingStatus(housekeepingStatus1);
                System.out.println("housekeeping1="+housekeeping1);
                boolean isAdded = housekeepingService.addHousekeeping(housekeeping1);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateHousekeeping")) {
                Long housekeepingId = Long.parseLong(request.getParameter("housekeepingId"));
                String housekeepingStatus = request.getParameter("housekeepingStatus");
                String remark = request.getParameter("remark");
                String staff = request.getParameter("staff");
                String cleanDate = request.getParameter("cleanDate");
                String room = request.getParameter("room");
                String roomId = request.getParameter("roomId");
                Housekeeping housekeeping = new Housekeeping();
                HousekeepingStatus housekeepingStatus1 = new HousekeepingStatus();
                housekeepingStatus1.setId(Long.parseLong(housekeepingStatus));
                housekeeping.setHousekeepingStatus(housekeepingStatus1);
                housekeeping.setRemark(remark);
                Room room1=new Room();
                room1.setId(Long.parseLong(roomId));
                housekeeping.setRoom(room1);
                Staff staff1 = new Staff();
                staff1.setId(Long.parseLong(staff));
                housekeeping.setStaff(staff1);
               /* Dictionary dictionary1=new Dictionary();
                dictionary1.setId(Long.parseLong(dictionary));
                housekeeping.setDictionary(dictionary1);*/
                housekeeping.setCleanDate(df.parse(cleanDate));
                housekeeping.setId(housekeepingId);
                boolean isUpdate = housekeepingService.updateHousekeeping(housekeeping);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteHousekeeping")) {
                Long housekeepingId = Long.parseLong(request.getParameter("housekeepingId"));
                boolean isDelete = housekeepingService.deleteHousekeeping(housekeepingId);
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
