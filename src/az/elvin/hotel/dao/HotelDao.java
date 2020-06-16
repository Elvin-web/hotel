package az.elvin.hotel.dao;

import az.elvin.hotel.model.Hotel;

import java.util.List;

public interface HotelDao {
    List<Hotel> getHotelList() throws Exception;

    boolean addHotel(Hotel hotel) throws Exception;

    Hotel getHotelById(Long hotelId) throws Exception;

    boolean updateHotel(Hotel hotel) throws Exception;

    boolean deleteHotel(Long hotelId) throws Exception;

    List<Hotel> searchHotelData(String keyword) throws Exception;
}
