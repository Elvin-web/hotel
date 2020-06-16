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
import java.util.List;

@WebServlet(name = "ServicesServlet", urlPatterns = "/ses")
public class ServicesServlet extends HttpServlet {
    ServicesDao servicesDao = new ServicesDaoImpl();
    ServicesService servicesService = new ServicesServiceImpl(servicesDao);
    Services_RoomTypeDao services_roomTypeDao = new Services_RoomTypeDaoImpl();
    Services_RoomTypeService services_roomTypeService = new Services_RoomTypeServiceImpl(services_roomTypeDao);
    RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
    RoomTypeService roomTypeService = new RoomTypeServiceImpl(roomTypeDao);
    PriceTypeDao priceTypeDao = new PriceTypeDaoImpl();
    PriceTypeService priceTypeService = new PriceTypeServiceImpl(priceTypeDao);
    Service_ReservationDao service_reservationDao = new Service_ReservationDaoImpl();
    Service_ReservationService service_reservationService = new Service_ReservationServiceImpl(service_reservationDao);
    PaymentDao paymentDao = new PaymentDaoImpl();
    PaymentService paymentService = new PaymentServiceImpl(paymentDao);
    TaxDao taxDao=new TaxDaoImpl();
    TaxService taxService=new TaxServiceImpl(taxDao);
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
            if (action.equalsIgnoreCase("getServicesList")) {
                List<Services> servicesList = servicesService.getServicesList();
                request.setAttribute("servicesList", servicesList);
                address1 = "/WEB-INF/pages/servicesList.jsp";

            }
            else if (action.equalsIgnoreCase("addServices")) {
                List<Services> servicesList = servicesService.getServicesList();
                List<PriceType> priceTypeList = priceTypeService.getPriceTypeList();
                request.setAttribute("priceTypeList", priceTypeList);
                request.setAttribute("servicesList", servicesList);
                address1 = "/WEB-INF/new/newServices.jsp";
            }else if (action.equalsIgnoreCase("addServices1")) {
                String name = request.getParameter("name");
                String priceType = request.getParameter("priceType");
                String price = request.getParameter("price");
                String description = request.getParameter("description");
                String action1 = request.getParameter("action1");
                Services services = new Services();
                services.setName(name);
                services.setAmount(Double.valueOf(price));
                services.setDescription(description);
                services.setAction(Integer.parseInt(action1));
                PriceType priceType1 = new PriceType();
                priceType1.setId(Long.parseLong(priceType));
                services.setPriceType(priceType1);
                boolean isAdded = servicesService.addServices(services);
                    response.setContentType("text/html");
                    if (isAdded) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }

            } else if (action.equalsIgnoreCase("editServices")) {
                List<Services> servicesList = servicesService.getServicesList();
                List<PriceType> priceTypeList = priceTypeService.getPriceTypeList();
                request.setAttribute("priceTypeList", priceTypeList);
                request.setAttribute("servicesList", servicesList);
                Long servicesId = Long.parseLong(request.getParameter("id"));
                Services services=servicesService.getServicesById(servicesId);
                request.setAttribute("services", services);
                address1 = "/WEB-INF/edit/editServices.jsp";

            } else if (action.equalsIgnoreCase("updateServices")) {
                Long servicesId = Long.parseLong(request.getParameter("servicesId"));
                String name = request.getParameter("name");
                String priceType = request.getParameter("priceType");
                String price = request.getParameter("price");
                String description = request.getParameter("description");
                String action1 = request.getParameter("action1");
                Services services = new Services();
                services.setName(name);
                services.setAmount(Double.valueOf(price));
                services.setDescription(description);
                services.setAction(Integer.parseInt(action1));
                PriceType priceType1 = new PriceType();
                priceType1.setId(Long.parseLong(priceType));
                services.setPriceType(priceType1);
               services.setId(servicesId);
                boolean isUpdate = servicesService.updateServices(services);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("addServiceReservation")) {
                Long reservationId = Long.parseLong(request.getParameter("reservationId"));
                Long serviceId = Long.parseLong(request.getParameter("serviceId"));
                Payment payment = paymentService.getPaymentByReservationId1(reservationId);
                Services services=servicesService.getServicesById(serviceId);
                Payment payment1 = new Payment();
                List<Tax> taxList=taxService.getTaxList();
                Double cem=0.0;
                for(Tax tax : taxList){
                    Double rate = tax.getRate();
                    cem+=rate;
                }

                payment1.setServicesCost(Math.abs(payment.getServicesCost()+services.getAmount()));
                payment1.setSum(Math.abs(services.getAmount()+payment.getSum()));
                payment1.setPending(Math.abs(payment.getPending()+services.getAmount()));
                payment1.setTaxCost(Math.abs((services.getAmount()+payment.getSum())/cem));
                payment1.setId(payment.getId());
                Reservation reservation = new Reservation();
                reservation.setId(reservationId);
                payment1.setReservation(reservation);
                boolean isUpdate = paymentService.updatePayment1(payment1);
                Service_Reservation service_reservation = new Service_Reservation();
                service_reservation.setReservation(reservation);
                Services services1 = new Services();
                services1.setId(serviceId);
                service_reservation.setServices(services1);
                boolean isAdded = service_reservationService.addService_Reservation(service_reservation);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }
            else if (action.equalsIgnoreCase("deleteServices")) {
                Long servicesId = Long.parseLong(request.getParameter("servicesId"));
                boolean isDelete = servicesService.deleteServices(servicesId);
                boolean isDelete1 = services_roomTypeService.deleteServices_RoomType(servicesId);
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
/*else if (action.equalsIgnoreCase("addServices")) {
                List<Services_RoomType> services_roomTypeList = services_roomTypeService.getServices_RoomTypeList();
                List<Services> servicesList = servicesService.getServicesList();
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                List<PriceType> priceTypeList = priceTypeService.getPriceTypeList();
                request.setAttribute("services_roomTypeList", services_roomTypeList);
                request.setAttribute("roomTypeList", roomTypeList);
                request.setAttribute("priceTypeList", priceTypeList);
                request.setAttribute("servicesList", servicesList);
                address1 = "/WEB-INF/new/newServices1.jsp";
            }*//*else if (action.equalsIgnoreCase("addServices1")) {
                String name = request.getParameter("name");
                String priceType = request.getParameter("priceType");
                String price = request.getParameter("price");
                String description = request.getParameter("description");
                String action1 = request.getParameter("action1");
                String[] roomType = request.getParameterValues("roomType[]");
                Services_RoomType services_roomType = new Services_RoomType();
                Services services = new Services();
                RoomType roomType1 = new RoomType();

                services.setName(name);
                services.setAmount(Double.valueOf(price));
                services.setDescription(description);
                services.setAction(Integer.parseInt(action1));
                PriceType priceType1 = new PriceType();
                priceType1.setId(Long.parseLong(priceType));
                services.setPriceType(priceType1);
                boolean isAdded = servicesService.addServices(services);
                for (String roomType2 : roomType) {
                    roomType1.setId(Long.parseLong(roomType2));
                    services_roomType.setRoomType(roomType1);
                    services_roomType.setServices(services);
                    boolean isAdded1 = services_roomTypeService.addServices_RoomType(services_roomType);
                    response.setContentType("text/html");
                    if (isAdded1) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                }
            }else if (action.equalsIgnoreCase("editServices")) {
                List<Services_RoomType> services_roomTypeList = services_roomTypeService.getServices_RoomTypeList();
                List<Services> servicesList = servicesService.getServicesList();
                List<RoomType> roomTypeList = roomTypeService.getRoomTypeList();
                List<PriceType> priceTypeList = priceTypeService.getPriceTypeList();
                request.setAttribute("services_roomTypeList", services_roomTypeList);
                request.setAttribute("roomTypeList", roomTypeList);
                request.setAttribute("priceTypeList", priceTypeList);
                request.setAttribute("servicesList", servicesList);
                Long servicesId = Long.parseLong(request.getParameter("id"));
                Services_RoomType services_roomType = services_roomTypeService.getServices_RoomTypeById(servicesId);
                Services services=servicesService.getServicesById(servicesId);
                request.setAttribute("services_roomType", services_roomType);
                request.setAttribute("services", services);
                address1 = "/WEB-INF/edit/editServices1.jsp";

            } else if (action.equalsIgnoreCase("updateServices")) {
                Long servicesId = Long.parseLong(request.getParameter("servicesId"));
                String name = request.getParameter("name");
                String priceType = request.getParameter("priceType");
                String price = request.getParameter("price");
                String description = request.getParameter("description");
                String action1 = request.getParameter("action1");
                String[] roomType = request.getParameterValues("roomType[]");
                Services_RoomType services_roomType = new Services_RoomType();
                RoomType roomType1 = new RoomType();
                for (String roomType2 : roomType) {
                    roomType1.setId(Long.parseLong(roomType2));
                }
                services_roomType.setRoomType(roomType1);
                Services services = new Services();
                services.setName(name);
                services.setAmount(Double.valueOf(price));
                services.setDescription(description);
                services.setAction(Integer.parseInt(action1));
                PriceType priceType1 = new PriceType();
                priceType1.setId(Long.parseLong(priceType));
                services.setPriceType(priceType1);
                services_roomType.setServices(services);
                services_roomType.setId(servicesId);
                boolean isUpdate = services_roomTypeService.updateServices_RoomType(services_roomType);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }*/