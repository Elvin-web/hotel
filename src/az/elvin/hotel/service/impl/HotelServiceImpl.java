package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.HotelDao;
import az.elvin.hotel.model.Hotel;
import az.elvin.hotel.service.HotelService;

import java.util.List;

public class HotelServiceImpl implements HotelService {
    private HotelDao hotelDao;
    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }


    @Override
    public List<Hotel> getHotelList() throws Exception {
        return hotelDao.getHotelList();
    }

    @Override
    public boolean addHotel(Hotel hotel) throws Exception {
        return hotelDao.addHotel(hotel);
    }

    @Override
    public Hotel getHotelById(Long hotelId) throws Exception {
        return hotelDao.getHotelById(hotelId);
    }

    @Override
    public boolean updateHotel(Hotel hotel) throws Exception {
        return hotelDao.updateHotel(hotel);
    }

    @Override
    public boolean deleteHotel(Long hotelId) throws Exception {
        return hotelDao.deleteHotel(hotelId);
    }

    @Override
    public List<Hotel> searchHotelData(String keyword) throws Exception {
        return hotelDao.searchHotelData(keyword);
    }
}
