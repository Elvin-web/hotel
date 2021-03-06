package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.RoomDao;
import az.elvin.hotel.model.Room;
import az.elvin.hotel.service.RoomService;

import java.util.List;

public class RoomServiceImpl  implements RoomService {
    private RoomDao roomDao;
    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }



    @Override
    public List<Room> getRoomList() throws Exception {
        return roomDao.getRoomList();
    }

    @Override
    public boolean addRoom(Room room) throws Exception {
        return roomDao.addRoom(room);
    }

    @Override
    public Room getRoomById(Long roomId) throws Exception {
        return roomDao.getRoomById(roomId);
    }

    @Override
    public boolean updateRoom(Room room) throws Exception {
        return roomDao.updateRoom(room);
    }

    @Override
    public boolean deleteRoom(Long roomId) throws Exception {
        return roomDao.deleteRoom(roomId);
    }

    @Override
    public List<Room> searchRoomData(String keyword) throws Exception {
        return roomDao.searchRoomData(keyword);
    }

    @Override
    public Long roomAcount() throws Exception {
        return roomDao.roomAcount();
    }
}
