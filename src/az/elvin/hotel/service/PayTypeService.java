package az.elvin.hotel.service;

import az.elvin.hotel.model.PayType;

import java.util.List;

public interface PayTypeService {
    List<PayType> getPayTypeList() throws Exception;

    boolean addPayType(PayType payType) throws Exception;

    PayType getPayTypeById(Long payTypeId) throws Exception;

    boolean updatePayType(PayType payType) throws Exception;

    boolean deletePayType(Long payTypeId) throws Exception;

    List<PayType> searchPayTypeData(String keyword) throws Exception;
}
