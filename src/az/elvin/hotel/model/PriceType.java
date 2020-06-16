package az.elvin.hotel.model;

public class PriceType extends HotelModel {
    private String priceType;

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    @Override
    public String toString() {
        return "PriceType{" + "priceType=" + priceType + '}';
    }
}
