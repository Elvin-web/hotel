package az.elvin.hotel.dao;

import az.elvin.hotel.model.PayType;

import java.util.List;

public interface PayTypeDao {
    List<PayType> getPayTypeList() throws Exception;

    boolean addPayType(PayType payType) throws Exception;

    PayType getPayTypeById(Long payTypeId) throws Exception;

    boolean updatePayType(PayType payType) throws Exception;

    boolean deletePayType(Long payTypeId) throws Exception;

    List<PayType> searchPayTypeData(String keyword) throws Exception;
}
