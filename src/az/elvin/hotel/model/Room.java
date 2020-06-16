package az.elvin.hotel.model;

import java.util.Date;

public class Room extends HotelModel {

    private Floor floor;
    private long roomNumber;
    private RoomStatus roomStatus;
    private HousekeepingStatus housekeepingStatus;
    private Staff staff;
    private Date housekeepingStatusDate;
    private RoomType roomType;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
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

    public Date getHousekeepingStatusDate() {
        return housekeepingStatusDate;
    }

    public void setHousekeepingStatusDate(Date housekeepingStatusDate) {
        this.housekeepingStatusDate = housekeepingStatusDate;
    }

    @Override
    public String toString() {
        return "Room{" +
                "floor=" + floor +
                ", roomNumber=" + roomNumber +
                ", roomStatus=" + roomStatus +
                ", housekeepingStatus=" + housekeepingStatus +
                ", staff=" + staff +
                ", housekeepingStatusDate=" + housekeepingStatusDate +
                ", roomType=" + roomType +
                '}';
    }
}

