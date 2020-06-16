package az.elvin.hotel.service;

import az.elvin.hotel.model.BookingStatus;

import java.util.List;

public interface BookingStatusService {
    List<BookingStatus> getBookingStatusList() throws Exception;

    boolean addBookingStatus(BookingStatus bookingStatus) throws Exception;

    BookingStatus getBookingStatusById(Long bookingStatusId) throws Exception;

    boolean updateBookingStatus(BookingStatus bookingStatus) throws Exception;

    boolean deleteBookingStatus(Long bookingStatusId) throws Exception;

    List<BookingStatus> searchBookingStatusData(String keyword) throws Exception;
}
