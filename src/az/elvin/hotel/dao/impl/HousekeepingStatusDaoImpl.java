package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.HousekeepingStatusDao;
import az.elvin.hotel.model.HousekeepingStatus;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HousekeepingStatusDaoImpl implements HousekeepingStatusDao {
    @Override
    public List<HousekeepingStatus> getHousekeepingStatusList() throws Exception {
        List<HousekeepingStatus> housekeepingStatuseList = new ArrayList<HousekeepingStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping_status,h.description,\n"
                + "         h.name,h.action\n"
                + "    FROM housekeeping_status h\n"
                + "   WHERE h.active = 1\n"
                + "ORDER BY h.id_housekeeping_status";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setDescription(rs.getString("description"));
                    housekeepingStatus.setName(rs.getString("name"));
                    housekeepingStatus.setAction(rs.getInt("action"));
                    housekeepingStatuseList.add(housekeepingStatus);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeepingStatuseList;
    }

    @Override
    public boolean addHousekeepingStatus(HousekeepingStatus housekeepingStatus) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  housekeeping_status (name,description,action) " + " VALUES (?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, housekeepingStatus.getName());
                ps.setString(2, housekeepingStatus.getDescription());
                ps.setInt(3, housekeepingStatus.getAction());
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
    public HousekeepingStatus getHousekeepingStatusById(Long housekeepingStatusId) throws Exception {
        HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping_status,h.description,\n"
                + "         h.name,h.action\n"
                + "    FROM housekeeping_status h\n"
                + "   WHERE h.active = 1\n"
                + "   AND h.id_housekeeping_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, housekeepingStatusId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setDescription(rs.getString("description"));
                    housekeepingStatus.setName(rs.getString("name"));
                    housekeepingStatus.setAction(rs.getInt("action"));
                } else {
                    housekeepingStatus = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeepingStatus;
    }

    @Override
    public boolean updateHousekeepingStatus(HousekeepingStatus housekeepingStatus) throws Exception {

        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  housekeeping_status SET name=?,description=?,action=? WHERE id_housekeeping_status=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, housekeepingStatus.getName());
                ps.setString(2, housekeepingStatus.getDescription());
                ps.setInt(3, housekeepingStatus.getAction());
                ps.setLong(4, housekeepingStatus.getId());
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
    public boolean deleteHousekeepingStatus(Long housekeepingStatusId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  housekeeping_status SET active = 0 " + " WHERE id_housekeeping_status = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, housekeepingStatusId);
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
    public List<HousekeepingStatus> searchHousekeepingStatusData(String keyword) throws Exception {
        List<HousekeepingStatus> housekeepingStatusList = new ArrayList<HousekeepingStatus>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT h.id_housekeeping_status,h.description,\n"
                + "         h.name,h.action\n"
                + "    FROM housekeeping_status h\n"
                + "   WHERE h.active = 1\n"
                + " AND ( LOWER(name) LIKE LOWER (?) OR LOWER (description) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    HousekeepingStatus housekeepingStatus = new HousekeepingStatus();
                    housekeepingStatus.setId(rs.getLong("id_housekeeping_status"));
                    housekeepingStatus.setDescription(rs.getString("description"));
                    housekeepingStatus.setName(rs.getString("name"));
                    housekeepingStatus.setAction(rs.getInt("action"));
                    housekeepingStatusList.add(housekeepingStatus);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return housekeepingStatusList;
    }
}
