package az.elvin.hotel.model;

import java.util.Date;

public class Payment extends HotelModel {
    private Double paymentAmount;
    private PayType payType;
    private Date addedDate;
    private Reservation reservation;
    private Double allNightCost;
    private Double sum;
    private Double pending;
    private Double servicesCost;
    private Double taxCost;

    public Double getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(Double taxCost) {
        this.taxCost = taxCost;
    }

    public Double getServicesCost() {
        return servicesCost;
    }

    public void setServicesCost(Double servicesCost) {
        this.servicesCost = servicesCost;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getPending() {
        return pending;
    }

    public void setPending(Double pending) {
        this.pending = pending;
    }

    public Double getAllNightCost() {
        return allNightCost;
    }

    public void setAllNightCost(Double allNightCost) {
        this.allNightCost = allNightCost;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }


    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentAmount=" + paymentAmount +
                ", payType=" + payType +
                ", addedDate=" + addedDate +
                ", reservation=" + reservation +
                ", allNightCost=" + allNightCost +
                ", sum=" + sum +
                ", pending=" + pending +
                ", servicesCost=" + servicesCost +
                ", taxCost=" + taxCost +
                '}';
    }
}

