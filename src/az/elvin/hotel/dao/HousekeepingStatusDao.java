package az.elvin.hotel.dao;

import az.elvin.hotel.model.HousekeepingStatus;

import java.util.List;

public interface HousekeepingStatusDao {
    List<HousekeepingStatus> getHousekeepingStatusList() throws Exception;

    boolean addHousekeepingStatus(HousekeepingStatus housekeepingStatus) throws Exception;

    HousekeepingStatus getHousekeepingStatusById(Long housekeepingStatusId) throws Exception;

    boolean updateHousekeepingStatus(HousekeepingStatus housekeepingStatus) throws Exception;

    boolean deleteHousekeepingStatus(Long housekeepingStatusId) throws Exception;

    List<HousekeepingStatus> searchHousekeepingStatusData(String keyword) throws Exception;
}
