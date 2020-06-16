package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.PaymentStatusDao;
import az.elvin.hotel.model.PaymentStatus;
import az.elvin.hotel.service.PaymentStatusService;

import java.util.List;

public class PaymentStatusServiceImpl implements PaymentStatusService {
    private PaymentStatusDao paymentStatusDao;

    public PaymentStatusServiceImpl(PaymentStatusDao paymentStatusDao) {
        this.paymentStatusDao = paymentStatusDao;
    }

    @Override
    public List<PaymentStatus> getPaymentStatusList() throws Exception {
        return paymentStatusDao.getPaymentStatusList();
    }

    @Override
    public boolean addPaymentStatus(PaymentStatus paymentStatus) throws Exception {
        return paymentStatusDao.addPaymentStatus(paymentStatus);
    }

    @Override
    public PaymentStatus getPaymentStatusById(Long paymentStatusId) throws Exception {
        return paymentStatusDao.getPaymentStatusById(paymentStatusId);
    }

    @Override
    public boolean updatePaymentStatus(PaymentStatus paymentStatus) throws Exception {
        return paymentStatusDao.updatePaymentStatus(paymentStatus);
    }

    @Override
    public boolean deletePaymentStatus(Long paymentStatusId) throws Exception {
        return paymentStatusDao.deletePaymentStatus(paymentStatusId);
    }

    @Override
    public List<PaymentStatus> searchPaymentStatusData(String keyword) throws Exception {
        return paymentStatusDao.searchPaymentStatusData(keyword);
    }
}
