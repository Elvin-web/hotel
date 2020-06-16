package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.HousekeepingDao;
import az.elvin.hotel.model.*;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HousekeepingDaoImpl implements HousekeepingDao {
    @Override
    public List<Housekeeping> getHousekeepingList() throws Exception {
        List<Housekeeping> housekeepingList = new ArrayList<Housekeeping>();
        Connection c = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping,\n"
                + "         h.clean_date,h.remark,\n"
                + "hs.id_housekeeping_status,hs.name housekeeping_status_name,\n"
                + "s.id_staff,s.first_name,s.last_name,\n"
                + "r.id_room,r.number,\n"
                + "rt.id_room_type,rt.room_type,\n"
                + "f.id_floor,f.floor_number \n"
                + "    FROM housekeeping h\n"
                + "         INNER JOIN housekeeping_status hs\n"
                + "            ON h.housekeeping_status_id= hs.id_housekeeping_status\n"
                + "         INNER JOIN staff s\n"
                + "            ON h.staff_id = s.id_staff\n"
                + "         INNER JOIN room r\n"
                + "            ON h.room_id = r.id_room\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.room_type\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "   WHERE h.active = 1\n"
                + "ORDER BY h.id_housekeeping";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Housekeeping housekeeping = new Housekeeping();
                    housekeeping.setId(rs.getLong("id_housekeeping"));
                    housekeeping.setCleanDate(rs.getDate("clean_date"));
                    housekeeping.setRemark(rs.getString("remark"));
                    HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setName(rs.getString("housekeeping_status_name"));
                    housekeeping.setHousekeepingStatus(housekeepingStatus);
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    housekeeping.setStaff(staff);
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    housekeeping.setRoom(room);
                    housekeepingList.add(housekeeping);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeepingList;
    }

    @Override
    public List<Housekeeping> getHousekeepingListByRoomId(Long roomId) throws Exception {
        List<Housekeeping> housekeepingList = new ArrayList<Housekeeping>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping,h.clean_date,h.remark,\n"
                + "hs.id_housekeeping_status,hs.name housekeeping_status_name,\n"
                + "s.id_staff,s.first_name,s.last_name,\n"
                + "r.id_room,r.number,\n"
                + "rt.id_room_type,rt.room_type,\n"
                + "f.id_floor,f.floor_number \n"
                + "    FROM housekeeping h\n"
                + "         INNER JOIN housekeeping_status hs\n"
                + "            ON h.housekeeping_status_id= hs.id_housekeeping_status\n"
                + "         INNER JOIN staff s\n"
                + "            ON h.staff_id = s.id_staff\n"
                + "         INNER JOIN room r\n"
                + "            ON h.room_id = r.id_room\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "   WHERE h.active = 1\n"
                + "AND h.room_id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Housekeeping housekeeping = new Housekeeping();
                    housekeeping.setId(rs.getLong("id_housekeeping"));
                    housekeeping.setCleanDate(rs.getDate("clean_date"));
                    housekeeping.setRemark(rs.getString("remark"));
                    HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setName(rs.getString("housekeeping_status_name"));
                    housekeeping.setHousekeepingStatus(housekeepingStatus);
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    housekeeping.setStaff(staff);
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    housekeeping.setRoom(room);
                    housekeepingList.add(housekeeping);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeepingList;
    }
    @Override
    public List<Housekeeping> getHousekeepingListByRoomId1(Long roomId) throws Exception {
        List<Housekeeping> housekeepingList = new ArrayList<Housekeeping>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping,h.clean_date,h.remark,\n"
                + "hs.id_housekeeping_status,hs.name housekeeping_status_name,\n"
                + "s.id_staff,s.first_name,s.last_name,\n"
                + "r.id_room,r.number,\n"
                + "f.id_floor,f.floor_number,f.name floor_name\n"
                + "    FROM housekeeping h\n"
                + "         INNER JOIN housekeeping_status hs\n"
                + "            ON h.housekeeping_status_id= hs.id_housekeeping_status\n"
                + "         INNER JOIN staff s\n"
                + "            ON h.staff_id = s.id_staff\n"
                + "         INNER JOIN room r\n"
                + "            ON h.room_id = r.id_room\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "   WHERE h.active = 1\n"
                + "AND h.room_id=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Housekeeping housekeeping = new Housekeeping();
                    housekeeping.setId(rs.getLong("id_housekeeping"));
                    housekeeping.setCleanDate(rs.getDate("clean_date"));
                    housekeeping.setRemark(rs.getString("remark"));
                    HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setName(rs.getString("housekeeping_status_name"));
                    housekeeping.setHousekeepingStatus(housekeepingStatus);
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    housekeeping.setStaff(staff);
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    floor.setName(rs.getString("floor_name"));
                    room.setFloor(floor);
                    housekeeping.setRoom(room);
                    housekeepingList.add(housekeeping);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeepingList;
    }


    @Override
    public boolean addHousekeeping(Housekeeping housekeeping) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  housekeeping (housekeeping_status_id,staff_id,room_id,remark,clean_date) " + " VALUES (?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, housekeeping.getHousekeepingStatus().getId());
                ps.setLong(2, housekeeping.getStaff().getId());
                ps.setLong(3, housekeeping.getRoom().getId());
                ps.setString(4, housekeeping.getRemark());
                ps.setDate(5, new java.sql.Date(housekeeping.getCleanDate().getTime()));
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
    public Housekeeping getHousekeepingById(Long housekeepingId) throws Exception {
        Housekeeping housekeeping = new Housekeeping();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping,\n"
                + "         h.clean_date,h.remark,\n"
                + "hs.id_housekeeping_status,hs.name housekeeping_status_name,\n"
                + "s.id_staff,s.first_name,s.last_name,\n"
                + "r.id_room,r.number,\n"
                + "rt.id_room_type,rt.room_type,\n"
                + "f.id_floor,f.floor_number,f.name floor_name \n"
                + "    FROM housekeeping h\n"
                + "         INNER JOIN housekeeping_status hs\n"
                + "            ON h.housekeeping_status_id= hs.id_housekeeping_status\n"
                + "         INNER JOIN staff s\n"
                + "            ON h.staff_id = s.id_staff\n"
                + "         INNER JOIN room r\n"
                + "            ON h.room_id = r.id_room\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "   WHERE h.active = 1\n"
                + "AND h.id_housekeeping=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, housekeepingId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    housekeeping.setId(rs.getLong("id_housekeeping"));
                    housekeeping.setCleanDate(rs.getDate("clean_date"));
                    housekeeping.setRemark(rs.getString("remark"));
                    HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setName(rs.getString("housekeeping_status_name"));
                    housekeeping.setHousekeepingStatus(housekeepingStatus);
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    housekeeping.setStaff(staff);
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    floor.setName(rs.getString("floor_name"));
                    room.setFloor(floor);
                    housekeeping.setRoom(room);
                } else {
                    housekeeping = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeeping;
    }

    @Override
    public boolean updateHousekeeping(Housekeeping housekeeping) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  housekeeping SET housekeeping_status_id=?,staff_id=?,room_id=?,remark=?,clean_date=? WHERE id_housekeeping=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, housekeeping.getHousekeepingStatus().getId());
                ps.setLong(2, housekeeping.getStaff().getId());
                ps.setLong(3, housekeeping.getRoom().getId());
                ps.setString(4, housekeeping.getRemark());
                ps.setDate(5, new java.sql.Date(housekeeping.getCleanDate().getTime()));
                ps.setLong(6, housekeeping.getId());
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
    public boolean deleteHousekeeping(Long housekeepingId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  housekeeping SET active = 0 " + " WHERE id_housekeeping = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, housekeepingId);
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
    public List<Housekeeping> searchHousekeepingData(String keyword) throws Exception {
        List<Housekeeping> housekeepingList = new ArrayList<Housekeeping>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping,\n"
                + "         h.clean_date,h.remark,\n"
                + "hs.id_housekeeping_status,hs.name housekeeping_status_name,\n"
                + "s.id_staff,s.first_name,s.last_name,\n"
                + "r.id_room,r.number,\n"
                + "rt.id_room_type,rt.room_type,\n"
                + "f.id_floor,f.floor_number \n"
                + "    FROM housekeeping h\n"
                + "         INNER JOIN housekeeping_status hs\n"
                + "            ON h.housekeeping_status_id= hs.id_housekeeping_status\n"
                + "         INNER JOIN staff s\n"
                + "            ON h.staff_id = s.id_staff\n"
                + "         INNER JOIN room r\n"
                + "            ON h.room_id = r.id_room\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.room_type\n"
                + "         INNER JOIN floor f\n"
                + "            ON r.floor_id = f.id_floor\n"
                + "   WHERE h.active = 1\n"
                + "AND ( LOWER(clean_date) LIKE LOWER (?)\n "
                + "OR LOWER(housekeeping_status_name) LIKE LOWER (?)\n"
                + "OR LOWER(first_name) LIKE LOWER (?)\n "
                + "OR LOWER(last_name) LIKE LOWER (?)\n "
                + "OR LOWER(number) LIKE LOWER (?)\n"
                + "OR LOWER(room_type) LIKE LOWER (?)\n "
                + "OR LOWER(floor_number) LIKE LOWER (?))"
                + "OR LOWER(remark) LIKE LOWER (?))";
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    Housekeeping housekeeping = new Housekeeping();
                    housekeeping.setId(rs.getLong("id_housekeeping"));
                    housekeeping.setCleanDate(rs.getDate("clean_date"));
                    housekeeping.setRemark(rs.getString("remark"));
                    HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setName(rs.getString("housekeeping_status_name"));
                    housekeeping.setHousekeepingStatus(housekeepingStatus);
                    Staff staff = new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    housekeeping.setStaff(staff);
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    room.setRoomType(roomType);
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setFloorNumber(rs.getString("floor_number"));
                    room.setFloor(floor);
                    housekeeping.setRoom(room);
                    housekeepingList.add(housekeeping);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeepingList;
    }

}
