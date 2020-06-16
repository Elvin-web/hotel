package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.HousekeepingDao;
import az.elvin.hotel.model.Housekeeping;
import az.elvin.hotel.service.HousekeepingService;

import java.util.List;

public class HousekeepingServiceImpl implements HousekeepingService {
    private HousekeepingDao housekeepingDao;

    public HousekeepingServiceImpl(HousekeepingDao housekeepingDao) {
        this.housekeepingDao = housekeepingDao;
    }

    @Override
    public List<Housekeeping> getHousekeepingList() throws Exception {
        return housekeepingDao.getHousekeepingList();
    }

    @Override
    public List<Housekeeping> getHousekeepingListByRoomId(Long roomId) throws Exception {
        return housekeepingDao.getHousekeepingListByRoomId(roomId);
    }
    @Override
    public List<Housekeeping> getHousekeepingListByRoomId1(Long roomId) throws Exception {
        return housekeepingDao.getHousekeepingListByRoomId1(roomId);
    }
    @Override
    public boolean addHousekeeping(Housekeeping housekeeping) throws Exception {
        return housekeepingDao.addHousekeeping(housekeeping);
    }


    @Override
    public Housekeeping getHousekeepingById(Long housekeepingId) throws Exception {
        return housekeepingDao.getHousekeepingById(housekeepingId);
    }

    @Override
    public boolean updateHousekeeping(Housekeeping housekeeping) throws Exception {
        return housekeepingDao.updateHousekeeping(housekeeping);
    }

    @Override
    public boolean deleteHousekeeping(Long housekeepingId) throws Exception {
        return housekeepingDao.deleteHousekeeping(housekeepingId);
    }

    @Override
    public List<Housekeeping> searchHousekeepingData(String keyword) throws Exception {
        return housekeepingDao.searchHousekeepingData(keyword);
    }
}
