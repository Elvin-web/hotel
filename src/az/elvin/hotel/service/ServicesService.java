package az.elvin.hotel.service;

import az.elvin.hotel.model.Services;

import java.util.List;

public interface ServicesService {
    List<Services> getServicesList() throws Exception;

    boolean addServices(Services services) throws Exception;

    Services getServicesById(Long servicesId) throws Exception;

    boolean updateServices(Services services) throws Exception;

    boolean deleteServices(Long servicesId) throws Exception;

    List<Services> searchServicesData(String keyword) throws Exception;
}
