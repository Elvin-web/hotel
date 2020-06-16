package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.RoomStatusDao;
import az.elvin.hotel.model.RoomStatus;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomStatusDaoImpl implements RoomStatusDao {
    @Override
    public List<RoomStatus> getRoomStatusList() throws Exception {
        List<RoomStatus> roomStatusList = new ArrayList<RoomStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room_status,r.room_status\n"
                + "    FROM room_status r\n"
                + "   WHERE r.active = 1\n"
                + "ORDER BY r.id_room_status";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    roomStatusList.add(roomStatus);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomStatusList;
    }

    @Override
    public boolean addRoomStatus(RoomStatus roomStatus) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  room_status (room_status) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, roomStatus.getRoomStatus());
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
    public RoomStatus getRoomStatusById(Long roomStatusId) throws Exception {
        RoomStatus roomStatus = new RoomStatus();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room_status,r.room_status\n"
                + "    FROM room_status r\n"
                + "   WHERE r.active = 1\n"
                + "AND r.id_room_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomStatusId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                } else {
                    roomStatus = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomStatus;
    }

    @Override
    public boolean updateRoomStatus(RoomStatus roomStatus) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  room_status SET room_status=?  WHERE id_room_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, roomStatus.getRoomStatus());
                ps.setLong(2, roomStatus.getId());
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
    public boolean deleteRoomStatus(Long roomStatusId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  room_status SET active = 0 " + " WHERE id_room_status = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomStatusId);
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
    public List<RoomStatus> searchRoomStatusData(String keyword) throws Exception {
        List<RoomStatus> roomStatusList = new ArrayList<RoomStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT r.id_room_status,r.room_status\n"
                + "    FROM room_status r\n"
                + "   WHERE r.active = 1\n"
                + "AND ( LOWER(room_status) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    roomStatusList.add(roomStatus);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomStatusList;
    }

}
