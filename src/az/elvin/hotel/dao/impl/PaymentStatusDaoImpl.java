package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PaymentStatusDao;
import az.elvin.hotel.model.PaymentStatus;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentStatusDaoImpl implements PaymentStatusDao {
    @Override
    public List<PaymentStatus> getPaymentStatusList() throws Exception {
        List<PaymentStatus> paymentStatusList = new ArrayList<PaymentStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment_status,\n"
                + "         p.payment_status\n"
                + "    FROM payment_status p\n"
                + "   WHERE p.active = 1\n"
                + "ORDER BY p.id_payment_status";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PaymentStatus paymentStatus = new PaymentStatus();
                    paymentStatus.setId(rs.getLong("id_payment_status"));
                    paymentStatus.setPaymentStatus(rs.getString("payment_status"));
                    paymentStatusList.add(paymentStatus);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentStatusList;
    }

    @Override
    public boolean addPaymentStatus(PaymentStatus paymentStatus) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  payment_status (payment_status) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, paymentStatus.getPaymentStatus());
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
    public PaymentStatus getPaymentStatusById(Long paymentStatusId) throws Exception {
        PaymentStatus paymentStatus = new PaymentStatus();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment_status,\n"
                + "         p.payment_status\n"
                + "    FROM payment_status p\n"
                + "   WHERE p.active = 1\n"
                + "AND p.id_payment_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentStatusId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    paymentStatus.setId(rs.getLong("id_payment_status"));
                    paymentStatus.setPaymentStatus(rs.getString("payment_status"));

                } else {
                    paymentStatus = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentStatus;
    }

    @Override
    public boolean updatePaymentStatus(PaymentStatus paymentStatus) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  payment_status SET payment_status=? WHERE id_payment_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, paymentStatus.getPaymentStatus());
                ps.setLong(2, paymentStatus.getId());
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
    public boolean deletePaymentStatus(Long paymentStatusId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  payment_status SET active = 0 " + " WHERE id_payment_status = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentStatusId);
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
    public List<PaymentStatus> searchPaymentStatusData(String keyword) throws Exception {
        List<PaymentStatus> paymentStatusList = new ArrayList<PaymentStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT p.id_payment_status,\n"
                + "         p.payment_status\n"
                + "    FROM payment_status p\n"
                + "   WHERE p.active = 1\n"
                + "AND ( LOWER(payment_status) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    PaymentStatus paymentStatus = new PaymentStatus();
                    paymentStatus.setId(rs.getLong("id_payment_status"));
                    paymentStatus.setPaymentStatus(rs.getString("payment_status"));
                    paymentStatusList.add(paymentStatus);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentStatusList;
    }

}
