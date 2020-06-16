package az.elvin.hotel.model;

public class Room_Reservation   extends HotelModel{
    private Reservation reservation;
    private Room room;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Room_Reservation{" +
                "reservation=" + reservation +
                ", room=" + room +
                '}';
    }
}
