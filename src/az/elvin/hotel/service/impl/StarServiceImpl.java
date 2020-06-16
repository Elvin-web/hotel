package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.StarDao;
import az.elvin.hotel.model.Star;
import az.elvin.hotel.service.StarService;

import java.util.List;

public class StarServiceImpl implements StarService {
    private StarDao starDao;
    public StarServiceImpl(StarDao starDao) {
        this.starDao = starDao;
    }

    @Override
    public List<Star> getStarList() throws Exception {
        return starDao.getStarList();
    }
    @Override
    public boolean addStar(Star star) throws Exception {
        return starDao.addStar(star);
    }
    @Override
    public Star getStarById(Long starId) throws Exception {
        return starDao.getStarById(starId);
    }
    @Override
    public boolean updateStar(Star star) throws Exception {
        return starDao.updateStar(star);
    }
    @Override
    public boolean deleteStar(Long starId) throws Exception {
        return starDao.deleteStar(starId);
    }
    @Override
    public List<Star> searchStarData(String keyword) throws Exception {
        return starDao.searchStarData(keyword);
    }
}
