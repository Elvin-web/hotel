package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.AmenitiesDao;
import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.model.Amenities;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AmenitiesDaoImpl implements AmenitiesDao {
    @Override
    public List<Amenities> getAmenitiesList() throws Exception {
        List<Amenities> amenitiesList = new ArrayList<Amenities>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT a.id_amenities,\n"
                + "         a.name,\n"
                + "         a.description,\n"
                + "         a.action\n"
                + "    FROM amenities a \n"
                + "   WHERE a.active = 1\n"
                + "ORDER BY a.id_amenities";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Amenities amenities = new Amenities();
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("name"));
                    amenities.setDescription(rs.getString("description"));
                    amenities.setAction(rs.getInt("action"));
                    amenitiesList.add(amenities);
                }

            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return amenitiesList;
    }

    @Override
    public boolean addAmenities(Amenities amenities) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  amenities (name,description,action,image) " + " VALUES (?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, amenities.getName());
                ps.setString(2, amenities.getDescription());
                ps.setInt(3, amenities.getAction());
                ps.setString(4,amenities.getImage());
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
    public Amenities getAmenitiesById(Long amenitiesId) throws Exception {
        Amenities amenities = new Amenities();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT a.id_amenities, a.name,a.description,a.action,a.image\n"
                + "    FROM amenities a\n"
                + " WHERE a.active = 1 AND a.id_amenities=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, amenitiesId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("name"));
                    amenities.setDescription(rs.getString("description"));
                    amenities.setAction(rs.getInt("action"));
                    amenities.setImage(rs.getString("image"));
                } else {
                    amenities = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return amenities;
    }

    @Override
    public boolean updateAmenities(Amenities amenities) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE amenities SET name=?,description=?,action=?,image=? WHERE id_amenities=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, amenities.getName());
                ps.setString(2, amenities.getDescription());
                ps.setInt(3, amenities.getAction());
                ps.setString(4, amenities.getImage());
                ps.setLong(5, amenities.getId());
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
    public boolean deleteAmenities(Long amenitiesId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  amenities SET active = 0 " + " WHERE id_amenities = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, amenitiesId);
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
    public List<Amenities> searchAmenitiesData(String keyword) throws Exception {
        List<Amenities> amenitiesList = new ArrayList<Amenities>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT a.id_amenities, a.name,a.description,a.action\n"
                + "    FROM amenities a\n"
                + "WHERE a.active = 1 AND ( LOWER(name) LIKE LOWER (?) OR LOWER(description) LIKE LOWER (?) OR LOWER(action) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Amenities amenities = new Amenities();
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("name"));
                    amenities.setName(rs.getString("description"));
                    amenities.setAction(rs.getInt("action"));
                    amenitiesList.add(amenities);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return amenitiesList;
    }
}
