package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.ServicesDao;
import az.elvin.hotel.model.Services;
import az.elvin.hotel.service.ServicesService;

import java.util.List;

public class ServicesServiceImpl implements ServicesService {
    private ServicesDao servicesDao;

    public ServicesServiceImpl(ServicesDao servicesDao) {
        this.servicesDao = servicesDao;
    }

    @Override
    public List<Services> getServicesList() throws Exception {
        return servicesDao.getServicesList();
    }

    @Override
    public boolean addServices(Services services) throws Exception {
        return servicesDao.addServices(services);
    }

    @Override
    public Services getServicesById(Long servicesId) throws Exception {
        return servicesDao.getServicesById(servicesId);
    }

    @Override
    public boolean updateServices(Services services) throws Exception {
        return servicesDao.updateServices(services);
    }

    @Override
    public boolean deleteServices(Long servicesId) throws Exception {
        return servicesDao.deleteServices(servicesId);
    }

    @Override
    public List<Services> searchServicesData(String keyword) throws Exception {
        return servicesDao.searchServicesData(keyword);
    }
}
