package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.DictionaryDao;
import az.elvin.hotel.model.*;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {
    @Override
    public List<Dictionary> getDictionaryList() throws Exception {
        List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ra.id_room_amenities,r.id_room,r.number,f.id_floor,f.name,f.floor_number,\n"
                + "rs.id_room_status,rs.room_status,\n"
                + "a.id_amenities,a.name amenities_name,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "FROM room_amenities ra\n"
                + "INNER JOIN room r\n"
                + "ON ra.room_id = r.id_room\n"
                + "INNER JOIN amenities a\n"
                + "ON ra.amenities_id = a.id_amenities\n"
                + "INNER JOIN floor f\n"
                + "ON r.floor_id = f.id_floor\n"
                + "INNER JOIN room_status rs\n"
                + "ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "WHERE ra.active = 1\n"
                + "ORDER BY ra.id_room_amenities";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Dictionary dictionary = new Dictionary();
                    dictionary.setId(rs.getLong("id_room_amenities"));
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    dictionary.setRoom(room);
                    Amenities amenities = new Amenities();
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("amenities_name"));
                    dictionary.setAmenities(amenities);

                    dictionaryList.add(dictionary);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return dictionaryList;
    }

    @Override
    public boolean addDictionary(Dictionary dictionary) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastRoom = "SELECT MAX(id_room) id_room FROM room";
        // String lastAmenities = "SELECT MAX(ID) ID FROM AMENITIES";
       /* String sqlRoom = "INSERT INTO ROOM(ID,ROOM_NUMBER,FLOOR_ID,ROOM_TYPE_ID \n)"
                + " VALUES (ROOM_SEQ.NEXTVAL,?,?,?)";*/
        String sqlDictionary = "INSERT INTO  room_amenities (active,room_id,amenities_id\n) "
                + " VALUES (?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
               /* ps = c.prepareStatement(sqlRoom);
                Room room = new Room();
                ps.setLong(1, dictionary.getRoom().getRoomNumber());
                ps.setLong(2, dictionary.getRoom().getFloor().getId());
                ps.setLong(3,dictionary.getRoom().getRoomType().getId());
                ps.execute();*/

                ps = c.prepareStatement(lastRoom);
                rs = ps.executeQuery();
                int roomId = 0;
                if (rs.next()) {
                    roomId = rs.getInt("id_room");
                }
                /*ps = c.prepareStatement(lastAmenities);
                rs = ps.executeQuery();
                int amenitiesId = 0;
                if (rs.next()) {
                    amenitiesId = rs.getInt("ID");
                }*/
                ps = c.prepareStatement(sqlDictionary);
                ps.setInt(1, dictionary.getActive());
                ps.setInt(2, roomId);
                // ps.setInt(2, amenitiesId);
                ps.setLong(3, dictionary.getAmenities().getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return result;
    }

    @Override
    public boolean updateDictionary(Dictionary dictionary) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastRoom = "SELECT MAX(id_room) id_room FROM room";
        //  String lastAmenities = "SELECT MAX(id_amenities) id_amenities FROM amenities";
        //String sqlRoom = "UPDATE  room SET number=?,floor_id=?,room_type_id=? WHERE id_room=?";

        String sqlDictionary = "UPDATE  room_amenities SET room_id=?,amenities_id=?,active=? WHERE id_room_amenities=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
              /*  ps = c.prepareStatement(sqlRoom);
                Room room = new Room();
                ps.setLong(1, dictionary.getRoom().getRoomNumber());
                ps.setLong(2, dictionary.getRoom().getFloor().getId());
                ps.setLong(3, dictionary.getRoom().getRoomType().getId());
                ps.setLong(4, dictionary.getRoom().getId());
                ps.execute();*/

               /* ps = c.prepareStatement(lastRoom);
                rs = ps.executeQuery();
                int roomId = 0;
                if (rs.next()) {
                    roomId = rs.getInt("id_room");
                }*/
               /* ps = c.prepareStatement(lastAmenities);
                rs = ps.executeQuery();
                int amenitiesId = 0;
                if (rs.next()) {
                    amenitiesId = rs.getInt("id_amenities");
                }*/
                ps = c.prepareStatement(sqlDictionary);
                ps.setLong(1, dictionary.getRoom().getId());
                ps.setLong(2, dictionary.getAmenities().getId());
                ps.setInt(3, dictionary.getActive());
                ps.setLong(4, dictionary.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return result;
    }

    @Override
    public Dictionary getDictionaryById(Long dictionaryId) throws Exception {
        return null;
    }

    @Override
    public Dictionary getDictionaryByAmenitiesId(Long amenitiesId, Long roomId) throws Exception {
        Dictionary dictionary = new Dictionary();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ra.id_room_amenities,r.id_room,r.number,f.id_floor,f.name,f.floor_number,\n"
                + "rs.id_room_status,rs.room_status,\n"
                + "a.id_amenities,a.name amenities_name,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "FROM room_amenities ra\n"
                + "INNER JOIN room r\n"
                + "ON ra.room_id = r.id_room\n"
                + "INNER JOIN amenities a\n"
                + "ON ra.amenities_id = a.id_amenities\n"
                + "INNER JOIN floor f\n"
                + "ON r.floor_id = f.id_floor\n"
                + "INNER JOIN room_status rs\n"
                + "ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "WHERE a.id_amenities=? AND r.id_room=?\n";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, amenitiesId);
                ps.setLong(2, roomId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    dictionary.setId(rs.getLong("id_room_amenities"));
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    dictionary.setRoom(room);
                    Amenities amenities = new Amenities();
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("amenities_name"));
                    dictionary.setAmenities(amenities);
                } else {
                    dictionary = null;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return dictionary;
    }

    @Override
    public List<Dictionary> getDictionaryByRoomId(Long roomId) throws Exception {
        List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ra.id_room_amenities,r.id_room,r.number,f.id_floor,f.name,f.floor_number,ra.active,\n"
                + "rs.id_room_status,rs.room_status,\n"
                + "a.id_amenities,a.name amenities_name,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "FROM room_amenities ra\n"
                + "INNER JOIN room r\n"
                + "ON ra.room_id = r.id_room\n"
                + "INNER JOIN amenities a\n"
                + "ON ra.amenities_id = a.id_amenities\n"
                + "INNER JOIN floor f\n"
                + "ON r.floor_id = f.id_floor\n"
                + "INNER JOIN room_status rs\n"
                + "ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "WHERE r.id_room=?\n";

               /* + "WHERE ra.active = 1\n"
                + "AND r.id_room=?";*/
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Dictionary dictionary = new Dictionary();
                    dictionary.setId(rs.getLong("id_room_amenities"));
                    dictionary.setActive(rs.getInt("active"));
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    dictionary.setRoom(room);
                    Amenities amenities = new Amenities();
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("amenities_name"));
                    dictionary.setAmenities(amenities);
                    dictionaryList.add(dictionary);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return dictionaryList;
    }
    /*@Override
    public List<Dictionary> getDictionaryByRoomId(Long roomId) throws Exception {
        List<Dictionary> dictionaryList=new ArrayList<Dictionary>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT ra.id_room_amenities,r.id_room,r.number,f.id_floor,f.name,f.floor_number,\n"
                + "rs.id_room_status,rs.room_status,\n"
                + "a.id_amenities,a.name amenities_name,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "FROM room_amenities ra\n"
                + "INNER JOIN room r\n"
                + "ON ra.room_id = r.id_room\n"
                + "INNER JOIN amenities a\n"
                + "ON ra.amenities_id = a.id_amenities\n"
                + "INNER JOIN floor f\n"
                + "ON r.floor_id = f.id_floor\n"
                + "INNER JOIN room_status rs\n"
                + "ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "WHERE ra.active = 1\n"
                + "AND R.ID=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Dictionary dictionary = new Dictionary();
                    dictionary.setId(rs.getLong("id_room_amenities"));
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    dictionary.setRoom(room);
                    Amenities amenities = new Amenities();
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("amenities_name"));
                    dictionary.setAmenities(amenities);
                    dictionaryList.add(dictionary);
                } else {
                    dictionaryList = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return dictionaryList;
    }*/


    @Override
    public boolean deleteDictionary(Long dictionaryId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  room_amenities SET active = 0 " + " WHERE id_room_amenities = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, dictionaryId);
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
    public List<Dictionary> searchDictionaryData(String keyword) throws Exception {
        List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ra.id_room_amenities,r.id_room,r.number,f.id_floor,f.name,f.floor_number,\n"
                + "rs.id_room_status,rs.room_status,\n"
                + "a.id_amenities,a.name amenities_name,\n"
                + "rt.id_room_type,rt.room_type\n"
                + "FROM room_amenities ra\n"
                + "INNER JOIN room r\n"
                + "ON ra.room_id = r.id_room\n"
                + "INNER JOIN amenities a\n"
                + "ON ra.amenities_id = a.id_amenities\n"
                + "INNER JOIN floor f\n"
                + "ON r.floor_id = f.id_floor\n"
                + "INNER JOIN room_status rs\n"
                + "ON r.room_status_id = rs.id_room_status\n"
                + "INNER JOIN room_type rt\n"
                + "ON r.room_type_id = rt.id_room_type\n"
                + "WHERE ra.active = 1\n"
                + "AND ( LOWER(number) LIKE LOWER (?)\n "
                + "OR LOWER(name) LIKE LOWER (?)\n"
                + "OR LOWER(room_status) LIKE LOWER (?)\n "
                + "OR LOWER(amenities_name) LIKE LOWER (?) OR LOWER(floor_number) LIKE LOWER (?) OR LOWER(room_type) LIKE LOWER (?))";
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    Dictionary dictionary = new Dictionary();
                    dictionary.setId(rs.getLong("id_room_amenities"));
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("id_room_status"));
                    roomStatus.setRoomStatus(rs.getString("room_status"));
                    room.setRoomStatus(roomStatus);
                    dictionary.setRoom(room);
                    Amenities amenities = new Amenities();
                    amenities.setId(rs.getLong("id_amenities"));
                    amenities.setName(rs.getString("amenities_name"));
                    dictionary.setAmenities(amenities);

                    dictionaryList.add(dictionary);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return dictionaryList;
    }
}
