package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PositionDao;
import az.elvin.hotel.model.Position;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PositionDaoImpl implements PositionDao {
    @Override
    public List<Position> getPositionList() throws Exception {
        List<Position> positionList = new ArrayList<Position>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_position, p.position_value\n"
                + "    FROM position p\n"
                + "   WHERE p.active = 1\n"
                + "ORDER BY p.id_position";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Position position = new Position();
                    position.setId(rs.getLong("id_position"));
                    position.setPositionValue(rs.getString("position_value"));
                    positionList.add(position);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return positionList;
    }

    @Override
    public boolean addPosition(Position position) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  position (position_value) VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, position.getPositionValue());
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
    public Position getPositionById(Long positionId) throws Exception {
        Position position = new Position();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_position, p.position_value\n"
                + "    FROM position p\n"
                + "   WHERE p.active = 1\n"
                + "AND p.id_position=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, positionId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    position.setId(rs.getLong("id_position"));
                    position.setPositionValue(rs.getString("position_value"));
                } else {
                    position = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return position;
    }

    @Override
    public boolean updatePosition(Position position) throws Exception {

        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  position SET position_value=? WHERE id_position=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, position.getPositionValue());
                ps.setLong(2, position.getId());
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
    public boolean deletePosition(Long positionId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  position SET active = 0 " + " WHERE id_position = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, positionId);
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
    public List<Position> searchPositionData(String keyword) throws Exception {
        List<Position> positionList = new ArrayList<Position>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_position, p.position_value\n"
                + "    FROM position p\n"
                + "   WHERE p.active = 1\n"
                + "AND ( LOWER(position_value) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Position position = new Position();
                    position.setId(rs.getLong("id_position"));
                    position.setPositionValue(rs.getString("position_value"));
                    positionList.add(position);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return positionList;
    }

}
