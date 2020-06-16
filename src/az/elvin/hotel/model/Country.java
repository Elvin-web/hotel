package az.elvin.hotel.model;

public class Country extends HotelModel {
    private  String name;
    private String sortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return "Country{" + "name=" + name + ", sortName=" + sortName + '}';
    }

}
