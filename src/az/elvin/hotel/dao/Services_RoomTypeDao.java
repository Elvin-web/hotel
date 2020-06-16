package az.elvin.hotel.dao;

import az.elvin.hotel.model.Services_RoomType;

import java.util.List;

public interface Services_RoomTypeDao {
    List<Services_RoomType> getServices_RoomTypeList() throws Exception;

    boolean addServices_RoomType(Services_RoomType services_roomType) throws Exception;

    Services_RoomType getServices_RoomTypeById(Long services_roomTypeId) throws Exception;

    boolean updateServices_RoomType(Services_RoomType services_roomType) throws Exception;

    boolean deleteServices_RoomType(Long services_roomTypeId) throws Exception;

    List<Services_RoomType> searchServices_RoomTypeData(String keyword) throws Exception;
}
