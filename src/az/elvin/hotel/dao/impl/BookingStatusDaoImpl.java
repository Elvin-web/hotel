package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.BookingStatusDao;
import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.model.BookingStatus;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingStatusDaoImpl implements BookingStatusDao {
    @Override
    public List<BookingStatus> getBookingStatusList() throws Exception {
        List<BookingStatus> bookingStatusList = new ArrayList<BookingStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT bs.id_booking_status,bs.booking_status\n"
                + "    FROM booking_status bs\n"
                + "   WHERE bs.active = 1\n"
                + "ORDER BY bs.id_booking_status";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookingStatus bookingStatus = new BookingStatus();
                    bookingStatus.setId(rs.getLong("id_booking_status"));
                    bookingStatus.setBookingStatus(rs.getString("booking_status"));
                    bookingStatusList.add(bookingStatus);
                }
                JdbcUtility.close(c, ps, rs);
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return bookingStatusList;
    }

    @Override
    public boolean addBookingStatus(BookingStatus bookingStatus) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  booking_status (booking_status) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, bookingStatus.getBookingStatus());
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
    public BookingStatus getBookingStatusById(Long bookingStatusId) throws Exception {
        BookingStatus bookingStatus = new BookingStatus();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT bs.id_booking_status,bs.booking_status\n"
                + "    FROM booking_status bs\n"
                + "   WHERE bs.active = 1\n"
                + "AND bs.id_booking_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookingStatusId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    bookingStatus.setId(rs.getLong("id_booking_status"));
                    bookingStatus.setBookingStatus(rs.getString("booking_status"));
                } else {
                    bookingStatus = null;
                }
                JdbcUtility.close(c, ps, rs);
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return bookingStatus;
    }

    @Override
    public boolean updateBookingStatus(BookingStatus bookingStatus) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  booking_status SET booking_status=? WHERE id_booking_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, bookingStatus.getBookingStatus());
                ps.setLong(2, bookingStatus.getId());
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
    public boolean deleteBookingStatus(Long bookingStatusId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  booking_status SET active = 0 " + " WHERE id_booking_status = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, bookingStatusId);
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
    public List<BookingStatus> searchBookingStatusData(String keyword) throws Exception {
        List<BookingStatus> bookingStatusList = new ArrayList<BookingStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT bs.id_booking_status,bs.booking_status\n"
                + "    FROM booking_status bs\n"
                + "   WHERE bs.active = 1\n"
                + "AND ( LOWER(booking_status) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    BookingStatus bookingStatus = new BookingStatus();
                    bookingStatus.setId(rs.getLong("id_booking_status"));
                    bookingStatus.setBookingStatus(rs.getString("booking_status"));
                    bookingStatusList.add(bookingStatus);
                }
                JdbcUtility.close(c, ps, rs);
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return bookingStatusList;
    }
}
