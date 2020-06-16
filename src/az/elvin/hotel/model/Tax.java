package az.elvin.hotel.model;

public class Tax extends HotelModel{
    private String name;
    private String code;
    private Double rate;
    private TaxType taxType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    @Override
    public String toString() {
        return "Tax{" + "name=" + name + ", code=" + code + ", rate=" + rate + ", taxType=" + taxType + '}';
    }

}
