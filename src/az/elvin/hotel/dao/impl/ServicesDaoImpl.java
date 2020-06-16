package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.ServicesDao;
import az.elvin.hotel.model.PriceType;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.model.Services;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicesDaoImpl implements ServicesDao {
    @Override
    public List<Services> getServicesList() throws Exception {
        List<Services> servicesList = new ArrayList<Services>();
        Connection c = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "SELECT s.id_services,\n"
                + "         s.name,s.amount,s.description,s.action,\n"
                + "p.id_price_type id_price_type,p.price_type \n"
                + "    FROM services s\n"
                + "         INNER JOIN price_type p\n"
                + "            ON s.price_type_id = p.id_price_type\n"
                + "   WHERE s.active = 1\n"
                + "ORDER BY s.id_services";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Services services = new Services();
                    services.setId(rs.getLong("id_services"));
                    services.setName(rs.getString("name"));
                    services.setAmount(rs.getDouble("amount"));
                    services.setDescription(rs.getString("description"));
                    services.setAction(rs.getInt("action"));
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    services.setPriceType(priceType);
                    servicesList.add(services);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return servicesList;
    }

    @Override
    public boolean addServices(Services services) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  services  (name,price_type_id,amount,description,action) " + " VALUES (?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, services.getName());
                ps.setLong(2, services.getPriceType().getId());
                ps.setDouble(3, services.getAmount());
                ps.setString(4, services.getDescription());
                ps.setInt(5, services.getAction());

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
    public Services getServicesById(Long servicesId) throws Exception {
        Services services = new Services();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_services,\n"
                + "         s.name,s.amount,s.description,s.action,\n"
                + "p.id_price_type id_price_type,p.price_type \n"
                + "    FROM services s\n"
                + "         INNER JOIN price_type p\n"
                + "            ON s.price_type_id = p.id_price_type\n"
                + "   WHERE s.active = 1\n"
                + "AND s.id_services=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, servicesId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    services.setId(rs.getLong("id_services"));
                    services.setName(rs.getString("name"));
                    services.setAmount(rs.getDouble("amount"));
                    services.setDescription(rs.getString("description"));
                    services.setAction(rs.getInt("action"));
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    services.setPriceType(priceType);

                } else {
                    services = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return services;
    }

    @Override
    public boolean updateServices(Services services) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  services  SET name=?,price_type_id=?,amount=?,description=?,action=? WHERE id_services=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, services.getName());
                ps.setLong(2, services.getPriceType().getId());
                ps.setDouble(3, services.getAmount());
                ps.setString(4, services.getDescription());
                ps.setInt(5, services.getAction());
                ps.setLong(6, services.getId());
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
    public boolean deleteServices(Long servicesId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  services  SET active = 0 " + " WHERE id_services = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, servicesId);
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
    public List<Services> searchServicesData(String keyword) throws Exception {
        List<Services> servicesList = new ArrayList<Services>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id_services,\n"
                + "         s.name,s.amount,s.description,s.action,\n"
                + "p.id_price_type id_price_type,p.price_type \n"
                + "    FROM services s\n"
                + "         INNER JOIN price_type p\n"
                + "            ON s.price_type_id = p.id_price_type\n"
                + "   WHERE s.active = 1\n"
                + "AND ( LOWER(name) LIKE LOWER (?)\n "
                + "OR LOWER(amount) LIKE LOWER (?)\n"
                + "OR LOWER(description) LIKE LOWER (?)\n "
                + "OR LOWER(action) LIKE LOWER (?)\n "
                + "OR LOWER(price_type) LIKE LOWER (?)) ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Services services = new Services();
                    services.setId(rs.getLong("id_services"));
                    services.setName(rs.getString("name"));
                    services.setAmount(rs.getDouble("amount"));
                    services.setDescription(rs.getString("description"));
                    services.setAction(rs.getInt("action"));
                    PriceType priceType = new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    services.setPriceType(priceType);
                    servicesList.add(services);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return servicesList;
    }
}
