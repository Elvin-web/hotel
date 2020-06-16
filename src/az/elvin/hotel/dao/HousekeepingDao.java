package az.elvin.hotel.dao;

import az.elvin.hotel.model.Housekeeping;

import java.util.List;

public interface HousekeepingDao {

    List<Housekeeping> getHousekeepingList() throws Exception;

    List<Housekeeping> getHousekeepingListByRoomId(Long roomId) throws Exception;

    List<Housekeeping> getHousekeepingListByRoomId1(Long roomId) throws Exception;

    boolean addHousekeeping(Housekeeping housekeeping) throws Exception;

    Housekeeping getHousekeepingById(Long housekeepingId) throws Exception;

    boolean updateHousekeeping(Housekeeping housekeeping) throws Exception;

    boolean deleteHousekeeping(Long housekeepingId) throws Exception;

    List<Housekeeping> searchHousekeepingData(String keyword) throws Exception;
}
