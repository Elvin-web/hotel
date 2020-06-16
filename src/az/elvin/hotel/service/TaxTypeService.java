package az.elvin.hotel.service;

import az.elvin.hotel.model.TaxType;

import java.util.List;

public interface TaxTypeService {
    List<TaxType> getTaxTypeList() throws Exception;

    boolean addTaxType(TaxType taxType) throws Exception;

    TaxType getTaxTypeById(Long taxTypeId) throws Exception;

    boolean updateTaxType(TaxType taxType) throws Exception;

    boolean deleteTaxType(Long taxTypeId) throws Exception;

    List<TaxType> searchTaxTypeData(String keyword) throws Exception;
}
