package az.elvin.hotel.service;

import az.elvin.hotel.model.Star;

import java.util.List;

public interface StarService {
    List<Star> getStarList() throws Exception;

    boolean addStar(Star star) throws Exception;

    Star getStarById(Long starId) throws Exception;

    boolean updateStar(Star star) throws Exception;

    boolean deleteStar(Long starId) throws Exception;

    List<Star> searchStarData(String keyword) throws Exception;
}
