package az.elvin.hotel.service;

import az.elvin.hotel.model.Service_Reservation;

import java.util.List;

public interface Service_ReservationService {
    boolean addService_Reservation(Service_Reservation service_reservationvices) throws Exception;
    List<Service_Reservation> getService_ReservationListByreservationId(Long reservationId) throws Exception;
}
