package az.elvin.hotel.service;

import az.elvin.hotel.model.Tax;

import java.util.List;

public interface TaxService {
    List<Tax> getTaxList() throws Exception;

    boolean addTax(Tax tax) throws Exception;

    Tax getTaxById(Long taxId) throws Exception;

    boolean updateTax(Tax tax) throws Exception;

    boolean deleteTax(Long taxId) throws Exception;

    List<Tax> searchTaxData(String keyword) throws Exception;
}
