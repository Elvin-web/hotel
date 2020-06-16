package az.elvin.hotel.dao;

import az.elvin.hotel.model.RoomType;

import java.util.List;

public interface RoomTypeDao {

    List<RoomType> getRoomTypeList() throws Exception;

    boolean addRoomType(RoomType roomType) throws Exception;

    RoomType getRoomTypeById(Long RoomTypeId) throws Exception;

    boolean updateRoomType(RoomType roomType) throws Exception;

    boolean deleteRoomType(Long roomTypeId) throws Exception;

    List<RoomType> searchRoomTypeData(String keyword) throws Exception;
    Long roomTypeAcount() throws Exception;
}
