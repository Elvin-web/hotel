package az.elvin.hotel.web;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PositionDao;
import az.elvin.hotel.dao.StaffDao;
import az.elvin.hotel.dao.impl.PositionDaoImpl;
import az.elvin.hotel.dao.impl.StaffDaoImpl;
import az.elvin.hotel.model.Amenities;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.model.Position;
import az.elvin.hotel.model.Staff;
import az.elvin.hotel.service.PositionService;
import az.elvin.hotel.service.StaffService;
import az.elvin.hotel.service.impl.PositionServiceImpl;
import az.elvin.hotel.service.impl.StaffServiceImpl;
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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "StaffServlet", urlPatterns = "/ss")
public class StaffServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 1024;
    private static final int REQUEST_SIZE = 1024 * 1024 * 50;
    StaffDao staffDao = new StaffDaoImpl();
    StaffService staffService = new StaffServiceImpl(staffDao);
    PositionDao positionDao = new PositionDaoImpl();
    PositionService positionService = new PositionServiceImpl(positionDao);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df2 = new SimpleDateFormat("HH:mm");

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
            if (action.equalsIgnoreCase("getStaffList")) {

                List<Staff> staffList = staffService.getStaffList();
                request.setAttribute("staffList", staffList);
                address1 = "/WEB-INF/pages/staffList.jsp";

            } else if (action.equalsIgnoreCase("addStaff")) {
                List<Position> positionList = positionService.getPositionList();
                request.setAttribute("positionList", positionList);
                address1 = "/WEB-INF/new/newStaff.jsp";
            } else if (action.equalsIgnoreCase("addStaff1")) {
                Staff staff = new Staff();
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
                            staff.setImage(filePath);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {

                                String name = item.getString();
                                staff.setName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("surname")) {
                                String surname = item.getString();
                                staff.setSurname(surname);
                            } else if (item.getFieldName().equalsIgnoreCase("email")) {
                                String email = item.getString();
                                staff.setEmail(email);
                            } else if (item.getFieldName().equalsIgnoreCase("username")) {
                                String username = item.getString();
                                staff.setUsername(username);
                            } else if (item.getFieldName().equalsIgnoreCase("password")) {
                                String password = item.getString();
                                staff.setPassword(password);
                            } else if (item.getFieldName().equalsIgnoreCase("address")) {
                                String address = item.getString();
                                staff.setAddress(address);
                            } else if (item.getFieldName().equalsIgnoreCase("dob")) {
                                String dob = item.getString();
                                staff.setDob(df.parse(dob));
                            } else if (item.getFieldName().equalsIgnoreCase("phone")) {
                                String phone = item.getString();
                                staff.setPhone(phone);
                            } else if (item.getFieldName().equalsIgnoreCase("jobStart")) {
                                String jobStart = item.getString();
                                staff.setJobStart(jobStart);
                            } else if (item.getFieldName().equalsIgnoreCase("jobEnd")) {
                                String jobEnd = item.getString();
                                staff.setJobEnd(jobEnd);
                            } else if (item.getFieldName().equalsIgnoreCase("salary")) {
                                String salary = item.getString();
                                staff.setSalary(Double.valueOf(salary));
                            } else if (item.getFieldName().equalsIgnoreCase("ID1")) {
                                String ID1 = item.getString();
                                staff.setID(ID1);
                            } else if (item.getFieldName().equalsIgnoreCase("position")) {
                                String position = item.getString();
                                Position position1 = new Position();
                                position1.setId(Long.parseLong(position));
                                staff.setPosition(position1);
                            } else if (item.getFieldName().equalsIgnoreCase("male")) {
                                String male = item.getString();
                                if (male != null) {
                                    staff.setGender(male);
                                }
                            } else if (item.getFieldName().equalsIgnoreCase("female")) {
                                String female = item.getString();
                                if (female != null) {
                                    staff.setGender(female);
                                }
                            }
                        }

                    }
                    boolean isAdded = staffService.addStaff(staff);
                    List<Staff> staffList = staffService.getStaffList();
                    request.setAttribute("staffList", staffList);
                    address1 = "/WEB-INF/pages/staffList.jsp";
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


            } else if (action.equalsIgnoreCase("editStaff")) {
                List<Position> positionList = positionService.getPositionList();
                request.setAttribute("positionList", positionList);
                Long staffId = Long.parseLong(request.getParameter("id"));
                Staff staff = staffService.getStaffById(staffId);
                String filePath = staff.getImage().substring(staff.getImage().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath;
                request.setAttribute("filePath2", filePath2);
                request.setAttribute("staff", staff);
                address1 = "/WEB-INF/edit/editStaff.jsp";

            } else if (action.equalsIgnoreCase("updateStaff")) {
                Staff staff = new Staff();
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
                           /* fileName = new File(item.getName()).getName();
                            System.out.println("fileName="+fileName);*/
                            Part bar = request.getPart("fileName");
                            if (bar == null) {
                                if (item.getFieldName().equalsIgnoreCase("id")) {
                                    String staffId = item.getString();
                                    Staff staff1=staffService.getStaffById(Long.parseLong(staffId));
                                    String picture=staff1.getImage();
                                    System.out.println("picture"+picture);
                                    staff.setImage(picture);
                                }
                                // bar was not submitted at all.
                            } else if (bar.getSize() == 0) {
                                if (item.getFieldName().equalsIgnoreCase("id")) {
                                    String staffId = item.getString();
                                    Staff staff1=staffService.getStaffById(Long.parseLong(staffId));
                                    String picture=staff1.getImage();
                                    System.out.println("picture"+picture);
                                    staff.setImage(picture);
                                }
                                // bar is submitted without value.
                            } else {
                                fileName = new File(item.getName()).getName();
                                System.out.println("fileName="+fileName);
                                newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                                System.out.println("newFileName="+newFileName);
                                filePath = uploadPath + File.separator + newFileName;
                                System.out.println("filePath=" + filePath);
                                staff.setImage(filePath);
                                File storeFile = new File(filePath);
                                item.write(storeFile);
                                // bar is submitted with a value.
                            }




                           // var vidFile = fileName.getElementById("videoUploadFile").files[0].name;
                         /*  if (fileName.length() != 0 && fileName!=null){
                               newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                               System.out.println("newFileName="+newFileName);
                               filePath = uploadPath + File.separator + newFileName;
                               System.out.println("filePath=" + filePath);
                               staff.setImage(filePath);
                               File storeFile = new File(filePath);
                               item.write(storeFile);
                           }else{
                               if (item.getFieldName().equalsIgnoreCase("id")) {
                                   String staffId = item.getString();
                                   Staff staff1=staffService.getStaffById(Long.parseLong(staffId));
                                  String picture=staff1.getImage();
                                   System.out.println("picture"+picture);
                                   staff.setImage(picture);
                               }
                           }*/
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {

                                String name = item.getString();
                                staff.setName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("surname")) {
                                String surname = item.getString();
                                staff.setSurname(surname);
                            } else if (item.getFieldName().equalsIgnoreCase("address")) {
                                String address = item.getString();
                                staff.setAddress(address);
                            } else if (item.getFieldName().equalsIgnoreCase("email")) {
                                String email = item.getString();
                                staff.setEmail(email);
                            } else if (item.getFieldName().equalsIgnoreCase("username")) {
                                String username = item.getString();
                                staff.setUsername(username);
                            } else if (item.getFieldName().equalsIgnoreCase("password")) {
                                String password = item.getString();
                                staff.setPassword(password);
                            } else if (item.getFieldName().equalsIgnoreCase("dob")) {
                                String dob = item.getString();
                                staff.setDob(df.parse(dob));
                            } else if (item.getFieldName().equalsIgnoreCase("phone")) {
                                String phone = item.getString();
                                staff.setPhone(phone);
                            } else if (item.getFieldName().equalsIgnoreCase("jobStart")) {
                                String jobStart = item.getString();
                                staff.setJobStart(jobStart);
                            } else if (item.getFieldName().equalsIgnoreCase("jobEnd")) {
                                String jobEnd = item.getString();
                                staff.setJobEnd(jobEnd);
                            } else if (item.getFieldName().equalsIgnoreCase("salary")) {
                                String salary = item.getString();
                                staff.setSalary(Double.valueOf(salary));
                            } else if (item.getFieldName().equalsIgnoreCase("position")) {
                                String position = item.getString();
                                Position position1 = new Position();
                                position1.setId(Long.parseLong(position));
                                staff.setPosition(position1);
                            } else if (item.getFieldName().equalsIgnoreCase("male")) {
                                String male = item.getString();
                                if (male != null) {
                                    staff.setGender(male);
                                }
                            } else if (item.getFieldName().equalsIgnoreCase("female")) {
                                String female = item.getString();
                                if (female != null) {
                                    staff.setGender(female);
                                }
                            } else if (item.getFieldName().equalsIgnoreCase("ID1")) {
                                String ID1 = item.getString();
                                staff.setID(ID1);
                            } else if (item.getFieldName().equalsIgnoreCase("id")) {
                                String staffId = item.getString();
                                staff.setId(Long.parseLong(staffId));
                            }
                        }

                    }
                    System.out.println("staff="+staff);
                    boolean isUpdate = staffService.updateStaff(staff);
                    List<Staff> staffList = staffService.getStaffList();
                    request.setAttribute("staffList", staffList);
                    address1 = "/WEB-INF/pages/staffList.jsp";
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

            } else if (action.equalsIgnoreCase("viewStaff")) {
                Long staffId = Long.parseLong(request.getParameter("id"));
                Staff staff = staffService.getStaffById(staffId);
                String filePath = staff.getImage().substring(staff.getImage().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath;
                request.setAttribute("filePath2", filePath2);
                request.setAttribute("staff", staff);
                address1 = "/WEB-INF/pages/viewStaff.jsp";

            } else if (action.equalsIgnoreCase("deleteStaff")) {
                Long staffId = Long.parseLong(request.getParameter("staffId"));
                boolean isDelete = staffService.deleteStaff(staffId);
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
