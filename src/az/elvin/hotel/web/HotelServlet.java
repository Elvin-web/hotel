package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.HotelDao;
import az.elvin.hotel.dao.StarDao;
import az.elvin.hotel.dao.impl.HotelDaoImpl;
import az.elvin.hotel.dao.impl.StarDaoImpl;
import az.elvin.hotel.model.Amenities;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.model.Hotel;
import az.elvin.hotel.model.Star;
import az.elvin.hotel.service.HotelService;
import az.elvin.hotel.service.StarService;
import az.elvin.hotel.service.impl.HotelServiceImpl;
import az.elvin.hotel.service.impl.StarServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "HotelServlet", urlPatterns = "/hs")
public class HotelServlet extends HttpServlet {
    StarDao starDao = new StarDaoImpl();
    StarService starService = new StarServiceImpl(starDao);
    HotelDao hotelDao = new HotelDaoImpl();
    HotelService hotelService = new HotelServiceImpl(hotelDao);
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
            if (action.equalsIgnoreCase("getHotelList")) {

                List<Hotel> hotelList = hotelService.getHotelList();
                List<Star> starList = starService.getStarList();
                request.setAttribute("hotelList", hotelList);
                request.setAttribute("starList", starList);
                address1 = "/WEB-INF/pages/hotelList.jsp";

            } else if (action.equalsIgnoreCase("addHotel")) {
                List<Star> starList = starService.getStarList();
                request.setAttribute("starList", starList);
                address1 = "/WEB-INF/new/newHotel.jsp";
            } else if (action.equalsIgnoreCase("addHotel1")) {
                Hotel hotel = new Hotel();
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
                            hotel.setLogo(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                hotel.setHotelName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("address")) {
                                String address = item.getString();
                                hotel.setHotelAddress(address);
                            } else if (item.getFieldName().equalsIgnoreCase("email")) {
                                String email = item.getString();
                                hotel.setHotelEmail(email);
                            } else if (item.getFieldName().equalsIgnoreCase("phone")) {
                                String phone = item.getString();
                                hotel.setHotelPhone(phone);
                            } else if (item.getFieldName().equalsIgnoreCase("star")) {
                                String star = item.getString();
                                Star star1 = new Star();
                                star1.setId(Long.parseLong(star));
                                hotel.setStar(star1);
                            }
                        }

                    }
                    boolean isAdded = hotelService.addHotel(hotel);
                    address1 = "/WEB-INF/pages/hotelList.jsp";
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


            } else if (action.equalsIgnoreCase("editHotel")) {
                Long hotelId = Long.parseLong(request.getParameter("id"));
                Hotel hotel = hotelService.getHotelById(hotelId);
                String filePath = hotel.getLogo().substring(hotel.getLogo().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath;
                request.setAttribute("filePath2", filePath2);
                List<Star> starList = starService.getStarList();
                request.setAttribute("starList", starList);
                request.setAttribute("hotel", hotel);
                address1 = "/WEB-INF/edit/editHotel.jsp";

            } else if (action.equalsIgnoreCase("updateHotel")) {
                Hotel hotel = new Hotel();
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
                            hotel.setLogo(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                hotel.setHotelName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("address")) {
                                String address = item.getString();
                                hotel.setHotelAddress(address);
                            } else if (item.getFieldName().equalsIgnoreCase("email")) {
                                String email = item.getString();
                                hotel.setHotelEmail(email);
                            } else if (item.getFieldName().equalsIgnoreCase("phone")) {
                                String phone = item.getString();
                                hotel.setHotelPhone(phone);
                            } else if (item.getFieldName().equalsIgnoreCase("star")) {
                                String star = item.getString();
                                Star star1 = new Star();
                                star1.setId(Long.parseLong(star));
                                hotel.setStar(star1);
                            } else if (item.getFieldName().equalsIgnoreCase("id")) {
                                String hotelId = item.getString();
                                hotel.setId(Long.parseLong(hotelId));
                            }
                        }
                    }
                    boolean isUpdate = hotelService.updateHotel(hotel);
                    address1 = "/WEB-INF/pages/hotelList.jsp";
                    response.setContentType("text/html");
                    if (isUpdate) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                } catch (Exception ex) {
                    request.setAttribute("message", "There was an error:" + ex.getMessage());
                    ex.printStackTrace();
                }


            } else if (action.equalsIgnoreCase("deleteHotel")) {
                Long hotelId = Long.parseLong(request.getParameter("hotelId"));
                boolean isDelete = hotelService.deleteHotel(hotelId);
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
