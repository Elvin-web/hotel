package az.elvin.hotel.model;

public class Price extends HotelModel{
    private Double extraBedPrice;
    private Double basePrice;

    public Double getExtraBedPrice() {
        return extraBedPrice;
    }

    public void setExtraBedPrice(Double extraBedPrice) {
        this.extraBedPrice = extraBedPrice;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Price{" + "extraBedPrice=" + extraBedPrice + ", basePrice=" + basePrice + '}';
    }
}
