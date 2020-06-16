package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.CityDao;
import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.model.City;
import az.elvin.hotel.model.Country;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao {
    @Override
    public List<City> getCityList() throws Exception {

        List<City> cityList = new ArrayList<City>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT c.id_city,\n"
                + "         c.name,\n"
                + "         ct.id_country id_country,ct.name country_name\n"
                + "    FROM city c\n"
                + "         INNER JOIN country ct\n"
                + "            ON c.country_id = ct.id_country\n"
                + "   WHERE c.active= 1\n"
                + "ORDER BY c.id_city";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    City city = new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("name"));
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("country_name"));
                    city.setCountry(country);
                    cityList.add(city);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return cityList;
    }

    @Override
    public List<City> getCityListByCountryId(Long countryId) throws Exception {
        List<City> cityList = new ArrayList<City>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT c.id_city,c.name FROM city c\n" +
                "INNER JOIN country ct ON c.country_id=ct.id_country\n" +
                "WHERE c.active=1 AND ct.id_country=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, countryId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    City city = new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("name"));
                    cityList.add(city);
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return cityList;
    }

    @Override
    public boolean addCity(City city) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  city (name,country_id) " + " VALUES (?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, city.getName());
                ps.setLong(2, city.getCountry().getId());
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
    public City getCityById(Long cityId) throws Exception {
        City city = new City();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT c.id_city,\n"
                + "         c.name,\n"
                + "         ct.id_country id_country,ct.name country_name\n"
                + "    FROM city c\n"
                + "         INNER JOIN country ct\n"
                + "            ON c.country_id = ct.id_country\n"
                + "   WHERE c.active= 1\n"
                + "AND c.id_city=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, cityId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("name"));
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    city.setCountry(country);
                } else {
                    city = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return city;
    }

    @Override
    public boolean updateCity(City city) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  city SET name=?,country_id=? WHERE id_city=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, city.getName());
                ps.setLong(2, city.getCountry().getId());
                ps.setLong(3, city.getId());
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
    public boolean deleteCity(Long cityId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  city SET active = 0 " + " WHERE id_city = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, cityId);
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
    public List<City> searchCityData(String keyword) throws Exception {
        List<City> cityList = new ArrayList<City>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT c.id_city,\n"
                + "         c.name,\n"
                + "         ct.id_country id_country,ct.name country_name\n"
                + "    FROM city c\n"
                + "         INNER JOIN country ct\n"
                + "            ON c.country_id = ct.id_country\n"
                + "   WHERE c.active= 1\n"
                + "AND ( LOWER(name) LIKE LOWER (?) OR LOWER(country_name) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    City city = new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("name"));
                    Country country = new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("country_name"));
                    city.setCountry(country);
                    cityList.add(city);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return cityList;
    }

}
