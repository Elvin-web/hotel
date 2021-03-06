package az.elvin.hotel.service;

import az.elvin.hotel.dao.PriceTypeDao;
import az.elvin.hotel.model.PriceType;

import java.util.List;

public interface PriceTypeService {
    List<PriceType> getPriceTypeList() throws Exception;

    boolean addPriceType(PriceType priceType) throws Exception;

    PriceType getPriceTypeById(Long priceTypeId) throws Exception;

    boolean updatePriceType(PriceType priceType) throws Exception;

    boolean deletePriceType(Long priceTypeId) throws Exception;

    List<PriceType> searchPriceTypeData(String keyword) throws Exception;
}
