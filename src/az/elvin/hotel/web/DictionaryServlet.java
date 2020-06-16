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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "DictionaryServlet", urlPatterns = "/ds")
public class DictionaryServlet extends HttpServlet {
    AmenitiesDao amenitiesDao = new AmenitiesDaoImpl();
    AmenitiesService amenitiesService = new AmenitiesServiceImpl(amenitiesDao);
    DictionaryDao dictionaryDao = new DictionaryDaoImpl();
    DictionaryService dictionaryService = new DictionaryServiceImpl(dictionaryDao);
    RoomDao roomDao = new RoomDaoImpl();
    RoomService roomService = new RoomServiceImpl(roomDao);
    FloorDao floorDao = new FloorDaoImpl();
    FloorService floorService = new FloorServiceImpl(floorDao);
    RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
    RoomTypeService roomTypeService = new RoomTypeServiceImpl(roomTypeDao);
    ReservationDao reservationDao = new ReservationDaoImpl();
    ReservationService reservationService = new ReservationServiceImpl(reservationDao);

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
            if (action.equalsIgnoreCase("getDictionaryList")) {
            /*   List<Dictionary> dictionaryList = dictionaryService.getDictionaryList();
                request.setAttribute("dictionaryList", dictionaryList);*/
                List<Room> roomList = roomService.getRoomList();
                request.setAttribute("roomList", roomList);
                Long roomAcount = roomService.roomAcount();
                request.setAttribute("roomAcount", roomAcount);
                Long floorAcount = floorService.floorAcount();
                request.setAttribute("floorAcount", floorAcount);
                Long roomTypeAcount = roomTypeService.roomTypeAcount();
                request.setAttribute("roomTypeAcount", roomTypeAcount);
                Long reservationTodayAcount = reservationService.reservationTodayAcount();
                request.setAttribute("reservationTodayAcount", reservationTodayAcount);
                address1 = "/WEB-INF/pages/dictionaryList.jsp";
            } else if (action.equalsIgnoreCase("addDictionary")) {
                List<Room> roomList = roomService.getRoomList();
                List<Floor> floorList = floorService.getFloorList();
                List<Amenities> amenitiesList = amenitiesService.getAmenitiesList();
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                request.setAttribute("roomList", roomList);
                request.setAttribute("floorList", floorList);
                request.setAttribute("amenitiesList", amenitiesList);
                request.setAttribute("roomTypeList", roomTypeList);
                address1 = "/WEB-INF/new/newDictionary.jsp";
            } else if (action.equalsIgnoreCase("addDictionary1")) {
                String room = request.getParameter("room");
                String floor = request.getParameter("floor");
                String roomType = request.getParameter("roomType");
                String[] amenities = request.getParameterValues("amenities[]");
                String[] amenitiesUnselect = request.getParameterValues("amenitiesUnselect[]");
                Dictionary dictionary = new Dictionary();
                Amenities amenities1 = new Amenities();
                Room room1 = new Room();
                room1.setRoomNumber(Long.parseLong(room));
                Floor floor1 = new Floor();
                floor1.setId(Long.parseLong(floor));
                room1.setFloor(floor1);
                RoomType roomType1 = new RoomType();
                roomType1.setId(Long.parseLong(roomType));
                room1.setRoomType(roomType1);
                boolean isAdded = roomService.addRoom(room1);
                List<Amenities> amenitiesList = amenitiesService.getAmenitiesList();
                for (String amenities2 : amenities) {//1 //2 //3
                    Long amentiesId = Long.parseLong(amenities2);
                    amenities1.setId(amentiesId);
                    dictionary.setAmenities(amenities1);
                    dictionary.setRoom(room1);
                    dictionary.setActive(1);
                    boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                    response.setContentType("text/html");
                    address1 = "/WEB-INF/pages/dictionaryList.jsp";
                    if (isAdded1) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                }
                if (amenitiesUnselect != null) {
                    for (String amenities3 : amenitiesUnselect) {
                        Long amentiesId = Long.parseLong(amenities3);
                        amenities1.setId(amentiesId);
                        dictionary.setAmenities(amenities1);
                        dictionary.setRoom(room1);
                        dictionary.setActive(0);
                        boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                        response.setContentType("text/html");
                        address1 = "/WEB-INF/pages/dictionaryList.jsp";
                        if (isAdded1) {
                            pw.write("success");
                        } else {
                            pw.write("fail");
                        }
                    }

                }
                // if ((amenitiesList.size() - amenities.length) == 0) {
                     /*   for (Amenities amenties3 : amenitiesList) {//1 //2 //3
                            Long amenties3Id = amenties3.getId();

                            if ((!amenties3Id.equals(amentiesId)) && (amentiesId>amenties3Id)) {
                                amenities1.setId(amentiesId);
                                dictionary.setAmenities(amenities1);
                                dictionary.setRoom(room1);
                                dictionary.setActive(0);
                               boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                                response.setContentType("text/html");
                                address1 = "/WEB-INF/pages/dictionaryList.jsp";
                                if (isAdded1) {
                                    pw.write("success");
                                } else {
                                    pw.write("fail");
                                }
                            }
                            if ((amenties3Id.equals(amentiesId))) {
                                amenities1.setId(amentiesId);
                                dictionary.setAmenities(amenities1);
                                dictionary.setRoom(room1);
                                dictionary.setActive(1);
                               boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                                response.setContentType("text/html");
                                address1 = "/WEB-INF/pages/dictionaryList.jsp";
                                if (isAdded1) {
                                    pw.write("success");
                                } else {
                                    pw.write("fail");
                                }
                            }
                            boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                            response.setContentType("text/html");
                            address1 = "/WEB-INF/pages/dictionaryList.jsp";
                            if (isAdded1) {
                                pw.write("success");
                            } else {
                                pw.write("fail");
                            }
                        }*/

                /* }*/


            } else if (action.equalsIgnoreCase("updateDictionary")) {
                String roomId = request.getParameter("roomId");
                // System.out.println("roomId=" + roomId);
                String room = request.getParameter("room");
                //System.out.println("room=" + room);
                String floor = request.getParameter("floor");
                //System.out.println("floor=" + floor);
                String roomType = request.getParameter("roomType");
                // System.out.println("roomType=" + roomType);
                String[] amenities = request.getParameterValues("amenities[]");
                String[] amenitiesUnselect = request.getParameterValues("amenitiesUnselect[]");
                Dictionary dictionary = new Dictionary();
                Room room1 = new Room();
                Amenities amenities1 = new Amenities();
                room1.setRoomNumber(Long.parseLong(room));
                Floor floor1 = new Floor();
                floor1.setId(Long.parseLong(floor));
                room1.setFloor(floor1);
                RoomType roomType1 = new RoomType();
                roomType1.setId(Long.parseLong(roomType));
                room1.setRoomType(roomType1);
                room1.setId(Long.parseLong(roomId));
                boolean isUpdate = roomService.updateRoom(room1);
                List<Amenities> amenitiesList = amenitiesService.getAmenitiesList();
                List<Dictionary> dictionaryList = dictionaryService.getDictionaryByRoomId(Long.parseLong(roomId));
                for (String amenities2 : amenities) {
                    Long amentiesId = Long.parseLong(amenities2);
                    Dictionary dictionary1 = dictionaryService.getDictionaryByAmenitiesId(amentiesId, Long.parseLong(roomId));
                    if (dictionary1 != null) {
                        Long dictionaryId = dictionary1.getId();
                        amenities1.setId(amentiesId);
                        dictionary.setAmenities(amenities1);
                        dictionary.setRoom(room1);
                        dictionary.setActive(1);
                        dictionary.setId(dictionaryId);
                        System.out.println("dictionary13=" + dictionary);
                        boolean isUpdate1 = dictionaryService.updateDictionary(dictionary);
                        response.setContentType("text/html");
                        address1 = "/WEB-INF/pages/dictionaryList.jsp";
                        if (isUpdate1) {
                            pw.write("success");
                        } else {
                            pw.write("fail");
                        }
                    }
                    if (dictionary1 == null) {
                        amenities1.setId(amentiesId);
                        dictionary.setAmenities(amenities1);
                        room1.setId(Long.parseLong(roomId));
                        dictionary.setRoom(room1);
                        dictionary.setActive(1);
                        System.out.println("dictionary14=" + dictionary);
                        boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                        response.setContentType("text/html");
                        address1 = "/WEB-INF/pages/dictionaryList.jsp";
                        if (isAdded1) {
                            pw.write("success");
                        } else {
                            pw.write("fail");
                        }
                    }


                }
                if (amenitiesUnselect != null) {
                    for (String amenities3 : amenitiesUnselect) {
                        Long amentiesId = Long.parseLong(amenities3);
                        Dictionary dictionary3 = dictionaryService.getDictionaryByAmenitiesId(amentiesId, Long.parseLong(roomId));
                        if (dictionary3 != null) {
                            Long dictionaryId = dictionary3.getId();
                            amenities1.setId(amentiesId);
                            dictionary.setAmenities(amenities1);
                            dictionary.setRoom(room1);
                            dictionary.setActive(0);
                            dictionary.setId(dictionaryId);
                            boolean isUpdate1 = dictionaryService.updateDictionary(dictionary);
                            response.setContentType("text/html");
                            address1 = "/WEB-INF/pages/dictionaryList.jsp";
                            if (isUpdate1) {
                                pw.write("success");
                            } else {
                                pw.write("fail");
                            }
                        }
                        if (dictionary3 == null) {
                            amenities1.setId(amentiesId);
                            dictionary.setAmenities(amenities1);
                            room1.setId(Long.parseLong(roomId));
                            dictionary.setRoom(room1);
                            dictionary.setActive(0);
                            boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                            response.setContentType("text/html");
                            address1 = "/WEB-INF/pages/dictionaryList.jsp";
                            if (isAdded1) {
                                pw.write("success");
                            } else {
                                pw.write("fail");
                            }
                        }

                    }

                }
                   /*
                    if (dictionary1 != null) {
                        for (Dictionary dictionary11 : dictionaryList) {
                            Long amenities5 = dictionary11.getAmenities().getId();
                            Long dictionary5 = dictionary11.getId();
                            if (amentiesId.equals(amenities5)) {
                                Long amenties1Id = dictionary1.getAmenities().getId();
                                System.out.println("amenties1Id=" + amenties1Id);
                                Long dictionaryId = dictionary1.getId();
                                System.out.println("dictionaryId=" + dictionaryId);
                                Long roomId1 = dictionary1.getRoom().getId();
                                System.out.println("roomId1=" + roomId1);

                            }
                            if (!amenities5.equals(amentiesId)) {
                                amenities1.setId(amenities5);
                                dictionary.setAmenities(amenities1);
                                dictionary.setRoom(room1);
                                dictionary.setActive(0);
                                // dictionary.setActive(1);
                                dictionary.setId(dictionary5);
                                System.out.println("dictionary11=" + dictionary);
                                boolean isUpdate1 = dictionaryService.updateDictionary(dictionary);
                                response.setContentType("text/html");
                                address1 = "/WEB-INF/pages/dictionaryList.jsp";
                                if (isUpdate1) {
                                    pw.write("success");
                                } else {
                                    pw.write("fail");
                                }
                            }
                            if (!amentiesId.equals(amenities5)) {
                                amenities1.setId(amentiesId);
                                dictionary.setAmenities(amenities1);
                                dictionary.setRoom(room1);
                                dictionary.setActive(1);
                                dictionary.setId(dictionary5);
                                System.out.println("dictionary12=" + dictionary);
                                boolean isUpdate1 = dictionaryService.updateDictionary(dictionary);
                                response.setContentType("text/html");
                                address1 = "/WEB-INF/pages/dictionaryList.jsp";
                                if (isUpdate1) {
                                    pw.write("success");
                                } else {
                                    pw.write("fail");
                                }
                            }

                        }
                    }

                }*/
            } else if (action.equalsIgnoreCase("editDictionary")) {
                List<Floor> floorList = floorService.getFloorList();
                List<Amenities> amenitiesList = amenitiesService.getAmenitiesList();
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                request.setAttribute("floorList", floorList);
                request.setAttribute("amenitiesList", amenitiesList);
                request.setAttribute("roomTypeList", roomTypeList);

                Long roomId = Long.parseLong(request.getParameter("id"));
                List<Dictionary> dictionaryList = dictionaryService.getDictionaryByRoomId(roomId);
                request.setAttribute("dictionaryList", dictionaryList);
                Room room = roomService.getRoomById(roomId);
                request.setAttribute("room", room);
                address1 = "/WEB-INF/edit/editDictionary.jsp";
            } else if (action.equalsIgnoreCase("viewDictionary")) {
                Long roomId = Long.parseLong(request.getParameter("id"));
                Room room = roomService.getRoomById(roomId);
                request.setAttribute("room", room);
                List<Dictionary> dictionaryList = dictionaryService.getDictionaryByRoomId(roomId);
                request.setAttribute("dictionaryList", dictionaryList);
                address1 = "/WEB-INF/pages/viewDictionary.jsp";
            } else if (action.equalsIgnoreCase("deleteDictionary")) {
                Long dictionaryId = Long.parseLong(request.getParameter("dictionaryId"));
                //boolean isDelete = dictionaryService.deleteDictionary(dictionaryId);
                boolean isDelete = roomService.deleteRoom(dictionaryId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }

            }/*else if (action.equalsIgnoreCase("addDictionary1")) {
                String room = request.getParameter("room");
                String floor = request.getParameter("floor");
                String roomType = request.getParameter("roomType");
                String[] amenities = request.getParameterValues("amenities[]");
                Dictionary dictionary = new Dictionary();
                Amenities amenities1 = new Amenities();
                Room room1 = new Room();
                room1.setRoomNumber(Long.parseLong(room));
                Floor floor1 = new Floor();
                floor1.setId(Long.parseLong(floor));
                room1.setFloor(floor1);
                RoomType roomType1 = new RoomType();
                roomType1.setId(Long.parseLong(roomType));
                room1.setRoomType(roomType1);
                boolean isAdded = roomService.addRoom(room1);
                for (String amenities2 : amenities) {
                    amenities1.setId(Long.parseLong(amenities2));
                    dictionary.setAmenities(amenities1);
                    dictionary.setRoom(room1);
                    boolean isAdded1 = dictionaryService.addDictionary(dictionary);
                    response.setContentType("text/html");
                    address1 = "/WEB-INF/pages/dictionaryList.jsp";
                    if (isAdded1) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                }


            }*/
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        if (address1 != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address1);
            dispatcher.forward(request, response);
        }
    }
}
