package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.AmenitiesDao;
import az.elvin.hotel.model.Amenities;
import az.elvin.hotel.service.AmenitiesService;

import java.util.List;

public class AmenitiesServiceImpl implements AmenitiesService {
    private AmenitiesDao amenitiesDao;

    public AmenitiesServiceImpl(AmenitiesDao amenitiesDao) {
        this.amenitiesDao = amenitiesDao;
    }


    @Override
    public List<Amenities> getAmenitiesList() throws Exception {
        return amenitiesDao.getAmenitiesList();
    }

    @Override
    public boolean addAmenities(Amenities amenities) throws Exception {
        return amenitiesDao.addAmenities(amenities);
    }

    @Override
    public Amenities getAmenitiesById(Long amenitiesId) throws Exception {
        return amenitiesDao.getAmenitiesById(amenitiesId);
    }

    @Override
    public boolean updateAmenities(Amenities amenities) throws Exception {
        return amenitiesDao.updateAmenities(amenities);
    }

    @Override
    public boolean deleteAmenities(Long amenitiesId) throws Exception {
        return amenitiesDao.deleteAmenities(amenitiesId);
    }

    @Override
    public List<Amenities> searchAmenitiesData(String keyword) throws Exception {
        return amenitiesDao.searchAmenitiesData(keyword);
    }
}
