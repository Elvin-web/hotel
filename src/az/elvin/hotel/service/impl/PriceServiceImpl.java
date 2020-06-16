package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.PriceDao;
import az.elvin.hotel.model.Price;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.service.PriceService;

import java.util.List;

public class PriceServiceImpl implements PriceService {
    private PriceDao priceDao;

    public PriceServiceImpl(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    @Override
    public List<RoomType> getPriceList() throws Exception {
        return priceDao.getPriceList();
    }

    @Override
    public boolean addPrice(Price price) throws Exception {
        return priceDao.addPrice(price);
    }

    @Override
    public Price getPriceById(Long priceId) throws Exception {
        return priceDao.getPriceById(priceId);
    }

    @Override
    public boolean updatePrice(Price price) throws Exception {
        return priceDao.updatePrice(price);
    }

    @Override
    public boolean deletePrice(Long priceId) throws Exception {
        return priceDao.deletePrice(priceId);
    }

    @Override
    public List<Price> searchPriceData(String keyword) throws Exception {
        return priceDao.searchPriceData(keyword);
    }
}
