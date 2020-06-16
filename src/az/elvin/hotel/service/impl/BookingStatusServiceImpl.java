package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.BookingStatusDao;
import az.elvin.hotel.model.BookingStatus;
import az.elvin.hotel.service.BookingStatusService;

import java.util.List;

public class BookingStatusServiceImpl implements BookingStatusService {
    private BookingStatusDao bookingStatusDao;
    public BookingStatusServiceImpl(BookingStatusDao bookingStatusDao) {
        this.bookingStatusDao = bookingStatusDao;
    }

    @Override
    public List<BookingStatus> getBookingStatusList() throws Exception {
        return bookingStatusDao.getBookingStatusList();
    }

    @Override
    public boolean addBookingStatus(BookingStatus bookingStatus) throws Exception {
        return bookingStatusDao.addBookingStatus(bookingStatus);
    }

    @Override
    public BookingStatus getBookingStatusById(Long bookingStatusId) throws Exception {
        return bookingStatusDao.getBookingStatusById(bookingStatusId);
    }

    @Override
    public boolean updateBookingStatus(BookingStatus bookingStatus) throws Exception {
        return bookingStatusDao.updateBookingStatus(bookingStatus);
    }

    @Override
    public boolean deleteBookingStatus(Long bookingStatusId) throws Exception {
        return bookingStatusDao.deleteBookingStatus(bookingStatusId);
    }

    @Override
    public List<BookingStatus> searchBookingStatusData(String keyword) throws Exception {
        return bookingStatusDao.searchBookingStatusData(keyword);
    }
}
