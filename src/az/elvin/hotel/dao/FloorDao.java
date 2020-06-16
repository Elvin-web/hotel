package az.elvin.hotel.dao;

import az.elvin.hotel.model.Floor;

import java.util.List;

public interface FloorDao {
    List<Floor> getFloorList() throws Exception;

    boolean addFloor(Floor floor) throws Exception;

    Floor getFloorById(Long floorId) throws Exception;

    boolean updateFloor(Floor floor) throws Exception;

    boolean deleteFloor(Long floorId) throws Exception;

    List<Floor> searchFloorData(String keyword) throws Exception;

    Long floorAcount() throws Exception;
}
