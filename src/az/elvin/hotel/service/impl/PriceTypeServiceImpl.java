package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.PriceTypeDao;
import az.elvin.hotel.model.PriceType;
import az.elvin.hotel.service.PriceTypeService;

import java.util.List;

public class PriceTypeServiceImpl implements PriceTypeService {
    private PriceTypeDao priceTypeDao;

    public PriceTypeServiceImpl(PriceTypeDao priceTypeDao) {
        this.priceTypeDao = priceTypeDao;
    }


    @Override
    public List<PriceType> getPriceTypeList() throws Exception {
        return priceTypeDao.getPriceTypeList();
    }

    @Override
    public boolean addPriceType(PriceType priceType) throws Exception {
        return priceTypeDao.addPriceType(priceType);
    }

    @Override
    public PriceType getPriceTypeById(Long priceTypeId) throws Exception {
        return priceTypeDao.getPriceTypeById(priceTypeId);
    }

    @Override
    public boolean updatePriceType(PriceType priceType) throws Exception {
        return priceTypeDao.updatePriceType(priceType);
    }

    @Override
    public boolean deletePriceType(Long priceTypeId) throws Exception {
        return priceTypeDao.deletePriceType(priceTypeId);
    }

    @Override
    public List<PriceType> searchPriceTypeData(String keyword) throws Exception {
        return priceTypeDao.searchPriceTypeData(keyword);
    }
}
