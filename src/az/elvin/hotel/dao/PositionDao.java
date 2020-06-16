package az.elvin.hotel.dao;

import az.elvin.hotel.model.Position;

import java.util.List;

public interface PositionDao {
    List<Position> getPositionList() throws Exception;

    boolean addPosition(Position position) throws Exception;

    Position getPositionById(Long positionId) throws Exception;

    boolean updatePosition(Position position) throws Exception;

    boolean deletePosition(Long positionId) throws Exception;

    List<Position> searchPositionData(String keyword) throws Exception;
}
