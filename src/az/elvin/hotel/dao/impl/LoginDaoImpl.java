package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.LoginDao;
import az.elvin.hotel.model.LoginUser;
import az.elvin.hotel.model.Role;
import az.elvin.hotel.model.Staff;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoImpl implements LoginDao {
    @Override
    public LoginUser login(String username, String password) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT lh.id_login_hotel,lh.username,lh.password,s.id_staff,s.first_name,s.last_name,r.id_role,r.role_name FROM login_hotel lh\n"
                + "INNER JOIN role r ON lh.role_id =r.id_role \n"
                + "INNER JOIN staff s ON lh.staff_id =s.id_staff \n"
                + "WHERE lh.active=1 AND lh.username=? AND lh.password =?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    loginUser.setId_hotel_login(rs.getLong("id_login_hotel"));
                    loginUser.setUsername(rs.getString("username"));
                    loginUser.setPassword(rs.getString("password"));
                    Staff staff=new Staff();
                    staff.setId(rs.getLong("id_staff"));
                    staff.setName(rs.getString("first_name"));
                    staff.setSurname(rs.getString("last_name"));
                    loginUser.setStaff(staff);
                    Role role = new Role();
                    role.setId(rs.getLong("id_role"));
                    role.setRoleName(rs.getString("role_name"));
                    loginUser.setRole(role);

                } else {
                    loginUser = null;
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return loginUser;
    }

    @Override
    public LoginUser checkEmail(String email) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM login_hotel lh\n"
                + "INNER JOIN staff s ON lh.staff_id =s.id_staff \n"
                + "WHERE lh.active=1 AND s.email=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    loginUser.setId_hotel_login(rs.getLong("id_login_hotel"));
                    loginUser.setToken(rs.getString("token"));

                } else {
                    loginUser = null;
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return loginUser;
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  login_hotel SET password=? WHERE token=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, token);
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
    public boolean updateToken(String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  login_hotel SET token=? WHERE token=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, null);
                ps.setString(2, token);
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
    public boolean updateTokenById(String token, long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  login_hotel SET token=? WHERE id_login_hotel=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);
                ps.setLong(2, id);
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

}
