package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.Service_ReservationDao;
import az.elvin.hotel.model.Service_Reservation;
import az.elvin.hotel.service.Service_ReservationService;

import java.util.List;

public class Service_ReservationServiceImpl implements Service_ReservationService {
    private Service_ReservationDao service_reservationDao;

    public Service_ReservationServiceImpl(Service_ReservationDao service_reservationDao) {
        this.service_reservationDao = service_reservationDao;
    }
    @Override
    public boolean addService_Reservation(Service_Reservation service_reservationvices) throws Exception {
        return service_reservationDao.addService_Reservation(service_reservationvices);
    }

    @Override
    public List<Service_Reservation> getService_ReservationListByreservationId(Long reservationId) throws Exception {
        return service_reservationDao.getService_ReservationListByreservationId(reservationId);
    }
}
