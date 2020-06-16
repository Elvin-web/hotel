package az.elvin.hotel.service;

import az.elvin.hotel.model.Amenities;

import java.util.List;

public interface AmenitiesService {
    List<Amenities> getAmenitiesList() throws Exception;

    boolean addAmenities(Amenities amenities) throws Exception;

    Amenities getAmenitiesById(Long amenitiesId) throws Exception;

    boolean updateAmenities(Amenities amenities) throws Exception;

    boolean deleteAmenities(Long amenitiesId) throws Exception;

    List<Amenities> searchAmenitiesData(String keyword) throws Exception;
}
