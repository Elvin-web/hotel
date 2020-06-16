package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.Service_ReservationDao;
import az.elvin.hotel.model.PriceType;
import az.elvin.hotel.model.Reservation;
import az.elvin.hotel.model.Service_Reservation;
import az.elvin.hotel.model.Services;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Service_ReservationDaoImpl implements Service_ReservationDao {
    @Override
    public boolean addService_Reservation(Service_Reservation service_reservationvices) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  services_reservation (reservation_id,services_id) VALUES (?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, service_reservationvices.getReservation().getId());
                ps.setLong(2, service_reservationvices.getServices().getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Service_Reservation> getService_ReservationListByreservationId(Long reservationId) throws Exception {
        List<Service_Reservation> service_reservationList = new ArrayList<Service_Reservation>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sr.id_services_reservation,sr.data_date,r.id_reservation,\n"
                + "        s.id_services,s.name,s.amount,\n"
                + "        pt.id_price_type,pt.price_type\n"
                + "         FROM services_reservation sr\n"
                + "         INNER JOIN reservation r\n"
                + "            ON sr.reservation_id = r.id_reservation\n"
                + "         INNER JOIN services s\n"
                + "            ON sr.services_id = s.id_services\n"
                + "         INNER JOIN price_type pt\n"
                + "            ON s.price_type_id = pt.id_price_type\n"
                + "   WHERE sr.active = 1\n"
                + "AND r.id_reservation=?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Service_Reservation service_reservation = new Service_Reservation();
                    service_reservation.setId(rs.getLong("id_services_reservation"));
                    service_reservation.setDate(rs.getTimestamp("data_date"));
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    Services services = new Services();
                    services.setId(rs.getLong("id_services"));
                    services.setAmount(rs.getDouble("amount"));
                    services.setName(rs.getString("name"));
                    PriceType priceType=new PriceType();
                    priceType.setId(rs.getLong("id_price_type"));
                    priceType.setPriceType(rs.getString("price_type"));
                    services.setPriceType(priceType);
                    service_reservation.setServices(services);
                    service_reservation.setReservation(reservation);
                    service_reservationList.add(service_reservation);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return service_reservationList;
    }
}

