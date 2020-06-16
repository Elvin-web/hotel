package az.elvin.hotel.service;

import az.elvin.hotel.model.Price;
import az.elvin.hotel.model.RoomType;

import java.util.List;

public interface PriceService {
    List<RoomType> getPriceList() throws Exception;

    boolean addPrice(Price price) throws Exception;

    Price getPriceById(Long priceId) throws Exception;

    boolean updatePrice(Price price) throws Exception;

    boolean deletePrice(Long priceId) throws Exception;

    List<Price> searchPriceData(String keyword) throws Exception;
}
