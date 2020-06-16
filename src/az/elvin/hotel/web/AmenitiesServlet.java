package az.elvin.hotel.web;

import az.elvin.hotel.dao.AmenitiesDao;
import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.impl.AmenitiesDaoImpl;
import az.elvin.hotel.model.*;
import az.elvin.hotel.service.AmenitiesService;
import az.elvin.hotel.service.impl.AmenitiesServiceImpl;
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

@WebServlet(name = "Servlet", urlPatterns = "/as")
public class AmenitiesServlet extends HttpServlet {
    AmenitiesDao amenitiesDao = new AmenitiesDaoImpl();
    AmenitiesService amenitiesService = new AmenitiesServiceImpl(amenitiesDao);
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


            if (action.equalsIgnoreCase("getAmenitiesList")) {
                List<Amenities> amenitiesList = amenitiesService.getAmenitiesList();
                request.setAttribute("amenitiesList", amenitiesList);
                address1 = "/WEB-INF/pages/amenitiesList.jsp";

            } else if (action.equalsIgnoreCase("addAmenities")) {
                address1 = "/views/newAmenities.jsp";
            } else if (action.equalsIgnoreCase("addAmenities1")) {
                Amenities amenities = new Amenities();
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
                        if ((!item.isFormField()) && (fileName != null)) {
                            fileName = new File(item.getName()).getName();
                            newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                            filePath = uploadPath + File.separator + newFileName;
                            System.out.println("filePath=" + filePath);
                            amenities.setImage(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                amenities.setName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("action1")) {
                                String action1 = item.getString();
                                amenities.setAction(Integer.parseInt(action1));
                            } else if (item.getFieldName().equalsIgnoreCase("description")) {
                                String description = item.getString();
                                amenities.setDescription(description);
                            }
                        }

                    }
                    boolean isAdded = amenitiesService.addAmenities(amenities);
                    address1 = "/WEB-INF/pages/amenitiesList.jsp";
                    response.setContentType("text/html");
                    if (isAdded) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
                  /*  request.setAttribute("imagePath2", UPLOAD_DIRECTORY + File.separator + newFileName);
                    request.setAttribute("imagePath", filePath);
                    request.setAttribute("message", "Upload has been done successfully");*/
                } catch (Exception ex) {
                    request.setAttribute("message", "There was an error:" + ex.getMessage());
                    ex.printStackTrace();
                }


            } else if (action.equalsIgnoreCase("editAmenities")) {
                Long amenitiesId = Long.parseLong(request.getParameter("id"));
                Amenities amenities = amenitiesService.getAmenitiesById(amenitiesId);
                String filePath = amenities.getImage().substring(amenities.getImage().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath;
                request.setAttribute("filePath2", filePath2);
                request.setAttribute("amenities", amenities);
                address1 = "/WEB-INF/edit/editAmenities.jsp";
            } else if (action.equalsIgnoreCase("updateAmenities")) {
                Amenities amenities = new Amenities();
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
                            amenities.setImage(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);

                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                amenities.setName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("action1")) {
                                String action1 = item.getString();
                                amenities.setAction(Integer.valueOf(action1));
                                System.out.println("action1=" + action1);
                            } else if (item.getFieldName().equalsIgnoreCase("description")) {
                                String description = item.getString();
                                amenities.setDescription(description);
                            } else if (item.getFieldName().equalsIgnoreCase("id")) {
                                String amenitiesId = item.getString();
                                amenities.setId(Long.parseLong(amenitiesId));
                                System.out.println("amenitiesId="+amenitiesId);
                            }
                        }

                    }
                    boolean isUpdate = amenitiesService.updateAmenities(amenities);
                    address1 = "/WEB-INF/pages/amenitiesList.jsp";
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

            } else if (action.equalsIgnoreCase("deleteAmenities")) {
                Long amenitiesId = Long.parseLong(request.getParameter("amenitiesId"));
                boolean isDelete = amenitiesService.deleteAmenities(amenitiesId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }

            } else if (action.equalsIgnoreCase("viewAmenities")) {
                Long amenitiesId = Long.parseLong(request.getParameter("id"));
                Amenities amenities = amenitiesService.getAmenitiesById(amenitiesId);
                String filePath = amenities.getImage().substring(amenities.getImage().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath;
                request.setAttribute("filePath2", filePath2);
                request.setAttribute("amenities", amenities);
                address1 = "/WEB-INF/pages/viewAmenities.jsp";
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
