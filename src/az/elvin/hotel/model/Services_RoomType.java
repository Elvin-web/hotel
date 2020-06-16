package az.elvin.hotel.model;

public class Services_RoomType extends HotelModel{
    private Services services;
    private RoomType roomType;

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Services_RoomType{" +
                "services=" + services +
                ", roomType=" + roomType +
                '}';
    }
}
