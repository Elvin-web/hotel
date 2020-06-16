package az.elvin.hotel.model;

public class Dictionary extends HotelModel{
    private Room room;
    private Amenities amenities;


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Dictionary{" + "room=" + room + ", amenities=" + amenities + '}';
    }
}
