package az.elvin.hotel.dao;

import az.elvin.hotel.model.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getCountryList() throws Exception;

    boolean addCountry(Country country) throws Exception;

    Country getCountryById(Long countryId) throws Exception;

    boolean updateCountry(Country country) throws Exception;

    boolean deleteCountry(Long countryId) throws Exception;

    List<Country> searchCountryData(String keyword) throws Exception;
}
