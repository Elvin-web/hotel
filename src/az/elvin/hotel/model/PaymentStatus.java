package az.elvin.hotel.model;

public class PaymentStatus extends HotelModel{
    private String paymentStatus;

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" + "paymentStatus=" + paymentStatus + '}';
    }
}
