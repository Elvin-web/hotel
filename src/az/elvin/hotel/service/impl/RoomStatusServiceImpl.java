package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.RoomStatusDao;
import az.elvin.hotel.model.RoomStatus;
import az.elvin.hotel.service.RoomStatusService;

import java.util.List;

public class RoomStatusServiceImpl implements RoomStatusService {
    private RoomStatusDao  roomStatusDao;

    public RoomStatusServiceImpl(RoomStatusDao roomStatusDao) {
        this.roomStatusDao = roomStatusDao;
    }


    @Override
    public List<RoomStatus> getRoomStatusList() throws Exception {
        return roomStatusDao.getRoomStatusList();
    }

    @Override
    public boolean addRoomStatus(RoomStatus roomStatus) throws Exception {
        return roomStatusDao.addRoomStatus(roomStatus);
    }

    @Override
    public RoomStatus getRoomStatusById(Long roomStatusId) throws Exception {
        return roomStatusDao.getRoomStatusById(roomStatusId);
    }

    @Override
    public boolean updateRoomStatus(RoomStatus roomStatus) throws Exception {
        return roomStatusDao.updateRoomStatus(roomStatus);
    }

    @Override
    public boolean deleteRoomStatus(Long roomStatusId) throws Exception {
        return roomStatusDao.deleteRoomStatus(roomStatusId);
    }

    @Override
    public List<RoomStatus> searchRoomStatusData(String keyword) throws Exception {
        return roomStatusDao.searchRoomStatusData(keyword);
    }
}
