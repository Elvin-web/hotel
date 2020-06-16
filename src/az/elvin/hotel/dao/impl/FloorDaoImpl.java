package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.FloorDao;
import az.elvin.hotel.model.Floor;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FloorDaoImpl implements FloorDao {
    @Override
    public List<Floor> getFloorList() throws Exception {
        List<Floor> floorList = new ArrayList<Floor>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT f.id_floor,\n"
                + "         f.name,f.action,\n"
                + "         f.floor_number,f.description\n"
                + "    FROM floor f\n"
                + "WHERE f.active = 1 ORDER BY f.id_floor";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setAction(rs.getInt("action"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    floor.setDescription(rs.getString("description"));
                    floorList.add(floor);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return floorList;
    }

    @Override
    public boolean addFloor(Floor floor) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  floor (name,floor_number,action,description) " + " VALUES (?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, floor.getName());
                ps.setString(2, floor.getFloorNumber());
                ps.setInt(3, floor.getAction());
                ps.setString(4, floor.getDescription());
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
    public Floor getFloorById(Long floorId) throws Exception {
        Floor floor = new Floor();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT f.id_floor,\n"
                + "         f.name,f.action,\n"
                + "         f.floor_number,f.description\n"
                + "    FROM floor f\n"
                + "WHERE f.active = 1 AND f.id_floor=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, floorId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    floor.setAction(rs.getInt("action"));
                    floor.setDescription(rs.getString("description"));
                } else {
                    floor = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return floor;
    }

    @Override
    public boolean updateFloor(Floor floor) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  floor SET name=?,floor_number=?,action=?,description=? WHERE id_floor=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, floor.getName());
                ps.setString(2, floor.getFloorNumber());
                ps.setInt(3, floor.getAction());
                ps.setString(4, floor.getDescription());
                ps.setLong(5, floor.getId());
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
    public boolean deleteFloor(Long floorId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  floor SET active = 0 " + " WHERE id_floor = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, floorId);
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
    public List<Floor> searchFloorData(String keyword) throws Exception {
        List<Floor> floorList = new ArrayList<Floor>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT f.id_floor,\n"
                + "         f.name,f.action,\n"
                + "         f.floor_number,f.description\n"
                + "    FROM floor f\n"
                + "WHERE f.active=1 AND( LOWER(name) LIKE LOWER (?) OR LOWER(floor_number) LIKE LOWER (?) OR LOWER(action) LIKE LOWER (?) OR LOWER(description) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    floor.setAction(rs.getInt("action"));
                    floor.setDescription(rs.getString("description"));
                    floorList.add(floor);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return floorList;
    }

    @Override
    public Long floorAcount() throws Exception {
        Long floorAcount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  COUNT(id_floor) AS floorCnt"
                + "    FROM floor \n"
                + "   WHERE active = 1\n";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    floorAcount = rs.getLong("floorCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return floorAcount;
    }

}
