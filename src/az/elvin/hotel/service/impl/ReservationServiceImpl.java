package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.ReservationDao;
import az.elvin.hotel.model.AdvancedSearch;
import az.elvin.hotel.model.Reservation;
import az.elvin.hotel.model.Room_Reservation;
import az.elvin.hotel.service.ReservationService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    private ReservationDao reservationDao;

    public ReservationServiceImpl(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }


    @Override
    public List<Reservation> getReservationList() throws Exception {
        return reservationDao.getReservationList();
    }

    @Override
    public boolean addReservation(Reservation reservation) throws Exception {
        return reservationDao.addReservation(reservation);
    }

    @Override
    public boolean addRoomReservation(Room_Reservation room_reservation) throws Exception {
        return reservationDao.addRoomReservation(room_reservation);
    }

    @Override
    public Reservation getReservationById(Long reservationId) throws Exception {
        return reservationDao.getReservationById(reservationId);
    }

    @Override
    public List<Room_Reservation> getReservationRoomById(Long reservationId) throws Exception {
        return reservationDao.getReservationRoomById(reservationId);
    }

    @Override
    public boolean updateReservation(Reservation reservation) throws Exception {
        return reservationDao.updateReservation(reservation);
    }
    @Override
    public boolean updateReservation1(Reservation reservation) throws Exception {
        return reservationDao.updateReservation1(reservation);
    }
    @Override
    public boolean updateReservation2(Reservation reservation) throws Exception {
        return reservationDao.updateReservation2(reservation);
    }

    @Override
    public boolean deleteReservation(Long reservationId) throws Exception {
        return reservationDao.deleteReservation(reservationId);
    }

    @Override
    public List<Reservation> searchReservationData(String keyword) throws Exception {
        return reservationDao.searchReservationData(keyword);
    }

    @Override
    public List<Reservation> advancedSearchReservationData(AdvancedSearch advancedSearch) throws Exception {
        return reservationDao.advancedSearchReservationData(advancedSearch);
    }

    @Override
    public Long reservationTodayAcount() throws Exception {
        return reservationDao.reservationTodayAcount();
    }

    @Override
    public Long reservationAcount() throws Exception {
        return reservationDao.reservationAcount();
    }

    @Override
    public Long reservationGuestAcount() throws Exception {
        return reservationDao.reservationGuestAcount();
    }
}
