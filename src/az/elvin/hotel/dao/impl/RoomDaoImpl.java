package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.RoomDao;
import az.elvin.hotel.model.Floor;
import az.elvin.hotel.model.Room;
import az.elvin.hotel.model.RoomStatus;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public List<Room> getRoomList() throws Exception {
        List<Room> roomList = new ArrayList<Room>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room, r.number,\n"
                + "         f.id_floor,f.name,f.floor_number,\n"
                + "         rs.id_room_status,rs.room_status,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "    FROM room r\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "         INNER JOIN room_status rs\n"
                + "            ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "   WHERE r.active = 1\n"
                + "ORDER BY r.id_room";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    roomList.add(room);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomList;
    }

    @Override
    public boolean addRoom(Room room) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  room (number,floor_id,room_type_id) " + " VALUES (?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, room.getRoomNumber());
                ps.setLong(2, room.getFloor().getId());
                ps.setLong(3, room.getRoomType().getId());
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
    public Room getRoomById(Long roomId) throws Exception {
        Room room = new Room();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room, r.number,\n"
                + "         f.id_floor,f.name,f.floor_number,\n"
                + "         rs.id_room_status,rs.room_status,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "    FROM room r\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "         INNER JOIN room_status rs\n"
                + "            ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "   WHERE r.active = 1\n"
                + "AND r.id_room=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                } else {
                    room = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return room;
    }

    @Override
    public boolean updateRoom(Room room) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  room SET number=?,floor_id=?,room_type_id=?,room_status_id=? WHERE id_room=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, room.getRoomNumber());
                ps.setLong(2, room.getFloor().getId());
                ps.setLong(3, room.getRoomType().getId());
                ps.setLong(4, 1);
                ps.setLong(5, room.getId());
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
    public boolean deleteRoom(Long roomId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  room SET active = 0 " + " WHERE id_room = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
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
    public List<Room> searchRoomData(String keyword) throws Exception {
        List<Room> roomList = new ArrayList<Room>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room, r.number,\n"
                + "         f.id_floor,f.name,f.floor_number,\n"
                + "         rs.id_room_status,rs.room_status,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "    FROM room r\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "         INNER JOIN room_status rs\n"
                + "            ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "   WHERE r.active = 1\n"
                + "AND ( LOWER(number) LIKE LOWER (?) OR LOWER(name) LIKE LOWER (?) OR LOWER(floor_number) LIKE LOWER (?) OR LOWER(room_status) LIKE LOWER (?) OR LOWER(room_type) LIKE LOWER (?))";
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
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    roomList.add(room);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomList;
    }

    @Override
    public Long roomAcount() throws Exception {
        Long roomAcount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  COUNT(id_room) AS roomCnt"
                + "    FROM room \n"
                + "   WHERE active = 1\n";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    roomAcount = rs.getLong("roomCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return roomAcount;
    }
}
