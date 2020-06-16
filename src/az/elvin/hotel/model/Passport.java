package az.elvin.hotel.model;

public class Passport extends HotelModel{
    private String passportType;

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    @Override
    public String toString() {
        return "Passport{" + "passportType=" + passportType + '}';
    }

}
