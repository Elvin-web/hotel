package az.elvin.hotel.model;

public class Services extends HotelModel{
    private String name;
    private RoomType roomType;
    private PriceType priceType;
    private Double amount;
    private int action;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Services{" + "name=" + name + ", roomType=" + roomType + ", priceType=" + priceType + ", amount=" + amount + ", action=" + action + ", description=" + description + '}';
    }
}
