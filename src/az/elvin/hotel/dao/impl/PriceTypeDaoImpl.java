package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PriceTypeDao;
import az.elvin.hotel.model.PriceType;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PriceTypeDaoImpl implements PriceTypeDao {
    @Override
    public List<PriceType> getPriceTypeList() throws Exception {
        List<PriceType> priceTypeList = new ArrayList<PriceType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_price_type,\n"
                + "         p.price_type\n"
                + "    FROM price_type p\n"
                + "   WHERE p.active=1 = 1\n"
                + "ORDER BY p.id_price_type";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    priceTypeList.add(priceType);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return priceTypeList;
    }

    @Override
    public boolean addPriceType(PriceType priceType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  price_type (price_type) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, priceType.getPriceType());
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
    public PriceType getPriceTypeById(Long priceTypeId) throws Exception {
        PriceType priceType = new PriceType();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_price_type,p.price_type\n"
                + "    FROM price_type p\n"
                + "   WHERE p.active = 1\n"
                + "AND p.id_price_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, priceTypeId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));

                } else {
                    priceType = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return priceType;
    }

    @Override
    public boolean updatePriceType(PriceType priceType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  price_type SET price_type=? WHERE id_price_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, priceType.getPriceType());
                ps.setLong(2, priceType.getId());
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
    public boolean deletePriceType(Long priceTypeId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  price_type SET active = 0 " + " WHERE id_price_type = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, priceTypeId);
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
    public List<PriceType> searchPriceTypeData(String keyword) throws Exception {
        List<PriceType> priceTypeList = new ArrayList<PriceType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_price_type,p.price_type\n"
                + "    FROM price_type p\n"
                + "   WHERE p.active = 1\n"
                + "AND ( LOWER(price_type) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    priceTypeList.add(priceType);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return priceTypeList;
    }
}
