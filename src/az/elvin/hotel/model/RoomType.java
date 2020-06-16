package az.elvin.hotel.model;

import java.sql.Blob;
import java.util.Arrays;

public class RoomType extends HotelModel {
    private String roomType;
    private String slug;
    private String shortCode;
    private String description;
    private RoomStructure roomStructure;
    private Price price;
     private String image;
    // private byte[] image;
   // private Blob image;


    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomStructure getRoomStructure() {
        return roomStructure;
    }

    public void setRoomStructure(RoomStructure roomStructure) {
        this.roomStructure = roomStructure;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "roomType='" + roomType + '\'' +
                ", slug='" + slug + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", description='" + description + '\'' +
                ", roomStructure=" + roomStructure +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
