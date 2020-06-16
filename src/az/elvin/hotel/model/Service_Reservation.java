package az.elvin.hotel.model;

public class Service_Reservation extends HotelModel {
    private Services services;
    private Reservation reservation;

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Service_Reservation{" +
                "services=" + services +
                ", reservation=" + reservation +
                '}';
    }
}
