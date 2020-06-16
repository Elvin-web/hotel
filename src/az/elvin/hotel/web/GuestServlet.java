package az.elvin.hotel.web;

import az.elvin.hotel.dao.*;
import az.elvin.hotel.dao.impl.*;
import az.elvin.hotel.model.*;
import az.elvin.hotel.service.*;
import az.elvin.hotel.service.impl.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "GuestServlet", urlPatterns = "/gs")
public class GuestServlet extends HttpServlet {
    GuestDao guestDao = new GuestDaoImpl();
    GuestService guestService = new GuestServiceImpl(guestDao);
    CityDao cityDao = new CityDaoImpl();
    CityService cityService = new CityServiceImpl(cityDao);
    CountryDao countryDao = new CountryDaoImpl();
    CountryService countryService = new CountryServiceImpl(countryDao);
    PassportDao passportDao = new PassportDaoImpl();
    PassportService passportService = new PassportServiceImpl(passportDao);
    PaymentDao paymentDao = new PaymentDaoImpl();
    PaymentService paymentService = new PaymentServiceImpl(paymentDao);
    ReservationDao reservationDao = new ReservationDaoImpl();
    ReservationService reservationService = new ReservationServiceImpl(reservationDao);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
            if (action.equalsIgnoreCase("getGuestList1")) {
                List<Guest> guestList1 = guestService.getGuestList();
                List<City> cityList = cityService.getCityList();
                List<Country> countryList = countryService.getCountryList();
                List<Passport> passportList = passportService.getPassportList();
                request.setAttribute("guestList1", guestList1);
                request.setAttribute("cityList", cityList);
                // request.setAttribute("countryList", countryList);
                request.setAttribute("passportList", passportList);
                Long reservationAcount = reservationService.reservationAcount();
                request.setAttribute("reservationAcount", reservationAcount);
                Long reservationGuestAcount = reservationService.reservationGuestAcount();
                request.setAttribute("reservationGuestAcount", reservationGuestAcount);
                Long ToWeekIncomeCount = paymentService.ToWeekIncomeCount();
                request.setAttribute("ToWeekIncomeCount", ToWeekIncomeCount);
                address1 = "/WEB-INF/pages/guestList.jsp";
            }

