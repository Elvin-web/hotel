package az.elvin.hotel.service;

import az.elvin.hotel.model.Floor;

import java.util.List;

public interface FloorService {
    List<Floor> getFloorList() throws Exception;

    boolean addFloor(Floor floor) throws Exception;

    Floor getFloorById(Long floorId) throws Exception;

    boolean updateFloor(Floor floor) throws Exception;

    boolean deleteFloor(Long floorId) throws Exception;

    List<Floor> searchFloorData(String keyword) throws Exception;
    Long floorAcount() throws Exception;
}
