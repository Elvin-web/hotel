package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PayTypeDao;
import az.elvin.hotel.model.PayType;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PayTypeDaoImpl implements PayTypeDao {
    @Override
    public List<PayType> getPayTypeList() throws Exception {
        List<PayType> payTypeList = new ArrayList<PayType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_pay_type,p.pay_type\n"
                + "    FROM pay_type p\n"
                + "   WHERE p.active = 1\n"
                + "ORDER BY p.id_pay_type";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payTypeList.add(payType);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payTypeList;
    }

    @Override
    public boolean addPayType(PayType payType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  pay_type (pay_type ) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, payType.getPayType());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public PayType getPayTypeById(Long payTypeId) throws Exception {
        PayType payType = new PayType();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_pay_type,p.pay_type\n"
                + "    FROM pay_type p\n"
                + "   WHERE p.active = 1\n"
                + "AND p.id_pay_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, payTypeId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                } else {
                    payType = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payType;
    }

    @Override
    public boolean updatePayType(PayType payType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  pay_type SET pay_type =? WHERE id_pay_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, payType.getPayType());
                ps.setLong(2, payType.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deletePayType(Long payTypeId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  pay_type SET active = 0 " + " WHERE id_pay_type = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, payTypeId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<PayType> searchPayTypeData(String keyword) throws Exception {
        List<PayType> payTypeList = new ArrayList<PayType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_pay_type,p.pay_type\n"
                + "    FROM pay_type p\n"
                + "   WHERE p.active = 1\n"
                + "AND ( LOWER(pay_type) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payTypeList.add(payType);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payTypeList;
    }
}
