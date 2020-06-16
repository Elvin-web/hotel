package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.CountryDao;
import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.model.Country;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {
    @Override
    public List<Country> getCountryList() throws Exception {

        List<Country> countryList = new ArrayList<Country>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT c.id_country,\n"
                + "         c.name,c.sort_name\n"
                + "    FROM country c\n"
                + "   WHERE c.active = 1\n"
                + "ORDER BY c.id_country";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("name"));
                    country.setSortName(rs.getString("sort_name"));
                    countryList.add(country);
                }

            } else {
                System.out.println("Connectionis null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return countryList;
    }

    @Override
    public boolean addCountry(Country country) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  country (name,sort_name) " + " VALUES (?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, country.getName());
                ps.setString(2, country.getSortName());
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
    public Country getCountryById(Long countryId) throws Exception {
        Country country = new Country();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT c.id_country,\n"
                + "         c.name,c.sort_name\n"
                + "    FROM country c\n"
                + "   WHERE c.active = 1\n"
                + "AND c.id_country=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, countryId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("name"));
                    country.setSortName(rs.getString("sort_name"));

                } else {
                    country = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return country;
    }

    @Override
    public boolean updateCountry(Country country) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  country SET name=?, sort_name=? WHERE id_country=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, country.getName());
                ps.setString(2, country.getSortName());
                ps.setLong(3, country.getId());
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
    public boolean deleteCountry(Long countryId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  country SET active = 0 " + " WHERE id_country = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, countryId);
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
    public List<Country> searchCountryData(String keyword) throws Exception {
        List<Country> countryList = new ArrayList<Country>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT c.id_country,\n"
                + "         c.name,c.sort_name\n"
                + "    FROM country c\n"
                + "   WHERE c.active = 1\n"
                + "AND ( LOWER(name) LIKE LOWER (?) OR LOWER(sort_name) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("name"));
                    country.setSortName(rs.getString("sort_name"));
                    countryList.add(country);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return countryList;
    }
}
