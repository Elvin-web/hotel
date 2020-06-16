package az.elvin.hotel.model;

public class Floor extends HotelModel{
    private String name;
    private String floorNumber;
    private String description;
    private int action;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

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

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return "Floor{" + "name=" + name + ", floorNumber=" + floorNumber + ", description=" + description + ", action=" + action + '}';
    }

}
