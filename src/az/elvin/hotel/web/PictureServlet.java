package az.elvin.hotel.web;

import az.elvin.hotel.dao.AmenitiesDao;
import az.elvin.hotel.dao.impl.AmenitiesDaoImpl;
import az.elvin.hotel.model.Amenities;
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
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "PictureServlet",urlPatterns = "/pcs")
public class PictureServlet extends HttpServlet {
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
        String filePath = "";
        String fileName = "";
        String newFileName = "";
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
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("studentName")) {
                                String name = item.getString();
                                amenities.setName(name);
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
                    }
                   /* request.setAttribute("imagePath2", UPLOAD_DIRECTORY + File.separator + newFileName);
                    request.setAttribute("imagePath", filePath);
                    request.setAttribute("message", "Upload has been done successfully");*/
                } catch (Exception ex) {
                    request.setAttribute("message", "There was an error:" + ex.getMessage());
                    ex.printStackTrace();
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
