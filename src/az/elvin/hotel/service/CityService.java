package az.elvin.hotel.service;

import az.elvin.hotel.model.City;

import java.util.List;

public interface CityService {
    List<City> getCityList() throws Exception;

    List<City> getCityListByCountryId(Long countryId) throws Exception;

    boolean addCity(City city) throws Exception;

    City getCityById(Long cityId) throws Exception;

    boolean updateCity(City city) throws Exception;

    boolean deleteCity(Long cityId) throws Exception;

    List<City> searchCityData(String keyword) throws Exception;
}
