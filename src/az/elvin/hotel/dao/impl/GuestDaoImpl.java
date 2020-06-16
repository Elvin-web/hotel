package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.GuestDao;
import az.elvin.hotel.model.*;
import az.elvin.hotel.util.JdbcUtility;

import java.lang.management.GarbageCollectorMXBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GuestDaoImpl implements GuestDao {
    @Override
    public List<Guest> getGuestList() throws Exception {
        List<Guest> guestList = new ArrayList<Guest>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT g.id_guest,g.name,g.surname, g.gender,g.phone, g.email,\n"
                + "         g.dob, g.passport_number,g.image1,g.image2,\n"
                + "         cy.id_city,cy.name city_name,\n"
                + "         c.id_country,c.name country_name,\n"
                + "         p.id_passport,\n"
                + "         p.passport_type\n"
                + "         FROM guest g\n"
                + "         INNER JOIN passport p\n"
                + "            ON g.passport_id = p.id_passport\n"
                + "         INNER JOIN city cy\n"
                + "            ON g.city_id = cy.id_city\n"
                + "         INNER JOIN country c\n"
                + "            ON cy.country_id = c.id_country\n"
                + "   WHERE g.active = 1\n"
                + "ORDER BY g.id_guest ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGender(rs.getString("gender"));
                    guest.setGuestName(rs.getString("name"));
                    guest.setGuestSurname(rs.getString("surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setDob(rs.getDate("dob"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setPassportNumber(rs.getString("passport_number"));
                    guest.setImage1(rs.getString("image1"));
                    guest.setImage2(rs.getString("image2"));
                    City city = new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("city_name"));
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("country_name"));
                    city.setCountry(country);
                    guest.setCity(city);
                    Passport passport = new Passport();
                    passport.setId(rs.getLong("id_passport"));
                    passport.setPassportType(rs.getString("passport_type"));
                    guest.setPassport(passport);
                    guestList.add(guest);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return guestList;
    }

    @Override
    public boolean addGuest(Guest guest) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  guest ( name,surname,email,city_id,phone,dob,passport_number,passport_id,gender,image1,image2 ) " + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, guest.getGuestName());
                ps.setString(2, guest.getGuestSurname());
                ps.setString(3, guest.getEmail());
                ps.setLong(4, guest.getCity().getId());
                ps.setString(5, guest.getPhone());
                ps.setDate(6, new java.sql.Date(guest.getDob().getTime()));
                ps.setString(7, guest.getPassportNumber());
                ps.setLong(8, guest.getPassport().getId());
                ps.setString(9, guest.getGender());
                ps.setString(10, guest.getImage1());
                ps.setString(11, guest.getImage2());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public Guest getGuestById(Long guestId) throws Exception {

        Guest guest = new Guest();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT g.id_guest,g.name,g.surname, g.gender,g.phone, g.email,\n"
                + "         g.dob, g.passport_number,g.image1,g.image2,\n"
                + "         cy.id_city,cy.name city_name,\n"
                + "         c.id_country,c.name country_name,\n"
                + "         p.id_passport,\n"
                + "         p.passport_type\n"
                + "         FROM guest g\n"
                + "         INNER JOIN passport p\n"
                + "            ON g.passport_id = p.id_passport\n"
                + "         INNER JOIN city cy\n"
                + "            ON g.city_id = cy.id_city\n"
                + "         INNER JOIN country c\n"
                + "            ON cy.country_id = c.id_country\n"
                + "   WHERE g.active = 1 AND g.id_guest =?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, guestId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGender(rs.getString("gender"));
                    guest.setGuestName(rs.getString("name"));
                    guest.setGuestSurname(rs.getString("surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setDob(rs.getDate("dob"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setPassportNumber(rs.getString("passport_number"));
                    guest.setImage1(rs.getString("image1"));
                    guest.setImage2(rs.getString("image2"));
                    City city = new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("city_name"));
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("country_name"));
                    city.setCountry(country);
                    guest.setCity(city);
                    Passport passport = new Passport();
                    passport.setId(rs.getLong("id_passport"));
                    passport.setPassportType(rs.getString("passport_type"));
                    guest.setPassport(passport);

                } else {
                    guest = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return guest;
    }

    @Override
    public boolean updateGuest(Guest guest) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE guest SET name=?,surname=?,dob=?,city_id=?,phone=?,passport_id=?,passport_number=?,gender=?,email=?,image1=?,image2=? WHERE id_guest=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, guest.getGuestName());
                ps.setString(2, guest.getGuestSurname());
                ps.setDate(3, new java.sql.Date(guest.getDob().getTime()));
                ps.setLong(4, guest.getCity().getId());
                ps.setString(5, guest.getPhone());
                ps.setLong(6, guest.getPassport().getId());
                ps.setString(7, guest.getPassportNumber());
                ps.setString(8, guest.getGender());
                ps.setString(9, guest.getEmail());
                ps.setString(10, guest.getImage1());
                ps.setString(11, guest.getImage2());
                ps.setLong(12, guest.getId());
                ps.execute();
                result = true;
                System.out.println("ps="+ps);
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteGuest(Long guestId) throws Exception {

        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  guest SET active = 0 " + " WHERE id_guest = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, guestId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Guest> searchGuestData(String keyword) throws Exception {
        List<Guest> guestList = new ArrayList<Guest>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT g.id_guest,g.name,g.surname, g.gender,g.phone, g.email,\n"
                + "         g.dob, g.passport_number,g.image1,g.image2,\n"
                + "         cy.id_city,cy.name city_name,\n"
                + "         c.id_country,c.name country_name,\n"
                + "         p.id_passport,\n"
                + "         p.passport_type\n"
                + "         FROM guest g\n"
                + "         INNER JOIN passport p\n"
                + "            ON g.passport_id = p.id_passport\n"
                + "         INNER JOIN city cy\n"
                + "            ON g.city_id = cy.id_city\n"
                + "         INNER JOIN country c\n"
                + "            ON cy.country_id = c.id_country\n"
                + "   WHERE g.active = 1\n"
                + "AND ( LOWER(name)\n "
                + "LIKE LOWER (?) OR LOWER(surname)\n "
                + "LIKE LOWER (?) OR LOWER(email)\n "
                + "LIKE LOWER (?) OR LOWER(country_name)\n "
                + "LIKE LOWER (?) OR LOWER(city_name)\n "
                + "LIKE LOWER (?) OR LOWER(phone)\n "
                + "LIKE LOWER (?) OR LOWER(passport_number)\n "
                + "LIKE LOWER (?) OR LOWER(passport_type)\n "
                + "LIKE LOWER (?) OR LOWER(dob)\n "
                + "LIKE LOWER (?)OR LOWER(gender) LIKE LOWER (?))";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                ps.setString(6, "%" + keyword + "%");
                ps.setString(7, "%" + keyword + "%");
                ps.setString(8, "%" + keyword + "%");
                ps.setString(9, "%" + keyword + "%");
                ps.setString(10, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGender(rs.getString("gender"));
                    guest.setGuestName(rs.getString("name"));
                    guest.setGuestSurname(rs.getString("surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setDob(rs.getDate("dob"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setPassportNumber(rs.getString("passport_number"));
                    guest.setImage1(rs.getString("image1"));
                    guest.setImage2(rs.getString("image2"));
                    City city = new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("city_name"));
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("country_name"));
                    city.setCountry(country);
                    guest.setCity(city);
                    Passport passport = new Passport();
                    passport.setId(rs.getLong("id_passport"));
                    passport.setPassportType(rs.getString("passport_type"));
                    guest.setPassport(passport);
                    guestList.add(guest);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return guestList;
    }

    @Override
    public List<Guest> advancedSearchGuestData(AdvancedSearch advancedSearch) throws Exception {
        List<Guest> guestList = new ArrayList<Guest>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sql ="SELECT g.id_guest,g.name,g.surname, g.gender,g.phone, g.email,\n"
                + "         g.dob, g.passport_number,g.image1,g.image2,\n"
                + "         cy.id_city,cy.name city_name,\n"
                + "         c.id_country,c.name country_name,\n"
                + "         p.id_passport,\n"
                + "         p.passport_type\n"
                + "         FROM guest g\n"
                + "         INNER JOIN passport p\n"
                + "            ON g.passport_id = p.id_passport\n"
                + "         INNER JOIN city cy\n"
                + "            ON g.city_id = cy.id_city\n"
                + "         INNER JOIN country c\n"
                + "            ON cy.country_id = c.id_country\n"
                + "   WHERE g.active = 1\n";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
              /*  if (advancedSearch.getCountryId() != 0) {
                    sql += " AND C.ID = " + advancedSearch.getCountryId();
                }
                if (advancedSearch.getCityId() != 0) {
                    sql += " AND CY.ID = " + advancedSearch.getCityId();
                }
                if (advancedSearch.getCityId() != 0) {
                    sql += " AND P.ID = " + advancedSearch.getPassportId();
                }
                if (advancedSearch.getBeginDate() != null && advancedSearch.getBeginDate().isEmpty()) {
                    sql += " AND G.DOB >= TO_DATE('" + new java.sql.Date(df.parse(advancedSearch.getBeginDate()).getTime()) + "','DD-MM-YYYY')";
                }
                if (advancedSearch.getEndDate() != null && advancedSearch.getEndDate().isEmpty()) {
                    sql += " AND G.DOB <= TO_DATE('" + new java.sql.Date(df.parse(advancedSearch.getEndDate()).getTime()) + "','DD-MM-YYYY')";
                }*/
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGender(rs.getString("gender"));
                    guest.setGuestName(rs.getString("name"));
                    guest.setGuestSurname(rs.getString("surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setDob(rs.getDate("dob"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setPassportNumber(rs.getString("passport_number"));
                    guest.setImage1(rs.getString("image1"));
                    guest.setImage2(rs.getString("image2"));
                    City city = new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("city_name"));
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("country_name"));
                    city.setCountry(country);
                    guest.setCity(city);
                    Passport passport = new Passport();
                    passport.setId(rs.getLong("id_passport"));
                    passport.setPassportType(rs.getString("passport_type"));
                    guest.setPassport(passport);
                    guestList.add(guest);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return guestList;
    }

    @Override
    public Long guestAcount() throws Exception {
        Long guestAcount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  COUNT(id_guest) AS guestCnt"
                + "    FROM guest \n"
                + "   WHERE active = 1\n";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    guestAcount = rs.getLong("guestCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return guestAcount;
    }

}

