package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.HousekeepingStatusDao;
import az.elvin.hotel.model.HousekeepingStatus;
import az.elvin.hotel.service.HousekeepingStatusService;

import java.util.List;

public class HousekeepingStatusServiceImpl implements HousekeepingStatusService {
    private HousekeepingStatusDao housekeepingStatusDao;

    public HousekeepingStatusServiceImpl(HousekeepingStatusDao housekeepingStatusDao) {
        this.housekeepingStatusDao = housekeepingStatusDao;
    }

    @Override
    public List<HousekeepingStatus> getHousekeepingStatusList() throws Exception {
        return housekeepingStatusDao.getHousekeepingStatusList();
    }

    @Override
    public boolean addHousekeepingStatus(HousekeepingStatus housekeepingStatus) throws Exception {
        return housekeepingStatusDao.addHousekeepingStatus(housekeepingStatus);
    }

    @Override
    public HousekeepingStatus getHousekeepingStatusById(Long housekeepingStatusId) throws Exception {
        return housekeepingStatusDao.getHousekeepingStatusById(housekeepingStatusId);
    }

    @Override
    public boolean updateHousekeepingStatus(HousekeepingStatus housekeepingStatus) throws Exception {
        return housekeepingStatusDao.updateHousekeepingStatus(housekeepingStatus);
    }

    @Override
    public boolean deleteHousekeepingStatus(Long housekeepingStatusId) throws Exception {
        return housekeepingStatusDao.deleteHousekeepingStatus(housekeepingStatusId);
    }

    @Override
    public List<HousekeepingStatus> searchHousekeepingStatusData(String keyword) throws Exception {
        return housekeepingStatusDao.searchHousekeepingStatusData(keyword);
    }
}
