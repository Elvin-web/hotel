package az.elvin.hotel.model;

import java.util.Date;

public class Housekeeping extends HotelModel {
    private HousekeepingStatus housekeepingStatus;
    private Staff staff;
    private Room room;
    private Date cleanDate;
    private String remark;
    private Date date;

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HousekeepingStatus getHousekeepingStatus() {
        return housekeepingStatus;
    }

    public void setHousekeepingStatus(HousekeepingStatus housekeepingStatus) {
        this.housekeepingStatus = housekeepingStatus;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCleanDate() {
        return cleanDate;
    }

    public void setCleanDate(Date cleanDate) {
        this.cleanDate = cleanDate;
    }

    @Override
    public String toString() {
        return "Housekeeping{" +
                "housekeepingStatus=" + housekeepingStatus +
                ", staff=" + staff +
                ", room=" + room +
                ", cleanDate=" + cleanDate +
                ", remark='" + remark + '\'' +
                ", date=" + date +
                '}';
    }
}
