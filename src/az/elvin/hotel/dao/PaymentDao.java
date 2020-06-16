package az.elvin.hotel.dao;

import az.elvin.hotel.model.Payment;

import java.util.List;

public interface PaymentDao {
    List<Payment> getPaymentList() throws Exception;

    List<Payment> getPaymentListByGuestId(Long guestId) throws Exception;

    List<Payment> getPaymentListByReservationId(Long reservationId) throws Exception;

    boolean addPayment(Payment payment) throws Exception;

    Payment getPaymentById(Long paymentId) throws Exception;

    Payment getPaymentByReservationId(Long reservationId) throws Exception;

    Payment getPaymentByReservationId1(Long reservationId) throws Exception;

    Payment getPaymentByReservationId2(Long reservationId) throws Exception;

    boolean updatePayment(Payment payment) throws Exception;

    boolean updatePayment1(Payment payment) throws Exception;

    boolean deletePayment(Long paymentId) throws Exception;

    List<Payment> searchPaymentData(String keyword) throws Exception;
    Long ToDayRevenueCount() throws Exception;
    Long ToWeekIncomeCount() throws Exception;
}
