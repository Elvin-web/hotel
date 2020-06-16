package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.CityDao;
import az.elvin.hotel.model.City;
import az.elvin.hotel.service.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {
    private CityDao cityDao;

    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public List<City> getCityList() throws Exception {
        return cityDao.getCityList();
    }

    @Override
    public List<City> getCityListByCountryId(Long countryId) throws Exception {
        return cityDao.getCityListByCountryId(countryId);
    }

    @Override
    public boolean addCity(City city) throws Exception {
        return cityDao.addCity(city);
    }

    @Override
    public City getCityById(Long cityId) throws Exception {
        return cityDao.getCityById(cityId);
    }

    @Override
    public boolean updateCity(City city) throws Exception {
        return cityDao.updateCity(city);
    }

    @Override
    public boolean deleteCity(Long cityId) throws Exception {
        return cityDao.deleteCity(cityId);
    }

    @Override
    public List<City> searchCityData(String keyword) throws Exception {
        return cityDao.searchCityData(keyword);
    }
}
