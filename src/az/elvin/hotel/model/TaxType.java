package az.elvin.hotel.model;

public class TaxType extends HotelModel{
    private String taxType;

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    @Override
    public String toString() {
        return "TaxType{" + "taxType=" + taxType + '}';
    }
}
