package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.StarDao;
import az.elvin.hotel.model.Star;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StarDaoImpl implements StarDao {
    @Override
    public List<Star> getStarList() throws Exception {
        List<Star> starList = new ArrayList<Star>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_star, s.star\n"
                + "    FROM star s\n"
                + "   WHERE s.active = 1\n"
                + "ORDER BY s.id_star";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Star star = new Star();
                    star.setId(rs.getLong("id_star"));
                    star.setStar(rs.getLong("star"));
                    starList.add(star);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return starList;
    }

    @Override
    public boolean addStar(Star star) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  star (id_star) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, star.getStar());
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
    public Star getStarById(Long starId) throws Exception {
        Star star = new Star();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_star, s.star\n"
                + "    FROM star s\n"
                + "   WHERE s.active = 1\n"
                + "AND s.id_star=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, starId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    star.setId(rs.getLong("id_star"));
                    star.setStar(rs.getLong("star"));
                } else {
                    star = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return star;
    }

    @Override
    public boolean updateStar(Star star) throws Exception {

        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  star SET star=? WHERE id_star=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, star.getStar());
                ps.setLong(2, star.getId());
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
    public boolean deleteStar(Long starId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  star SET active = 0 " + " WHERE id_star = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, starId);
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
    public List<Star> searchStarData(String keyword) throws Exception {
        List<Star> starList = new ArrayList<Star>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_star, s.star\n"
                + "    FROM star s\n"
                + "   WHERE s.active = 1\n"
                + "AND ( LOWER(star) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Star star = new Star();
                    star.setId(rs.getLong("id_star"));
                    star.setStar(rs.getLong("star"));
                    starList.add(star);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return starList;
    }

}
