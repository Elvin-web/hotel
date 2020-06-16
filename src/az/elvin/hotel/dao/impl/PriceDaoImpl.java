package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PriceDao;
import az.elvin.hotel.model.Price;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PriceDaoImpl implements PriceDao {
    @Override
    public List<RoomType> getPriceList() throws Exception {
        List<RoomType> priceList = new ArrayList<RoomType>();

        Connection c = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "SELECT   r.id_room_type,\n" +
                "         r.room_type,\n" +
                "         p.id_price,\n" +
                "         p.extra_bed_price,\n" +
                "         p.base_price\n" +
                "    FROM room_type r \n" +
                "    INNER JOIN price p ON r.price_id = p.id_price\n" +
                "   WHERE r.active = 1\n" +
                "ORDER BY r.id_room_type";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    RoomType roomType=new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    Price price = new Price();
                    price.setId(rs.getLong("id_price"));
                    price.setBasePrice(rs.getDouble("base_price"));
                    price.setExtraBedPrice(rs.getDouble("extra_bed_price"));
                    roomType.setPrice(price);
                    priceList.add(roomType);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return priceList;
    }

    @Override
    public boolean addPrice(Price price) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Price getPriceById(Long priceId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePrice(Price price) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePrice(Long priceId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Price> searchPriceData(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
