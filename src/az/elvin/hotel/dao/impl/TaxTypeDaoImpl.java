package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.TaxTypeDao;
import az.elvin.hotel.model.TaxType;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaxTypeDaoImpl implements TaxTypeDao {
    @Override
    public List<TaxType> getTaxTypeList() throws Exception {

        List<TaxType> taxTypeList = new ArrayList<TaxType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.id_tax_type,\n"
                + "         t.type\n"
                + "    FROM tax_type t\n"
                + "   WHERE t.active = 1\n"
                + "ORDER BY t.id_tax_type";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    TaxType taxType = new TaxType();
                    taxType.setId(rs.getLong("id_tax_type"));
                    taxType.setTaxType(rs.getString("type"));
                    taxTypeList.add(taxType);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return taxTypeList;
    }

    @Override
    public boolean addTaxType(TaxType taxType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  tax_type (type) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, taxType.getTaxType());
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
    public TaxType getTaxTypeById(Long taxTypeId) throws Exception {
        TaxType taxType = new TaxType();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.id_tax_type, t.type\n"
                + "    FROM tax_type t\n"
                + "   WHERE t.active = 1\n"
                + "AND t.id_tax_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, taxTypeId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    taxType.setId(rs.getLong("id_tax_type"));
                    taxType.setTaxType(rs.getString("type"));

                } else {
                    taxType = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return taxType;
    }

    @Override
    public boolean updateTaxType(TaxType taxType) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  tax_type SET type=? WHERE id_tax_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, taxType.getTaxType());
                ps.setLong(2, taxType.getId());
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
    public boolean deleteTaxType(Long taxTypeId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  tax_type SET active = 0 " + " WHERE id_tax_type = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, taxTypeId);
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
    public List<TaxType> searchTaxTypeData(String keyword) throws Exception {
        List<TaxType> taxTypeList = new ArrayList<TaxType>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.id_tax_type, t.type\n"
                + "    FROM tax_type t\n"
                + "   WHERE t.active = 1\n"
                + "AND ( LOWER(type) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    TaxType taxType = new TaxType();
                    taxType.setId(rs.getLong("id_tax_type"));
                    taxType.setTaxType(rs.getString("type"));
                    taxTypeList.add(taxType);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return taxTypeList;
    }
}
