package az.elvin.hotel.service;

import az.elvin.hotel.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRoomList() throws Exception;
    boolean addRoom(Room room) throws Exception;
    Room getRoomById(Long roomId) throws Exception;
    boolean updateRoom(Room room) throws Exception;
    boolean deleteRoom(Long roomId) throws Exception;
    List<Room> searchRoomData(String keyword) throws Exception;
    Long roomAcount() throws Exception;
}
