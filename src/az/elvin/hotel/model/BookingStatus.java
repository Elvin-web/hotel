package az.elvin.hotel.model;

public class BookingStatus extends HotelModel{
    private String bookingStatus;

    public String getBookingStatus()  {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "BookingStatus{" + "bookingStatus=" + bookingStatus + '}';
    }
}
