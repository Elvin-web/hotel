package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PassportDao;
import az.elvin.hotel.model.Passport;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassportDaoImpl implements PassportDao {
    @Override
    public List<Passport> getPassportList() throws Exception {
        List<Passport> passportList = new ArrayList<Passport>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_passport,\n"
                + "         p.passport_type\n"
                + "    FROM passport p\n"
                + "   WHERE p.active = 1\n"
                + "ORDER BY p.id_passport";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Passport passport = new Passport();
                    passport.setId(rs.getLong("id_passport"));
                    passport.setPassportType(rs.getString("passport_type"));
                    passportList.add(passport);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return passportList;
    }

    @Override
    public boolean addPassport(Passport passport) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  passport (passport_type) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, passport.getPassportType());
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
    public Passport getPassportById(Long passportId) throws Exception {
        Passport passport = new Passport();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_passport,\n"
                + "         p.passport_type\n"
                + "    FROM passport p\n"
                + "   WHERE p.active = 1\n"
                + "AND p.id_passport=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, passportId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    passport.setId(rs.getLong("id_passport"));
                    passport.setPassportType(rs.getString("passport_type"));

                } else {
                    passport = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return passport;
    }

    @Override
    public boolean updatePassport(Passport passport) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  passport SET passport_type=? WHERE id_passport=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, passport.getPassportType());
                ps.setLong(2, passport.getId());
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
    public boolean deletePassport(Long passportId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  passport SET active = 0 " + " WHERE id_passport = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, passportId);
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
    public List<Passport> searchPassportData(String keyword) throws Exception {
        List<Passport> passportList = new ArrayList<Passport>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_passport,\n"
                + "         p.passport_type\n"
                + "    FROM passport p\n"
                + "   WHERE p.active = 1\n"
                + "AND ( LOWER(passport_type) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Passport passport = new Passport();
                    passport.setId(rs.getLong("id_passport"));
                    passport.setPassportType(rs.getString("passport_type"));
                    passportList.add(passport);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return passportList;
    }
}
