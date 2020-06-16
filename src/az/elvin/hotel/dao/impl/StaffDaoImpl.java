package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.StaffDao;
import az.elvin.hotel.model.Hotel;
import az.elvin.hotel.model.Position;
import az.elvin.hotel.model.Staff;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {
    @Override
    public List<Staff> getStaffList() throws Exception {
        List<Staff> staffList = new ArrayList<Staff>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_staff,\n"
                + "         s.first_name,\n"
                + "         s.last_name,\n"
                + "         s.address,\n"
                + "         s.picture,s.email,\n"
                + "         s.username,s.password,\n"
                + "         s.dob,\n"
                + "         s.phone,\n"
                + "         s.salary,\n"
                + "         s.job_start,\n"
                + "         s.job_end,\n"
                + "         s.gender,s.ID,\n"
                + "         p.id_position id_position,\n"
                + "         p.position_value\n"
                + "    FROM staff s\n"
                + "         INNER JOIN position p\n"
                + "            ON s.position_id = p.id_position\n"
                + "   WHERE s.active = 1\n"
                + "ORDER BY s.id_staff";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    staff.setImage(rs.getString("picture"));
                    staff.setEmail(rs.getString("email"));
                    staff.setPassword(rs.getString("password"));
                    staff.setUsername(rs.getString("username"));
                    staff.setPhone(rs.getString("phone"));
                    staff.setSalary(rs.getDouble("salary"));
                    staff.setAddress(rs.getString("address"));
                    staff.setDob(rs.getDate("dob"));
                    staff.setJobStart(rs.getString("job_start"));
                    staff.setJobEnd(rs.getString("job_end"));
                    staff.setGender(rs.getString("gender"));
                    staff.setID(rs.getString("ID"));
                    Position position = new Position();
                    position.setId(rs.getLong("id_position"));
                    position.setPositionValue(rs.getString("position_value"));
                    staff.setPosition(position);
                    staffList.add(staff);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return staffList;
    }

    @Override
    public boolean addStaff(Staff staff) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  staff (first_name,last_name,address,dob,phone,salary,job_start,job_end,position_id,gender,picture,email,username,password,ID) " + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, staff.getName());
                ps.setString(2, staff.getSurname());
                ps.setString(3, staff.getAddress());
                ps.setDate(4, new java.sql.Date(staff.getDob().getTime()));
                ps.setString(5, staff.getPhone());
                ps.setDouble(6, staff.getSalary());
                ps.setString(7,staff.getJobStart());
                ps.setString(8, staff.getJobEnd());
                ps.setLong(9, staff.getPosition().getId());
                ps.setString(10, staff.getGender());
                ps.setString(11,staff.getImage());
                ps.setString(12,staff.getEmail());
                ps.setString(13,staff.getUsername());
                ps.setString(14,staff.getPassword());
                ps.setString(15,staff.getID());
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
    public Staff getStaffById(Long staffId) throws Exception {
        Staff staff = new Staff();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_staff,\n"
                + "         s.first_name,\n"
                + "         s.last_name,\n"
                + "         s.address,s.email,\n"
                + "         s.dob,\n"
                + "         s.phone,\n"
                + "         s.salary,\n"
                + "         s.job_start,\n"
                + "         s.job_end,\n"
                + "         s.gender,s.ID,s.data_date,\n"
                + "         s.picture,\n"
                + "         s.username,s.password,\n"
                + "         p.id_position id_position,\n"
                + "         p.position_value,\n"
                + "         h.id_hotel,\n"
                + "         h.address hotel_address,h.name hotel_name,\n"
                + "         h.phone hotel_phone,\n"
                + "         h.email hotel_email\n"
                + "    FROM staff s\n"
                + "         INNER JOIN position p\n"
                + "            ON s.position_id = p.id_position\n"
                + "         INNER JOIN hotel h\n"
                + "            ON s.hotel_id = h.id_hotel\n"
                + "   WHERE s.active = 1\n"
                + "AND s.id_staff=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, staffId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    staff.setAddress(rs.getString("address"));
                    staff.setEmail(rs.getString("email"));
                    staff.setPassword(rs.getString("password"));
                    staff.setUsername(rs.getString("username"));
                    staff.setDob(rs.getDate("dob"));
                    staff.setJobStart(rs.getString("job_start"));
                    staff.setJobEnd(rs.getString("job_end"));
                    staff.setGender(rs.getString("gender"));
                    staff.setPhone(rs.getString("phone"));
                    staff.setSalary(rs.getDouble("salary"));
                    staff.setImage(rs.getString("picture"));
                    staff.setID(rs.getString("ID"));
                    staff.setDate(rs.getDate("data_date"));
                    Position position = new Position();
                    position.setId(rs.getLong("id_position"));
                    position.setPositionValue(rs.getString("position_value"));
                    Hotel hotel=new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelPhone(rs.getString("hotel_phone"));
                    hotel.setHotelEmail(rs.getString("hotel_email"));
                    hotel.setHotelAddress(rs.getString("hotel_address"));
                    hotel.setHotelName(rs.getString("hotel_name"));
                    staff.setHotel(hotel);
                    staff.setPosition(position);
                } else {
                    staff = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return staff;
    }

    @Override
    public boolean updateStaff(Staff staff) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE staff SET first_name=?,last_name=?,address=?,dob=?,phone=?,salary=?,job_start=?,job_end=?,position_id=?,gender=?,picture=?,email=?,username=?,password=?,ID=?,hotel_id=?  WHERE id_staff=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, staff.getName());
                ps.setString(2, staff.getSurname());
                ps.setString(3, staff.getAddress());
                ps.setDate(4, new java.sql.Date(staff.getDob().getTime()));
                ps.setString(5, staff.getPhone());
                ps.setDouble(6, staff.getSalary());
                ps.setString(7,staff.getJobStart());
                ps.setString(8,staff.getJobEnd());
                ps.setLong(9, staff.getPosition().getId());
                ps.setString(10, staff.getGender());
                ps.setString(11, staff.getImage());
                ps.setString(12, staff.getEmail());
                ps.setString(13,staff.getUsername());
                ps.setString(14,staff.getPassword());
                ps.setString(15,staff.getID());
                ps.setLong(16, 1);
                ps.setLong(17, staff.getId());
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
    public boolean deleteStaff(Long staffId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  staff SET active = 0 " + " WHERE id_staff = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, staffId);
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
    public List<Staff> searchStaffData(String keyword) throws Exception {
        List<Staff> staffList = new ArrayList<Staff>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_staff,\n"
                + "         s.first_name,\n"
                + "         s.last_name,\n"
                + "         s.address,\n"
                + "         s.picture,s.email,\n"
                + "         s.username,s.password,\n"
                + "         s.dob,\n"
                + "         s.phone,\n"
                + "         s.salary,\n"
                + "         s.job_start,\n"
                + "         s.job_end,\n"
                + "         s.gender,\n"
                + "         p.id_position id_position,\n"
                + "         p.position_value\n"
                + "    FROM staff s\n"
                + "         INNER JOIN position p\n"
                + "            ON s.position_id = P.ID\n"
                + "  WHERE active =1 AND (LOWER(job_end) LIKE LOWER (?)\n "
                + "OR LOWER(job_start) LIKE LOWER (?) OR LOWER(first_name) LIKE LOWER (?) \n"
                + "OR LOWER(last_name) LIKE LOWER (?) OR LOWER(address) LIKE LOWER (?) \n"
                + "OR LOWER(gender) LIKE LOWER (?) OR LOWER(picture) LIKE LOWER (?)\n "
                + "OR LOWER(phone) LIKE LOWER (?) OR LOWER(dob) LIKE LOWER (?)\n"
                + "OR LOWER(username) LIKE LOWER (?) OR LOWER(password) LIKE LOWER (?)\n"
                + "OR LOWER(salary) LIKE LOWER (?) OR LOWER(position_value) LIKE LOWER (?) OR LOWER(email) LIKE LOWER (?)) ";
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
                ps.setString(11, "%" + keyword + "%");
                ps.setString(12, "%" + keyword + "%");
                ps.setString(13, "%" + keyword + "%");
                ps.setString(14, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    staff.setAddress(rs.getString("address"));
                    staff.setImage(rs.getString("picture"));
                    staff.setEmail(rs.getString("email"));
                    staff.setGender(rs.getString("gender"));
                    staff.setPassword(rs.getString("password"));
                    staff.setUsername(rs.getString("username"));
                    staff.setSalary(rs.getDouble("salary"));
                    staff.setPhone(rs.getString("phone"));
                    staff.setDob(rs.getDate("dob"));
                    staff.setJobStart(rs.getString("job_start"));
                    staff.setJobEnd(rs.getString("job_end"));
                    Position position = new Position();
                    position.setId(rs.getLong("id_position"));
                    position.setPositionValue(rs.getString("position_value"));
                    staff.setPosition(position);
                    staffList.add(staff);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return staffList;
    }

}
