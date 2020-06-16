package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.PayTypeDao;
import az.elvin.hotel.model.PayType;
import az.elvin.hotel.service.PayTypeService;

import java.util.List;

public class PayTypeServiceImpl implements PayTypeService {
    private PayTypeDao payTypeDao;
    public PayTypeServiceImpl(PayTypeDao payTypeDao) {
        this.payTypeDao = payTypeDao;
    }


    @Override
    public List<PayType> getPayTypeList() throws Exception {
        return payTypeDao.getPayTypeList();
    }

    @Override
    public boolean addPayType(PayType payType) throws Exception {
        return payTypeDao.addPayType(payType);
    }

    @Override
    public PayType getPayTypeById(Long payTypeId) throws Exception {
        return payTypeDao.getPayTypeById(payTypeId);
    }

    @Override
    public boolean updatePayType(PayType payType) throws Exception {
        return payTypeDao.updatePayType(payType);
    }

    @Override
    public boolean deletePayType(Long payTypeId) throws Exception {
        return payTypeDao.deletePayType(payTypeId);
    }

    @Override
    public List<PayType> searchPayTypeData(String keyword) throws Exception {
        return payTypeDao.searchPayTypeData(keyword);
    }
}
