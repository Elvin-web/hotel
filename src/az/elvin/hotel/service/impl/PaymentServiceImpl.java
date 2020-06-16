package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.PaymentDao;
import az.elvin.hotel.model.Payment;
import az.elvin.hotel.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Payment> getPaymentList() throws Exception {
        return paymentDao.getPaymentList();
    }

    @Override
    public List<Payment> getPaymentListByGuestId(Long guestId) throws Exception {
        return paymentDao.getPaymentListByGuestId(guestId);
    }

    @Override
    public List<Payment> getPaymentListByReservationId(Long reservationId) throws Exception {
        return paymentDao.getPaymentListByReservationId(reservationId);
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        return paymentDao.getPaymentById(paymentId);
    }

    @Override
    public Payment getPaymentByReservationId(Long reservationId) throws Exception {
        return paymentDao.getPaymentByReservationId(reservationId);
    }

    @Override
    public Payment getPaymentByReservationId1(Long reservationId) throws Exception {
        return paymentDao.getPaymentByReservationId1(reservationId);
    }

    @Override
    public Payment getPaymentByReservationId2(Long reservationId) throws Exception {
        return paymentDao.getPaymentByReservationId2(reservationId);
    }

    @Override
    public boolean updatePayment(Payment payment) throws Exception {
        return paymentDao.updatePayment(payment);
    }

    @Override
    public boolean updatePayment1(Payment payment) throws Exception {
        return paymentDao.updatePayment1(payment);
    }

    @Override
    public boolean deletePayment(Long paymentId) throws Exception {
        return paymentDao.deletePayment(paymentId);
    }

    @Override
    public List<Payment> searchPaymentData(String keyword) throws Exception {
        return paymentDao.searchPaymentData(keyword);
    }

    @Override
    public Long ToDayRevenueCount() throws Exception {
        return paymentDao.ToDayRevenueCount();
    }

    @Override
    public Long ToWeekIncomeCount() throws Exception {
        return paymentDao.ToWeekIncomeCount();
    }
}
