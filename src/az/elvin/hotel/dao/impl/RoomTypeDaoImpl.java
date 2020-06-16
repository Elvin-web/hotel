package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.RoomTypeDao;
import az.elvin.hotel.model.*;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDaoImpl implements RoomTypeDao {
    @Override
    public List<RoomType> getRoomTypeList() throws Exception {

        List<RoomType> roomTypeList = new ArrayList<RoomType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room_type,r.room_type,r.slug,r.short_code,\n"
                + "r.description,r.image,\n"
                + "rs.id_room_structure,rs.higher_occupancy,rs.extra_bed,rs.kids_occupancy,rs.base_occupancy,\n"
                + "p.id_price,p.base_price,p.extra_bed_price\n"
                + "FROM room_type r\n"
                + "INNER JOIN room_structure rs\n"
                + "ON r.room_structure_id = rs.id_room_structure\n"
                + "INNER JOIN price p\n"
                + "ON r.price_id = p.id_price\n"
                + "WHERE r.active = 1\n"
                + "ORDER BY r.id_room_type";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    roomType.setSlug(rs.getString("slug"));
                    roomType.setShortCode(rs.getString("short_code"));
                    roomType.setDescription(rs.getString("description"));
                    roomType.setImage(rs.getString("image"));
                    RoomStructure roomStructure = new RoomStructure();
                    roomStructure.setId(rs.getLong("id_room_structure"));
                    roomStructure.setBaseOccupancy(rs.getInt("base_occupancy"));
                    roomStructure.setHigherOccupancy(rs.getInt("higher_occupancy"));
                    roomStructure.setExtaBed(rs.getInt("extra_bed"));
                    roomStructure.setKidsOccupancy(rs.getInt("kids_occupancy"));
                    roomType.setRoomStructure(roomStructure);
                    Price price = new Price();
                    price.setId(rs.getLong("id_price"));
                    price.setExtraBedPrice(rs.getDouble("extra_bed_price"));
                    price.setBasePrice(rs.getDouble("base_price"));
                    roomType.setPrice(price);
                    roomTypeList.add(roomType);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomTypeList;
    }

    @Override
    public boolean addRoomType(RoomType roomType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastStructure = "SELECT MAX(id_room_structure) id_room_structure FROM room_structure";
        String lastPrice = "SELECT MAX(id_price) id_price FROM price";
        // String lastDictionary = "SELECT MAX(ID) ID FROM DICTIONARY";
        String sqlRoomStructure = "INSERT INTO room_structure(base_occupancy,higher_occupancy,kids_occupancy,extra_bed \n)"
                + " VALUES (?,?,?,?)";
        String sqlPrice = "INSERT INTO price(extra_bed_price,base_price \n) VALUES (?,?)";
        // String sqlDictionary = "INSERT INTO DICTIONARY(ID,ROOM_ID) VALUES (DICTIONARY_SEQ.NEXTVAL,?)";
        String sqlRoomtype = "INSERT INTO  room_type (room_type,slug,short_code,description,image,room_structure_id,price_id\n) "
                + " VALUES (?,?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sqlRoomStructure);
                RoomStructure roomStructure = new RoomStructure();
                ps.setInt(1, roomType.getRoomStructure().getBaseOccupancy());
                ps.setInt(2, roomType.getRoomStructure().getHigherOccupancy());
                ps.setInt(3, roomType.getRoomStructure().getKidsOccupancy());
                ps.setInt(4, roomType.getRoomStructure().getExtaBed());
                ps.execute();

                ps = c.prepareStatement(sqlPrice);
                Price price = new Price();
                ps.setDouble(1, roomType.getPrice().getExtraBedPrice());
                ps.setDouble(2, roomType.getPrice().getBasePrice());
                ps.execute();

            /*    ps = c.prepareStatement(sqlDictionary);
                Dictionary dictionary = new Dictionary();
                ps.setLong(1, roomType.getDictionary().getRoom().getId());
*/
                ps = c.prepareStatement(lastStructure);
                rs = ps.executeQuery();
                int structureId = 0;
                if (rs.next()) {
                    structureId = rs.getInt("id_room_structure");
                }
                ps = c.prepareStatement(lastPrice);
                rs = ps.executeQuery();
                int priceId = 0;
                if (rs.next()) {
                    priceId = rs.getInt("id_price");
                }
               /* ps = c.prepareStatement(lastDictionary);
                rs = ps.executeQuery();
                int dictionaryId = 0;
                if (rs.next()) {
                    dictionaryId = rs.getInt("ID");
                }*/
                ps = c.prepareStatement(sqlRoomtype);
                ps.setString(1, roomType.getRoomType());
                ps.setString(2, roomType.getSlug());
                ps.setString(3, roomType.getShortCode());
                ps.setString(4, roomType.getDescription());
                ps.setString(5, roomType.getImage());
                ps.setInt(6, structureId);
                ps.setInt(7, priceId);
                //ps.setInt(7, dictionaryId);
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
    public RoomType getRoomTypeById(Long RoomTypeId) throws Exception {
        RoomType roomType = new RoomType();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room_type,r.room_type,r.slug,r.short_code,\n"
                + "r.description,r.image,\n"
                + "rs.id_room_structure,rs.higher_occupancy,rs.extra_bed,rs.kids_occupancy,rs.base_occupancy,\n"
                + "p.id_price,p.base_price,p.extra_bed_price\n"
                + "FROM room_type r\n"
                + "INNER JOIN room_structure rs\n"
                + "ON r.room_structure_id = rs.id_room_structure\n"
                + "INNER JOIN price p\n"
                + "ON r.price_id = p.id_price\n"
                + "WHERE r.active = 1\n"
                + "AND r.id_room_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, RoomTypeId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    roomType.setSlug(rs.getString("slug"));
                    roomType.setShortCode(rs.getString("short_code"));
                    roomType.setDescription(rs.getString("description"));
                    roomType.setImage(rs.getString("image"));
                    RoomStructure roomStructure = new RoomStructure();
                    roomStructure.setId(rs.getLong("id_room_structure"));
                    roomStructure.setBaseOccupancy(rs.getInt("base_occupancy"));
                    roomStructure.setHigherOccupancy(rs.getInt("higher_occupancy"));
                    roomStructure.setExtaBed(rs.getInt("extra_bed"));
                    roomStructure.setKidsOccupancy(rs.getInt("kids_occupancy"));
                    roomType.setRoomStructure(roomStructure);
                    Price price = new Price();
                    price.setId(rs.getLong("id_price"));
                    price.setExtraBedPrice(rs.getDouble("extra_bed_price"));
                    price.setBasePrice(rs.getDouble("base_price"));
                    roomType.setPrice(price);
                } else {
                    roomType = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomType;
    }

    @Override
    public boolean updateRoomType(RoomType roomType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastStructure = "SELECT MAX(id_room_structure) id_room_structure FROM room_structure";
        String lastPrice = "SELECT MAX(id_price) id_price FROM price";
        //  String lastDictionary = "SELECT MAX(ID) ID FROM DICTIONARY";
        String sqlRoomStructure = "UPDATE room_structure SET base_occupancy=?,higher_occupancy=?,kids_occupancy=?,extra_bed=? WHERE id_room_structure=?";
        String sqlPrice = "UPDATE price SET extra_bed_price=?,base_price=? WHERE id_price=?";
        //String sqlDictionary = "UPDATE DICTIONARY SET ROOM_ID=? WHERE ID=?";

        String sqlRoomtype = "UPDATE  room_type SET room_type=?,slug=?,short_code=?,description=?,\n"
                + "room_structure_id=?,price_id=?,image=? WHERE id_room_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sqlRoomStructure);
                ps = c.prepareStatement(sqlRoomStructure);
                RoomStructure roomStructure = new RoomStructure();
                ps.setInt(1, roomType.getRoomStructure().getBaseOccupancy());
                ps.setInt(2, roomType.getRoomStructure().getHigherOccupancy());
                ps.setInt(3, roomType.getRoomStructure().getKidsOccupancy());
                ps.setInt(4, roomType.getRoomStructure().getExtaBed());
                ps.setLong(5, roomType.getRoomStructure().getId());
                ps.execute();

                ps = c.prepareStatement(sqlPrice);
                Price price = new Price();
                ps.setDouble(1, roomType.getPrice().getExtraBedPrice());
                ps.setDouble(2, roomType.getPrice().getBasePrice());
                ps.setLong(3, roomType.getPrice().getId());
                ps.execute();

               /* ps = c.prepareStatement(sqlDictionary);
                Dictionary dictionary = new Dictionary();
                ps.setLong(1, roomType.getDictionary().getRoom().getId());
                ps.setLong(2, roomType.getDictionary().getId());*/

                ps = c.prepareStatement(lastStructure);
                rs = ps.executeQuery();
                int structureId = 0;
                if (rs.next()) {
                    structureId = rs.getInt("id_room_structure");
                }
                ps = c.prepareStatement(lastPrice);
                rs = ps.executeQuery();
                int priceId = 0;
                if (rs.next()) {
                    priceId = rs.getInt("id_price");
                }
               /* ps = c.prepareStatement(lastDictionary);
                rs = ps.executeQuery();
                int dictionaryId = 0;
                if (rs.next()) {
                    dictionaryId = rs.getInt("ID");
                }*/
                ps = c.prepareStatement(sqlRoomtype);
                ps.setString(1, roomType.getRoomType());
                ps.setString(2, roomType.getSlug());
                ps.setString(3, roomType.getShortCode());
                ps.setString(4, roomType.getDescription());
                ps.setInt(5, structureId);
                ps.setInt(6, priceId);
                // ps.setInt(7, dictionaryId);
                ps.setString(7, roomType.getImage());
                ps.setLong(8, roomType.getId());
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
    public boolean deleteRoomType(Long roomTypeId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  room_type SET active = 0 " + " WHERE id_room_type = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, roomTypeId);
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
    public List<RoomType> searchRoomTypeData(String keyword) throws Exception {
        List<RoomType> roomTypeList = new ArrayList<RoomType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_room_type,r.room_type,r.slug,r.short_code,\n"
                + "r.description,\n"
                + "rs.id_room_structure,rs.higher_occupancy,rs.extra_bed,rs.kids_occupancy,rs.base_occupancy,\n"
                + "p.id_price,p.base_price,p.extra_bed_price\n"
                + "FROM room_type r\n"
                + "INNER JOIN room_structure rs\n"
                + "ON r.room_structure_id = rs.id_room_structure\n"
                + "INNER JOIN price p\n"
                + "ON r.price_id = p.id_price\n"
                + "WHERE r.active = 1\n"
                + "AND ( LOWER(room_type) LIKE LOWER (?) OR LOWER(slug) LIKE LOWER (?)\n "
                + " OR LOWER(short_code) LIKE LOWER (?) OR LOWER(description) LIKE LOWER (?)\n "
                + "OR LOWER(base_occupancy) LIKE LOWER (?) OR  LOWER(higher_occupancy) LIKE LOWER (?)\n "
                + "OR LOWER(extra_bed) LIKE LOWER (?)  OR LOWER(kids_occupancy) LIKE LOWER (?)\n "
                + "OR LOWER(base_price) LIKE LOWER (?) \n "
                + "OR LOWER(extra_bed_price) LIKE LOWER (?))  ";
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    roomType.setSlug(rs.getString("slug"));
                    roomType.setShortCode(rs.getString("short_code"));
                    roomType.setDescription(rs.getString("description"));
                    RoomStructure roomStructure = new RoomStructure();
                    roomStructure.setId(rs.getLong("id_room_structure"));
                    roomStructure.setBaseOccupancy(rs.getInt("base_occupancy"));
                    roomStructure.setHigherOccupancy(rs.getInt("higher_occupancy"));
                    roomStructure.setExtaBed(rs.getInt("extra_bed"));
                    roomStructure.setKidsOccupancy(rs.getInt("kids_occupancy"));
                    roomType.setRoomStructure(roomStructure);
                    Price price = new Price();
                    price.setId(rs.getLong("id_price"));
                    price.setExtraBedPrice(rs.getDouble("extra_bed_price"));
                    price.setBasePrice(rs.getDouble("base_price"));
                    roomType.setPrice(price);
                    roomTypeList.add(roomType);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roomTypeList;
    }

    @Override
    public Long roomTypeAcount() throws Exception {
        Long roomTypeAcount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  COUNT(id_room_type) AS roomTypeCnt"
                + "    FROM room_type \n"
                + "   WHERE active = 1\n";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    roomTypeAcount = rs.getLong("roomTypeCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return roomTypeAcount;
    }
}
