package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.PositionDao;
import az.elvin.hotel.model.Position;
import az.elvin.hotel.service.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    private PositionDao positionDao;
    public PositionServiceImpl(PositionDao positionDao) {
        this.positionDao = positionDao;
    }



    @Override
    public List<Position> getPositionList() throws Exception {
        return positionDao.getPositionList();
    }

    @Override
    public boolean addPosition(Position position) throws Exception {
        return positionDao.addPosition(position);
    }

    @Override
    public Position getPositionById(Long positionId) throws Exception {
        return positionDao.getPositionById(positionId);
    }

    @Override
    public boolean updatePosition(Position position) throws Exception {
        return positionDao.updatePosition(position);
    }

    @Override
    public boolean deletePosition(Long positionId) throws Exception {
        return positionDao.deletePosition(positionId);
    }

    @Override
    public List<Position> searchPositionData(String keyword) throws Exception {
        return positionDao.searchPositionData(keyword);
    }
}
