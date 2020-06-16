package az.elvin.hotel.model;

import java.sql.Blob;

public class Amenities extends HotelModel {
    private String name;
    private String description;
    private int action;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
        return "Amenities{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", action=" + action +
                ", image=" + image +
                '}';
    }
}
