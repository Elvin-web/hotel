package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.HotelDao;
import az.elvin.hotel.model.Hotel;
import az.elvin.hotel.model.Star;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl implements HotelDao {
    @Override
    public List<Hotel> getHotelList() throws Exception {
        List<Hotel> hotelList = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_hotel,\n"
                + "        h.name,\n"
                + "         h.address,\n"
                + "         h.phone,\n"
                + "         h.email,h.logo,\n"
                + "         s.id_star id_star,\n"
                + "         s.star\n"
                + "    FROM hotel h\n"
                + "         INNER JOIN star s\n"
                + "            ON h.star_id = s.id_star\n"
                + "   WHERE h.active = 1\n"
                + "ORDER BY h.id_hotel";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("name"));
                    hotel.setHotelAddress(rs.getString("address"));
                    hotel.setHotelEmail(rs.getString("email"));
                    hotel.setHotelPhone(rs.getString("phone"));
                    hotel.setLogo(rs.getString("logo"));
                    Star star = new Star();
                    star.setId(rs.getLong("id_star"));
                    star.setStar(rs.getLong("star"));
                    hotel.setStar(star);
                    hotelList.add(hotel);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return hotelList;

    }

    @Override
    public boolean addHotel(Hotel hotel) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  hotel ( name,address,phone,email,star_id,logo) " + " VALUES (?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, hotel.getHotelName());
                ps.setString(2, hotel.getHotelAddress());
                ps.setString(3, hotel.getHotelPhone());
                ps.setString(4, hotel.getHotelEmail());
                ps.setLong(5, hotel.getStar().getId());
                ps.setString(6,hotel.getLogo());
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
    public Hotel getHotelById(Long hotelId) throws Exception {
        Hotel hotel = new Hotel();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT h.id_hotel,\n"
                + "        h.name,\n"
                + "         h.address,\n"
                + "         h.phone,\n"
                + "         h.email,h.logo,\n"
                + "         s.id_star id_star,\n"
                + "         s.star\n"
                + "    FROM hotel h\n"
                + "         INNER JOIN star s\n"
                + "            ON h.star_id = s.id_star\n"
                + "   WHERE h.active = 1\n"
                + "AND h.id_hotel=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, hotelId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("name"));
                    hotel.setHotelAddress(rs.getString("address"));
                    hotel.setHotelEmail(rs.getString("email"));
                    hotel.setHotelPhone(rs.getString("phone"));
                    hotel.setLogo(rs.getString("logo"));
                    Star star = new Star();
                    star.setId(rs.getLong("id_star"));
                    star.setStar(rs.getLong("star"));
                    hotel.setStar(star);
                } else {
                    hotel = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return hotel;
    }

    @Override
    public boolean updateHotel(Hotel hotel) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE hotel SET name=?,address=?,phone=?,email=?,star_id=?,logo=? WHERE id_hotel=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, hotel.getHotelName());
                ps.setString(2, hotel.getHotelAddress());
                ps.setString(3, hotel.getHotelPhone());
                ps.setString(4, hotel.getHotelEmail());
                ps.setLong(5, hotel.getStar().getId());
                ps.setString(6,hotel.getLogo());
                ps.setLong(7, hotel.getId());
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
    public boolean deleteHotel(Long hotelId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  hotel SET active = 0 " + " WHERE id_hotel = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, hotelId);
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
    public List<Hotel> searchHotelData(String keyword) throws Exception {
        List<Hotel> hotelList = new ArrayList<Hotel>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_hotel,\n"
                + "        h.name,\n"
                + "         h.address,\n"
                + "         h.phone,\n"
                + "         h.email,h.logo,\n"
                + "         s.id_star id_star,\n"
                + "         s.star\n"
                + "    FROM hotel h\n"
                + "         INNER JOIN star s\n"
                + "            ON h.star_id = s.id_star\n"
                + "   WHERE h.active = 1\n"
                + "AND ( LOWER(name) LIKE LOWER (?) OR LOWER(address) LIKE LOWER (?) OR LOWER(phone) LIKE LOWER (?) OR LOWER(email) LIKE LOWER (?) OR LOWER(star) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("name"));
                    hotel.setHotelAddress(rs.getString("address"));
                    hotel.setHotelEmail(rs.getString("email"));
                    hotel.setHotelPhone(rs.getString("phone"));
                    hotel.setLogo(rs.getString("logo"));
                    Star star = new Star();
                    star.setId(rs.getLong("id_star"));
                    star.setStar(rs.getLong("star"));
                    hotel.setStar(star);
                    hotelList.add(hotel);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return hotelList;
    }
}

