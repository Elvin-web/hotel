package az.elvin.hotel.model;

public class HousekeepingStatus extends HotelModel{
    private String name;
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

    @Override
    public String toString() {
        return "HousekeepingStatus{" + "name=" + name + ", description=" + description + ", action=" + action + '}';
    }

}
