package az.elvin.hotel.service;

import az.elvin.hotel.model.AdvancedSearch;
import az.elvin.hotel.model.Reservation;
import az.elvin.hotel.model.Room_Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationList() throws Exception;

    boolean addReservation(Reservation reservation) throws Exception;

    boolean addRoomReservation(Room_Reservation room_reservation) throws Exception;

    Reservation getReservationById(Long reservationId) throws Exception;

    List<Room_Reservation> getReservationRoomById(Long reservationId) throws Exception;

    boolean updateReservation(Reservation reservation) throws Exception;

    boolean updateReservation1(Reservation reservation) throws Exception;

    boolean updateReservation2(Reservation reservation) throws Exception;

    boolean deleteReservation(Long reservationId) throws Exception;

    List<Reservation> searchReservationData(String keyword) throws Exception;

    List<Reservation> advancedSearchReservationData(AdvancedSearch advancedSearch) throws Exception;

    Long reservationTodayAcount() throws Exception;

    Long reservationAcount() throws Exception;
    Long reservationGuestAcount() throws Exception;
}
