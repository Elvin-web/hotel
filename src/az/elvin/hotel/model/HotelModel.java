package az.elvin.hotel.model;

import java.util.Date;

public abstract class HotelModel {
    private Long id;
    private Integer active;
    private Long r;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Long getR() {
        return r;
    }

    public void setR(Long r) {
        this.r = r;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HotelModel{" +
                "id=" + id +
                ", active=" + active +
                ", r=" + r +
                ", date=" + date +
                '}';
    }
}
