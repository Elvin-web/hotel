package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.CityDao;
import az.elvin.hotel.dao.CountryDao;
import az.elvin.hotel.model.Country;
import az.elvin.hotel.service.CountryService;

import java.util.List;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> getCountryList() throws Exception {
        return countryDao.getCountryList();
    }

    @Override
    public boolean addCountry(Country country) throws Exception {
        return countryDao.addCountry(country);
    }

    @Override
    public Country getCountryById(Long countryId) throws Exception {
        return countryDao.getCountryById(countryId);
    }

    @Override
    public boolean updateCountry(Country country) throws Exception {
        return countryDao.updateCountry(country);
    }

    @Override
    public boolean deleteCountry(Long countryId) throws Exception {
        return countryDao.deleteCountry(countryId);
    }

    @Override
    public List<Country> searchCountryData(String keyword) throws Exception {
        return countryDao.searchCountryData(keyword);
    }
}
