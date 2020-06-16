package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.Services_RoomTypeDao;
import az.elvin.hotel.model.PriceType;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.model.Services;
import az.elvin.hotel.model.Services_RoomType;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Services_RoomTypeDaoImpl implements Services_RoomTypeDao {

    @Override
    public List<Services_RoomType> getServices_RoomTypeList() throws Exception {
        List<Services_RoomType> services_roomTypeList = new ArrayList<Services_RoomType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sr.id_services_room_type,s.id_services,\n"
                + "         s.name,s.amount,s.description,s.action,\n"
                + "rt.id_room_type,rt.room_type,\n"
                + "pt.id_price_type,pt.price_type \n"
                + "    FROM services_room_type sr\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON sr.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN services s\n"
                + "            ON sr.services_id = s.id_services\n"
                + "         INNER JOIN price_type pt\n"
                + "            ON s.price_type_id = pt.id_price_type\n"
                + "   WHERE sr.active = 1\n"
                + "ORDER BY sr.id_services_room_type";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Services_RoomType services_roomType = new Services_RoomType();
                    services_roomType.setId(rs.getLong("id_services_room_type"));
                    Services services = new Services();
                    services.setId(rs.getLong("id_services"));
                    services.setName(rs.getString("name"));
                    services.setAmount(rs.getDouble("amount"));
                    services.setDescription(rs.getString("description"));
                    services.setAction(rs.getInt("action"));
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    services.setPriceType(priceType);
                    services_roomType.setServices(services);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    services_roomType.setRoomType(roomType);

                    services_roomTypeList.add(services_roomType);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return services_roomTypeList;
    }

    @Override
    public boolean addServices_RoomType(Services_RoomType services_roomType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
       // String lastRoomType = "SELECT MAX(id_room_type) id_room_type FROM room_type";
        String lastServices = "SELECT MAX(id_services) id_services FROM services";
        //String sqlServices = "INSERT INTO  services (name,price_type_id,amount,description,action) VALUES (?,?,?,?,?)";
        String sqlServices_RoomType = "INSERT INTO  services_room_type (room_type_id,services_id) VALUES (?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
               /* ps = c.prepareStatement(sqlServices);
                Services_RoomType servicesRoomType = new Services_RoomType();
                ps.setString(1, services_roomType.getServices().getName());
                ps.setLong(2, services_roomType.getServices().getPriceType().getId());
                ps.setDouble(3, services_roomType.getServices().getAmount());
                ps.setString(4, services_roomType.getServices().getDescription());
                ps.setInt(5, services_roomType.getServices().getAction());
                ps.execute();

               ps = c.prepareStatement(lastRoomType);
                rs = ps.executeQuery();
                int roomTypeId = 0;
                if (rs.next()) {
                    roomTypeId = rs.getInt("id_room_type");
                }*/
                ps = c.prepareStatement(lastServices);
                rs = ps.executeQuery();
                int servicesId = 0;
                if (rs.next()) {
                    servicesId = rs.getInt("id_services");
                }
                ps = c.prepareStatement(sqlServices_RoomType);
                ps.setLong(1, services_roomType.getRoomType().getId());
                ps.setInt(2, servicesId);
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
    public Services_RoomType getServices_RoomTypeById(Long services_roomTypeId) throws Exception {
        Services_RoomType services_roomType = new Services_RoomType();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sr.id_services_room_type,s.id_services,\n"
                + "         s.name,s.amount,s.description,s.action,\n"
                + "rt.id_room_type,rt.room_type,\n"
                + "pt.id_price_type,pt.price_type \n"
                + "    FROM services_room_type sr\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON sr.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN services s\n"
                + "            ON sr.services_id = s.id_services\n"
                + "         INNER JOIN price_type pt\n"
                + "            ON s.price_type_id = pt.id_price_type\n"
                + "   WHERE sr.active = 1\n"
                + "AND sr.id_services_room_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, services_roomTypeId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    services_roomType.setId(rs.getLong("id_services_room_type"));
                    Services services = new Services();
                    services.setId(rs.getLong("id_services"));
                    services.setName(rs.getString("name"));
                    services.setAmount(rs.getDouble("amount"));
                    services.setDescription(rs.getString("description"));
                    services.setAction(rs.getInt("action"));
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    services.setPriceType(priceType);
                    services_roomType.setServices(services);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    services_roomType.setRoomType(roomType);


                } else {
                    services_roomType = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return services_roomType;
    }

    @Override
    public boolean updateServices_RoomType(Services_RoomType services_roomType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastRoomType = "SELECT MAX(id_room_type) id_room_type FROM room_type";
        String lastServices = "SELECT MAX(id_services) id_services FROM services";
        String sqlServices = "UPDATE  services SET name=?,price_type_id=?,amount=?,description=?,action=? WHERE id_services=?";
        String sqlServices_RoomType = "UPDATE services_room_type SET room_type_id=?,services_id=? WHERE id_services_room_type=?";


        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sqlServices);
                ps.setString(1, services_roomType.getServices().getName());
                ps.setLong(2, services_roomType.getServices().getPriceType().getId());
                ps.setDouble(3, services_roomType.getServices().getAmount());
                ps.setString(4, services_roomType.getServices().getDescription());
                ps.setInt(5, services_roomType.getServices().getAction());
                ps.setLong(6, services_roomType.getServices().getId());
                ps.execute();
                ps = c.prepareStatement(lastRoomType);
                rs = ps.executeQuery();
                int roomTypeId = 0;
                if (rs.next()) {
                    roomTypeId = rs.getInt("id_room_type");
                }
                ps = c.prepareStatement(lastServices);
                rs = ps.executeQuery();
                int servicesId = 0;
                if (rs.next()) {
                    servicesId = rs.getInt("id_services");
                } ps = c.prepareStatement(sqlServices_RoomType);
                ps.setInt(1, roomTypeId);
                ps.setInt(2, servicesId);
                ps.setLong(3, services_roomType.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps,rs);
        }
        return result;    }

    @Override
    public boolean deleteServices_RoomType(Long services) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE services_room_type SET active = 0 " + " WHERE services_id = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, services);
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
        return result;    }

    @Override
    public List<Services_RoomType> searchServices_RoomTypeData(String keyword) throws Exception {
        List<Services_RoomType> services_roomTypeList = new ArrayList<Services_RoomType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sr.id_services_room_type,s.id_services,\n"
                + "         s.name,s.amount,s.description,s.action,\n"
                + "rt.id_room_type,rt.room_type,\n"
                + "pt.id_price_type,pt.price_type \n"
                + "    FROM services_room_type sr\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON sr.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN services s\n"
                + "            ON sr.services_id = s.id_services\n"
                + "         INNER JOIN price_type pt\n"
                + "            ON s.price_type_id = pt.id_price_type\n"
                + "   WHERE sr.active = 1\n"
                + "AND ( LOWER(name) LIKE LOWER (?)\n "
                + "OR LOWER(amount) LIKE LOWER (?)\n"
                + "OR LOWER(description) LIKE LOWER (?)\n "
                + "OR LOWER(action) LIKE LOWER (?)\n "
                + "OR LOWER(room_type) LIKE LOWER (?)\n "
                + "OR LOWER(price_type) LIKE LOWER (?)) ";
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
                    Services_RoomType services_roomType = new Services_RoomType();
                    services_roomType.setId(rs.getLong("id_services_room_type"));
                    Services services = new Services();
                    services.setId(rs.getLong("id_services"));
                    services.setName(rs.getString("name"));
                    services.setAmount(rs.getDouble("amount"));
                    services.setDescription(rs.getString("description"));
                    services.setAction(rs.getInt("action"));
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    services.setPriceType(priceType);
                    services_roomType.setServices(services);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    services_roomType.setRoomType(roomType);

                    services_roomTypeList.add(services_roomType);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return services_roomTypeList;    }
}
