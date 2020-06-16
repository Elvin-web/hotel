package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.TaxDao;
import az.elvin.hotel.model.Tax;
import az.elvin.hotel.service.TaxService;

import java.util.List;

public class TaxServiceImpl implements TaxService {
    private TaxDao taxDao;

    public TaxServiceImpl(TaxDao taxDao) {
        this.taxDao = taxDao;
    }

    @Override
    public List<Tax> getTaxList() throws Exception {
        return taxDao.getTaxList();
    }

    @Override
    public boolean addTax(Tax tax) throws Exception {
        return taxDao.addTax(tax);
    }

    @Override
    public Tax getTaxById(Long taxId) throws Exception {
        return taxDao.getTaxById(taxId);
    }

    @Override
    public boolean updateTax(Tax tax) throws Exception {
        return taxDao.updateTax(tax);
    }

    @Override
    public boolean deleteTax(Long taxId) throws Exception {
        return taxDao.deleteTax(taxId);
    }

    @Override
    public List<Tax> searchTaxData(String keyword) throws Exception {
        return taxDao.searchTaxData(keyword);
    }
}
