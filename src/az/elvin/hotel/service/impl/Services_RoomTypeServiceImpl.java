package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.Services_RoomTypeDao;
import az.elvin.hotel.model.PriceType;
import az.elvin.hotel.model.RoomType;
import az.elvin.hotel.model.Services;
import az.elvin.hotel.model.Services_RoomType;
import az.elvin.hotel.service.Services_RoomTypeService;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Services_RoomTypeServiceImpl implements Services_RoomTypeService {
    private Services_RoomTypeDao services_roomTypeDao;

    public Services_RoomTypeServiceImpl(Services_RoomTypeDao services_roomTypeDao) {
        this.services_roomTypeDao = services_roomTypeDao;
    }

    @Override
    public List<Services_RoomType> getServices_RoomTypeList() throws Exception {
        return services_roomTypeDao.getServices_RoomTypeList();
    }

    @Override
    public boolean addServices_RoomType(Services_RoomType services_roomType) throws Exception {
        return services_roomTypeDao.addServices_RoomType(services_roomType);
    }

    @Override
    public Services_RoomType getServices_RoomTypeById(Long services_roomTypeId) throws Exception {
        return services_roomTypeDao.getServices_RoomTypeById(services_roomTypeId);
    }

    @Override
    public boolean updateServices_RoomType(Services_RoomType services_roomType) throws Exception {
        return services_roomTypeDao.updateServices_RoomType(services_roomType);
    }

    @Override
    public boolean deleteServices_RoomType(Long services_roomTypeId) throws Exception {
        return services_roomTypeDao.deleteServices_RoomType(services_roomTypeId);
    }

    @Override
    public List<Services_RoomType> searchServices_RoomTypeData(String keyword) throws Exception {
        return services_roomTypeDao.searchServices_RoomTypeData(keyword);
    }


}