            else if (action.equalsIgnoreCase("getCityListByCountryId")) {
                Long countryId = Long.parseLong(request.getParameter("countryId"));
                List<City> cityList = cityService.getCityListByCountryId(countryId);
                request.setAttribute("cityList", cityList);
                address1 = "/WEB-INF/pages/cityCombo.jsp";
            } else if (action.equalsIgnoreCase("addGuest")) {
                List<Country> countryList = countryService.getCountryList();
                List<City> cityList = cityService.getCityList();
                List<Passport> passportList = passportService.getPassportList();
                request.setAttribute("countryList", countryList);
                request.setAttribute("cityList", cityList);
                request.setAttribute("passportList", passportList);
                address1 = "/WEB-INF/new/newGuest.jsp";
            } else if (action.equalsIgnoreCase("addGuest1")) {

                Guest guest = new Guest();
                City city1 = new City();
                Country country1 = new Country();
                String filePath = "";
                String filePath1 = "";
                String fileName = "";
                String fileName1 = "";
                String newFileName = "";
                String newFileName1 = "";
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
                    while (iter.hasNext()) {
                        UUID uuid = UUID.randomUUID();
                        FileItem item = (FileItem) iter.next();
                        if (!item.isFormField()) {
                            if (item.getFieldName().equalsIgnoreCase("fileName")) {
                                fileName = new File(item.getName()).getName();
                                System.out.println("fileName" + fileName);
                                newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                                filePath = uploadPath + File.separator + newFileName;
                                System.out.println("filePath=" + filePath);
                                guest.setImage1(filePath);
                                File storeFile = new File(filePath);
                                item.write(storeFile);
                            } else if (item.getFieldName().equalsIgnoreCase("fileName1")) {
                                fileName1 = new File(item.getName()).getName();
                                System.out.println("fileName1" + fileName1);
                                newFileName1 = fileName1.replace(fileName1.substring(0, fileName1.lastIndexOf(".")), uuid.toString());
                                filePath1 = uploadPath + File.separator + newFileName1;
                                System.out.println("filePath1=" + filePath1);
                                guest.setImage2(filePath1);
                                File storeFile1 = new File(filePath1);
                                item.write(storeFile1);
                            }
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("name")) {

                                String name = item.getString();
                                guest.setGuestName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("surname")) {
                                String surname = item.getString();
                                guest.setGuestSurname(surname);
                            } else if (item.getFieldName().equalsIgnoreCase("email")) {
                                String email = item.getString();
                                guest.setEmail(email);
                            } else if (item.getFieldName().equalsIgnoreCase("passport")) {
                                String passport = item.getString();
                                Passport passport1 = new Passport();
                                passport1.setId(Long.parseLong(passport));
                                guest.setPassport(passport1);
                            } else if (item.getFieldName().equalsIgnoreCase("dob")) {
                                String dob = item.getString();
                                guest.setDob(df.parse(dob));
                            } else if (item.getFieldName().equalsIgnoreCase("phone")) {
                                String phone = item.getString();
                                guest.setPhone(phone);
                            } else if (item.getFieldName().equalsIgnoreCase("ID1")) {
                                String ID1 = item.getString();
                                guest.setPassportNumber(ID1);
                            } else if (item.getFieldName().equalsIgnoreCase("city")) {
                                String city = item.getString();
                                city1.setId(Long.parseLong(city));
                                guest.setCity(city1);
                            } else if (item.getFieldName().equalsIgnoreCase("country")) {
                                String country = item.getString();
                                country1.setId(Long.parseLong(country));
                                city1.setCountry(country1);
                                guest.setCity(city1);
                            } else if (item.getFieldName().equalsIgnoreCase("male")) {
                                String male = item.getString();
                                if (male != null) {
                                    guest.setGender(male);
                                }
                            } else if (item.getFieldName().equalsIgnoreCase("female")) {
                                String female = item.getString();
                                if (female != null) {
                                    guest.setGender(female);
                                }
                            }
                        }

                    }
                    boolean isAdded = guestService.addGuest(guest);
                    List<Guest> guestList1 = guestService.getGuestList();
                    List<City> cityList = cityService.getCityList();
                    List<Country> countryList = countryService.getCountryList();
                    List<Passport> passportList = passportService.getPassportList();
                    request.setAttribute("guestList1", guestList1);
                    request.setAttribute("cityList", cityList);
                    // request.setAttribute("countryList", countryList);
                    request.setAttribute("passportList", passportList);
                    address1 = "/WEB-INF/pages/guestList.jsp";
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

            } else if (action.equalsIgnoreCase("editGuest")) {
                Long guestId = Long.parseLong(request.getParameter("id"));
                Guest guest = guestService.getGuestById(guestId);
                String filePath1 = guest.getImage1().substring(guest.getImage1().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath1;
                request.setAttribute("filePath2", filePath2);
                String filePath3 = guest.getImage2().substring(guest.getImage2().lastIndexOf("\\"));
                String filePath4 = "upload" + File.separator + filePath3;
                request.setAttribute("filePath4", filePath4);
                List<Country> countryList = countryService.getCountryList();
                List<City> cityList = cityService.getCityList();
                List<Passport> passportList = passportService.getPassportList();
                request.setAttribute("cityList", cityList);
                request.setAttribute("passportList", passportList);
                request.setAttribute("countryList", countryList);
                request.setAttribute("guest", guest);
                address1 = "/WEB-INF/edit/editGuest.jsp";

            } else if (action.equalsIgnoreCase("updateGuest")) {
                Guest guest = new Guest();
                City city1 = new City();
                Country country1 = new Country();
                String filePath = "";
                String filePath1 = "";
                String fileName = "";
                String fileName1 = "";
                String newFileName = "";
                String newFileName1 = "";
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

                    while (iter.hasNext()) {
                        UUID uuid = UUID.randomUUID();
                        FileItem item = (FileItem) iter.next();
                        if (!item.isFormField()) {
                            if (item.getFieldName().equalsIgnoreCase("fileName")) {
                                System.out.println("item.getName()=" + item.getName());
                                if (item.getName() != null || !item.getName().isEmpty()) {
                                    fileName = new File(item.getName()).getName();
                                    System.out.println("fileName=" + fileName);
                                    newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());
                                    filePath = uploadPath + File.separator + newFileName;
                                    guest.setImage1(filePath);
                                    File storeFile = new File(filePath);
                                    item.write(storeFile);
                                } else {
                                    String picture1 = item.getString();
                                    System.out.println("picture1=" + picture1);
                                    guest.setImage1(picture1);

                                }

                            } else if (item.getFieldName().equalsIgnoreCase("fileName1")) {
                                if (item.getName() != null && !item.getName().isEmpty()) {
                                    fileName1 = new File(item.getName()).getName();
                                    newFileName1 = fileName1.replace(fileName1.substring(0, fileName1.lastIndexOf(".")), uuid.toString());
                                    filePath1 = uploadPath + File.separator + newFileName1;
                                    guest.setImage2(filePath1);
                                    File storeFile1 = new File(filePath1);
                                    item.write(storeFile1);
                                } else {
                                    if (item.getFieldName().equalsIgnoreCase("id")) {
                                        String guestId4 = item.getString();
                                        Guest guest1 = guestService.getGuestById(Long.parseLong(guestId4));
                                        System.out.println("guestId4=" + guestId4);
                                        String picture2 = guest1.getImage2();
                                        guest.setImage2(picture2);
                                    }
                                }
                            }
                        } else {
                            if (item.getFieldName().equalsIgnoreCase("id")) {
                                String guestId5 = item.getString();
                                guest.setId(Long.parseLong(guestId5));
                                System.out.println("guestId5=" + guestId5);
                            } else if (item.getFieldName().equalsIgnoreCase("name")) {
                                String name = item.getString();
                                guest.setGuestName(name);
                                System.out.println("name=" + name);
                            } else if (item.getFieldName().equalsIgnoreCase("surname")) {
                                String surname = item.getString();
                                guest.setGuestSurname(surname);
                            } else if (item.getFieldName().equalsIgnoreCase("email")) {
                                String email = item.getString();
                                guest.setEmail(email);
                            } else if (item.getFieldName().equalsIgnoreCase("passport")) {
                                String passport = item.getString();
                                Passport passport1 = new Passport();
                                passport1.setId(Long.parseLong(passport));
                                guest.setPassport(passport1);
                            } else if (item.getFieldName().equalsIgnoreCase("dob")) {
                                String dob = item.getString();
                                guest.setDob(df.parse(dob));
                            } else if (item.getFieldName().equalsIgnoreCase("phone")) {
                                String phone = item.getString();
                                guest.setPhone(phone);
                            } else if (item.getFieldName().equalsIgnoreCase("ID1")) {
                                String ID1 = item.getString();
                                guest.setPassportNumber(ID1);
                            } else if (item.getFieldName().equalsIgnoreCase("city")) {
                                String city = item.getString();
                                city1.setId(Long.parseLong(city));
                                guest.setCity(city1);
                            } else if (item.getFieldName().equalsIgnoreCase("male")) {
                                String male = item.getString();
                                if (male != null) {
                                    guest.setGender(male);
                                }
                            } else if (item.getFieldName().equalsIgnoreCase("female")) {
                                String female = item.getString();
                                if (female != null) {
                                    guest.setGender(female);
                                }
                            }
                        }

                    }
                    boolean isUpdate = guestService.updateGuest(guest);
                    System.out.println("guest=" + guest);
                    response.setContentType("text/html");
                    List<Guest> guestList1 = guestService.getGuestList();
                    List<City> cityList = cityService.getCityList();
                    List<Country> countryList = countryService.getCountryList();
                    List<Passport> passportList = passportService.getPassportList();
                    request.setAttribute("guestList1", guestList1);
                    request.setAttribute("cityList", cityList);
                    // request.setAttribute("countryList", countryList);
                    request.setAttribute("passportList", passportList);
                    address1 = "/WEB-INF/pages/guestList.jsp";
                    if (isUpdate) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }

                } catch (Exception ex) {
                    request.setAttribute("message", "There was an error:" + ex.getMessage());
                    ex.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("deleteGuest")) {
                Long guestId = Long.parseLong(request.getParameter("guestId"));
                boolean isDelete = guestService.deleteGuest(guestId);
                response.setContentType("text/html");
                if (isDelete) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }

            } else if (action.equalsIgnoreCase("viewGuest")) {
                Long guestId = Long.parseLong(request.getParameter("id"));
                Guest guest = guestService.getGuestById(guestId);
                String filePath1 = guest.getImage1().substring(guest.getImage1().lastIndexOf("\\"));
                String filePath2 = "upload" + File.separator + filePath1;
                request.setAttribute("filePath2", filePath2);
                String filePath3 = guest.getImage2().substring(guest.getImage2().lastIndexOf("\\"));
                String filePath4 = "upload" + File.separator + filePath3;
                request.setAttribute("filePath4", filePath4);
                List<City> cityList = cityService.getCityList();
                List<Passport> passportList = passportService.getPassportList();
                request.setAttribute("cityList", cityList);
                request.setAttribute("passportList", passportList);
                request.setAttribute("guest", guest);
                address1 = "/WEB-INF/pages/viewGuest.jsp";

            } else if (action.equalsIgnoreCase("paymentGuest")) {
                Long guestId = Long.parseLong(request.getParameter("id"));
                List<Payment> paymentList = paymentService.getPaymentListByGuestId(guestId);
                List<City> cityList = cityService.getCityList();
                List<Passport> passportList = passportService.getPassportList();
                request.setAttribute("cityList", cityList);
                request.setAttribute("passportList", passportList);
                request.setAttribute("paymentList", paymentList);
                address1 = "/WEB-INF/pages/paymentGuest.jsp";

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
