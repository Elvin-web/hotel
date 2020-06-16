package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.FloorDao;
import az.elvin.hotel.model.Floor;
import az.elvin.hotel.service.FloorService;

import java.util.List;

public class FloorServiceImpl implements FloorService {
    private FloorDao floorDao;

    public FloorServiceImpl(FloorDao floorDao) {
        this.floorDao = floorDao;
    }

    @Override
    public List<Floor> getFloorList() throws Exception {
        return floorDao.getFloorList();
    }

    @Override
    public boolean addFloor(Floor floor) throws Exception {
        return floorDao.addFloor(floor);
    }

    @Override
    public Floor getFloorById(Long floorId) throws Exception {
        return floorDao.getFloorById(floorId);
    }

    @Override
    public boolean updateFloor(Floor floor) throws Exception {
        return floorDao.updateFloor(floor);
    }

    @Override
    public boolean deleteFloor(Long floorId) throws Exception {
        return floorDao.deleteFloor(floorId);
    }

    @Override
    public List<Floor> searchFloorData(String keyword) throws Exception {
        return floorDao.searchFloorData(keyword);
    }

    @Override
    public Long floorAcount() throws Exception {
        return floorDao.floorAcount();
    }
}
