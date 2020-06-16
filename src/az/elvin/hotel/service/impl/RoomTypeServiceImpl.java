package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.RoomTypeDao;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.service.RoomTypeService;

import java.util.List;

public class RoomTypeServiceImpl implements RoomTypeService {
    private RoomTypeDao roomTypeDao;
    public RoomTypeServiceImpl(RoomTypeDao roomTypeDao) {
        this.roomTypeDao = roomTypeDao;
    }



    @Override
    public List<RoomType> getRoomTypeList() throws Exception {
        return roomTypeDao.getRoomTypeList();
    }

    @Override
    public boolean addRoomType(RoomType roomType) throws Exception {
        return roomTypeDao.addRoomType(roomType);
    }

    @Override
    public RoomType getRoomTypeById(Long RoomTypeId) throws Exception {
        return roomTypeDao.getRoomTypeById(RoomTypeId);
    }

    @Override
    public boolean updateRoomType(RoomType roomType) throws Exception {
        return roomTypeDao.updateRoomType(roomType);
    }

    @Override
    public boolean deleteRoomType(Long roomTypeId) throws Exception {
        return roomTypeDao.deleteRoomType(roomTypeId);
    }

    @Override
    public List<RoomType> searchRoomTypeData(String keyword) throws Exception {
        return roomTypeDao.searchRoomTypeData(keyword);
    }

    @Override
    public Long roomTypeAcount() throws Exception {
        return roomTypeDao.roomTypeAcount();
    }
}
