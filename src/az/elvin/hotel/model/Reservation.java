package az.elvin.hotel.model;

import java.util.Date;

public class Reservation extends HotelModel {

    private Guest guest;
    private RoomType roomType;
    private long adults;
    private long children;
    private Date checkIn;
    private Date checkOut;
    private BookingStatus bookingStatus;
    private Hotel hotel;
    private long night;
    private int extaBed;
    private PaymentStatus paymentStatus;

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getExtaBed() {
        return extaBed;
    }

    public void setExtaBed(int extaBed) {
        this.extaBed = extaBed;
    }

    public long getNight() {
        return night;
    }

    public void setNight(long night) {
        this.night = night;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public long getAdults() {
        return adults;
    }

    public void setAdults(long adults) {
        this.adults = adults;
    }

    public long getChildren() {
        return children;
    }

    public void setChildren(long children) {
        this.children = children;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "guest=" + guest +
                ", roomType=" + roomType +
                ", adults=" + adults +
                ", children=" + children +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", bookingStatus=" + bookingStatus +
                ", hotel=" + hotel +
                ", night=" + night +
                ", extaBed=" + extaBed +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}

