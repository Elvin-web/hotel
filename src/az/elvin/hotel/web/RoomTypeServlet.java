package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.DictionaryDao;
import az.elvin.hotel.dao.RoomTypeDao;
import az.elvin.hotel.dao.impl.DictionaryDaoImpl;
import az.elvin.hotel.dao.impl.RoomTypeDaoImpl;
import az.elvin.hotel.model.*;
import az.elvin.hotel.service.DictionaryService;
import az.elvin.hotel.service.RoomTypeService;
import az.elvin.hotel.service.impl.DictionaryServiceImpl;
import az.elvin.hotel.service.impl.RoomTypeServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "RoomTypeServlet", urlPatterns = "/rts")
public class RoomTypeServlet extends HttpServlet {
    RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
    RoomTypeService roomTypeService = new RoomTypeServiceImpl(roomTypeDao);
    DictionaryDao dictionaryDao = new DictionaryDaoImpl();
    DictionaryService dictionaryService = new DictionaryServiceImpl(dictionaryDao);
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
            if (action.equalsIgnoreCase("getRoomTypeList")) {

                List<RoomType> roomTypeList = roomTypeDao.getRoomTypeList();

                request.setAttribute("roomTypeList", roomTypeList);
                address1 = "/WEB-INF/pages/roomTypeList.jsp";

            } else if (action.equalsIgnoreCase("addRoomType")) {
                address1 = "/WEB-INF/new/newRoomType.jsp";
            } else if (action.equalsIgnoreCase("addRoomType1")) {
                RoomType roomType = new RoomType();
                RoomStructure roomStructure = new RoomStructure();
                Price price = new Price();
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
                            // newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                            // filePath = uploadPath + File.separator + newFileName;
                            System.out.println("filePath=" + filePath);
                            //  roomType.setImage(filePath);
                            //File storeFile = new File(filePath);
                            //item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                roomType.setRoomType(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("slug")) {
                                String slug = item.getString();
                                roomType.setSlug(slug);
                                System.out.println("slug=" + slug);
                            } else if (item.getFieldName().equalsIgnoreCase("description")) {
                                String description = item.getString();
                                roomType.setDescription(description);
                                System.out.println("description=" + description);
                            } else if (item.getFieldName().equalsIgnoreCase("shortCode")) {
                                String shortCode = item.getString();
                                roomType.setShortCode(shortCode);
                            } else if (item.getFieldName().equalsIgnoreCase("baseOccupancy")) {
                                String baseOccupancy = item.getString();
                                roomStructure.setBaseOccupancy(Integer.parseInt(baseOccupancy));
                                roomType.setRoomStructure(roomStructure);
                                System.out.println("baseOccupancy=" + baseOccupancy);
                            } else if (item.getFieldName().equalsIgnoreCase("higherOccupancy")) {
                                String higherOccupancy = item.getString();
                                roomStructure.setHigherOccupancy(Integer.parseInt(higherOccupancy));
                                roomType.setRoomStructure(roomStructure);
                                System.out.println("higherOccupancy=" + higherOccupancy);
                            } else if (item.getFieldName().equalsIgnoreCase("kidsOccupancy")) {
                                String kidsOccupancy = item.getString();
                                roomStructure.setKidsOccupancy(Integer.parseInt(kidsOccupancy));
                                roomType.setRoomStructure(roomStructure);
                                System.out.println("kidsOccupancy=" + kidsOccupancy);
                            } else if (item.getFieldName().equalsIgnoreCase("extraBed")) {
                                String extraBed = item.getString();
                                roomStructure.setExtaBed(Integer.parseInt(extraBed));
                                roomType.setRoomStructure(roomStructure);
                            } else if (item.getFieldName().equalsIgnoreCase("basePice")) {

                                String basePice = item.getString();
                                price.setBasePrice(Double.parseDouble(basePice));
                                roomType.setPrice(price);
                            } else if (item.getFieldName().equalsIgnoreCase("extraBedPrice")) {
                                String extraBedPrice = item.getString();
                                price.setExtraBedPrice(Double.parseDouble(extraBedPrice));
                                roomType.setPrice(price);
                            }
                        }

                    }
                    boolean isAdded = roomTypeService.addRoomType(roomType);
                    address1 = "/WEB-INF/pages/roomTypeList.jsp";
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

            } else if (action.equalsIgnoreCase("editRoomType")) {
                Long roomTypeId = Long.parseLong(request.getParameter("id"));
                RoomType roomType = roomTypeService.getRoomTypeById(roomTypeId);
                String filePath = roomType.getImage().substring(roomType.getImage().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath;
                request.setAttribute("filePath2", filePath2);
                request.setAttribute("roomType", roomType);
                address1 = "/WEB-INF/edit/editRoomType.jsp";

            } else if (action.equalsIgnoreCase("updateRoomType")) {
                RoomType roomType = new RoomType();
                RoomStructure roomStructure = new RoomStructure();
                Price price = new Price();
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
                            roomType.setImage(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                roomType.setRoomType(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("slug")) {
                                String slug = item.getString();
                                roomType.setSlug(slug);
                            } else if (item.getFieldName().equalsIgnoreCase("description")) {
                                String description = item.getString();
                                roomType.setDescription(description);
                            } else if (item.getFieldName().equalsIgnoreCase("shortCode")) {
                                String shortCode = item.getString();
                                roomType.setShortCode(shortCode);
                            } else if (item.getFieldName().equalsIgnoreCase("baseOccupancy")) {
                                String baseOccupancy = item.getString();

                                roomStructure.setBaseOccupancy(Integer.parseInt(baseOccupancy));
                                roomType.setRoomStructure(roomStructure);
                            } else if (item.getFieldName().equalsIgnoreCase("higherOccupancy")) {

                                String higherOccupancy = item.getString();
                                roomStructure.setHigherOccupancy(Integer.parseInt(higherOccupancy));
                                roomType.setRoomStructure(roomStructure);
                            } else if (item.getFieldName().equalsIgnoreCase("kidsOccupancy")) {

                                String kidsOccupancy = item.getString();
                                roomStructure.setKidsOccupancy(Integer.parseInt(kidsOccupancy));
                                roomType.setRoomStructure(roomStructure);
                            } else if (item.getFieldName().equalsIgnoreCase("extraBed")) {

                                String extraBed = item.getString();
                                roomStructure.setExtaBed(Integer.parseInt(extraBed));
                                roomType.setRoomStructure(roomStructure);
                            } else if (item.getFieldName().equalsIgnoreCase("basePice")) {

                                String basePice = item.getString();
                                price.setBasePrice(Double.parseDouble(basePice));
                                roomType.setPrice(price);
                            } else if (item.getFieldName().equalsIgnoreCase("extraBedPrice")) {

                                String extraBedPrice = item.getString();
                                price.setExtraBedPrice(Double.parseDouble(extraBedPrice));
                                roomType.setPrice(price);
                            } else if (item.getFieldName().equalsIgnoreCase("id")) {
                                String roomTypeId = item.getString();
                                roomType.setId(Long.parseLong(roomTypeId));

                            }
                        }

                    }
                    boolean isUpdate = roomTypeService.updateRoomType(roomType);
                    address1 = "/WEB-INF/pages/roomTypeList.jsp";
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


            } else if (action.equalsIgnoreCase("deleteRoomType")) {
                Long roomTypeId = Long.parseLong(request.getParameter("roomTypeId"));
                boolean isDelete = roomTypeService.deleteRoomType(roomTypeId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }

            } else if (action.equalsIgnoreCase("viewRoomType")) {
                Long roomTypeId = Long.parseLong(request.getParameter("id"));
                RoomType roomType = roomTypeService.getRoomTypeById(roomTypeId);
                String filePath = roomType.getImage().substring(roomType.getImage().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath;
                request.setAttribute("filePath2", filePath2);
                List<Dictionary> dictionaryList = dictionaryService.getDictionaryList();
                request.setAttribute("dictionaryList", dictionaryList);
                request.setAttribute("roomType", roomType);
                address1 = "/WEB-INF/pages/viewRoomType.jsp";
            }



          /* else if (action.equalsIgnoreCase("addRoomType1")) {
              //  String fileName = request.getParameter("fileName");
                RoomType roomType = new RoomType();
                RoomStructure roomStructure = new RoomStructure();
                Price price = new Price();
                InputStream inputStream = null;
                Part filePart = request.getPart("fileName");
                if (filePart != null) {
                    // prints out some information for debugging
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());

                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                    roomType.setImage((Blob) inputStream);
                }
                String extraBed = request.getParameter("extraBed");
                String roomType1 = request.getParameter("name");
                String slug = request.getParameter("slug");
                String shortCode = request.getParameter("shortCode");
                String description = request.getParameter("description");
                String baseOccupancy = request.getParameter("baseOccupancy");
                String higherOccupancy = request.getParameter("higherOccupancy");
                String kidsOccupancy = request.getParameter("kidsOccupancy");
                String basePrice = request.getParameter("basePice");
                String extraBedPrice = request.getParameter("extraBedPrice");
                roomStructure.setExtaBed(Integer.parseInt(extraBed));
                roomStructure.setKidsOccupancy(Integer.parseInt(kidsOccupancy));
                roomStructure.setBaseOccupancy(Integer.parseInt(baseOccupancy));
                roomStructure.setHigherOccupancy(Integer.parseInt(higherOccupancy));
                roomType.setRoomStructure(roomStructure);
                price.setExtraBedPrice(Double.valueOf(extraBedPrice));
                price.setBasePrice(Double.valueOf(basePrice));
                roomType.setRoomType(roomType1);
                roomType.setSlug(slug);
                roomType.setShortCode(shortCode);
                roomType.setDescription(description);
                boolean isAdded = roomTypeService.addRoomType(roomType);
                address1 = "/WEB-INF/pages/roomTypeList.jsp";
                response.setContentType("text/html");
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
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
