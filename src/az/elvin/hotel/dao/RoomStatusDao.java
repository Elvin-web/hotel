package az.elvin.hotel.dao;

import az.elvin.hotel.model.RoomStatus;

import java.util.List;

public interface RoomStatusDao {
    List<RoomStatus> getRoomStatusList() throws Exception;

    boolean addRoomStatus(RoomStatus roomStatus) throws Exception;

    RoomStatus getRoomStatusById(Long roomStatusId) throws Exception;

    boolean updateRoomStatus(RoomStatus roomStatus) throws Exception;

    boolean deleteRoomStatus(Long roomStatusId) throws Exception;

    List<RoomStatus> searchRoomStatusData(String keyword) throws Exception;
}
