package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.TaxDao;
import az.elvin.hotel.model.Tax;
import az.elvin.hotel.model.TaxType;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaxDaoImpl implements TaxDao {
    @Override
    public List<Tax> getTaxList() throws Exception {

        List<Tax> taxList = new ArrayList<Tax>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.id_tax,\n"
                + "        t.name,t.code,t.rate,tt.id_tax_type id_tax_type,tt.type\n"
                + "    FROM tax t\n"
                + "         INNER JOIN tax_type tt\n"
                + "            ON t.tax_type_id = tt.id_tax_type\n"
                + "   WHERE t.active = 1\n"
                + "ORDER BY t.id_tax";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Tax tax = new Tax();
                    tax.setId(rs.getLong("id_tax"));
                    tax.setName(rs.getString("name"));
                    tax.setCode(rs.getString("code"));
                    tax.setRate(rs.getDouble("rate"));
                    TaxType taxType = new TaxType();
                    taxType.setId(rs.getLong("id_tax_type"));
                    taxType.setTaxType(rs.getString("type"));
                    tax.setTaxType(taxType);
                    taxList.add(tax);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return taxList;
    }

    @Override
    public boolean addTax(Tax tax) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  tax  (name,code,rate,tax_type_id) " + " VALUES (?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, tax.getName());
                ps.setString(2, tax.getCode());
                ps.setDouble(3, tax.getRate());
                ps.setLong(4, tax.getTaxType().getId());
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
    public Tax getTaxById(Long taxId) throws Exception {
        Tax tax = new Tax();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  t.id_tax,\n"
                + "         t.name,t.code,t.rate,tt.id_tax_type id_tax_type,tt.type\n"
                + "    FROM tax t\n"
                + "         INNER JOIN tax_type tt\n"
                + "            ON t.tax_type_id = tt.id_tax_type\n"
                + "   WHERE t.active = 1\n"
                + "AND t.id_tax=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, taxId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    tax.setId(rs.getLong("id_tax"));
                    tax.setName(rs.getString("name"));
                    tax.setCode(rs.getString("code"));
                    tax.setRate(rs.getDouble("rate"));
                    TaxType taxType = new TaxType();
                    taxType.setId(rs.getLong("id_tax_type"));
                    taxType.setTaxType(rs.getString("type"));
                    tax.setTaxType(taxType);

                } else {
                    tax = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return tax;
    }

    @Override
    public boolean updateTax(Tax tax) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  tax  SET name=?,code=?,rate=?,tax_type_id=? WHERE id_tax=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, tax.getName());
                ps.setString(2, tax.getCode());
                ps.setDouble(3, tax.getRate());
                ps.setLong(4, tax.getTaxType().getId());
                ps.setLong(5, tax.getId());
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
    public boolean deleteTax(Long taxId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  tax  SET active = 0 " + " WHERE id_tax = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, taxId);
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
    public List<Tax> searchTaxData(String keyword) throws Exception {
        List<Tax> taxList = new ArrayList<Tax>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.id_tax,\n"
                + "         t.name,t.code,t.rate,tt.id_tax_type id_tax_type,tt.type\n"
                + "    FROM tax t\n"
                + "         INNER JOIN tax_type tt\n"
                + "            ON t.tax_type_id = tt.id_tax_type\n"
                + "   WHERE t.active = 1\n"
                + "AND ( LOWER(name) LIKE LOWER (?) OR LOWER(code) LIKE LOWER (?)\n "
                + "OR LOWER(rate) LIKE LOWER (?) OR LOWER(type) LIKE LOWER (?) )";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Tax tax = new Tax();
                    tax.setId(rs.getLong("id_tax"));
                    tax.setName(rs.getString("name"));
                    tax.setCode(rs.getString("code"));
                    tax.setRate(rs.getDouble("rate"));
                    TaxType taxType = new TaxType();
                    taxType.setId(rs.getLong("id_tax_type"));
                    taxType.setTaxType(rs.getString("type"));
                    tax.setTaxType(taxType);
                    taxList.add(tax);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return taxList;
    }
}
