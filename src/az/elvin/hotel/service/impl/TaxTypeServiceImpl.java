package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.TaxTypeDao;
import az.elvin.hotel.model.TaxType;
import az.elvin.hotel.service.TaxTypeService;

import java.util.List;

public class TaxTypeServiceImpl implements TaxTypeService {
    private TaxTypeDao taxTypeDao;

    public TaxTypeServiceImpl(TaxTypeDao taxTypeDao) {
        this.taxTypeDao = taxTypeDao;
    }

    @Override
    public List<TaxType> getTaxTypeList() throws Exception {
        return taxTypeDao.getTaxTypeList();
    }

    @Override
    public boolean addTaxType(TaxType taxType) throws Exception {
        return taxTypeDao.addTaxType(taxType);
    }

    @Override
    public TaxType getTaxTypeById(Long taxTypeId) throws Exception {
        return taxTypeDao.getTaxTypeById(taxTypeId);
    }

    @Override
    public boolean updateTaxType(TaxType taxType) throws Exception {
        return taxTypeDao.updateTaxType(taxType);
    }

    @Override
    public boolean deleteTaxType(Long taxTypeId) throws Exception {
        return taxTypeDao.deleteTaxType(taxTypeId);
    }

    @Override
    public List<TaxType> searchTaxTypeData(String keyword) throws Exception {
        return taxTypeDao.searchTaxTypeData(keyword);
    }
}
