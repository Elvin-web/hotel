package az.elvin.hotel.service;

import az.elvin.hotel.model.PaymentStatus;

import java.util.List;

public interface PaymentStatusService {
    List<PaymentStatus> getPaymentStatusList() throws Exception;

    boolean addPaymentStatus(PaymentStatus paymentStatus) throws Exception;

    PaymentStatus getPaymentStatusById(Long paymentStatusId) throws Exception;

    boolean updatePaymentStatus(PaymentStatus paymentStatus) throws Exception;

    boolean deletePaymentStatus(Long paymentStatusId) throws Exception;

    List<PaymentStatus> searchPaymentStatusData(String keyword) throws Exception;
}
