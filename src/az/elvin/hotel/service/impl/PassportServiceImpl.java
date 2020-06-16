package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.PassportDao;
import az.elvin.hotel.model.Passport;
import az.elvin.hotel.service.PassportService;

import java.util.List;

public class PassportServiceImpl implements PassportService {
    private PassportDao passportDao;

    public PassportServiceImpl(PassportDao passportDao) {
        this.passportDao = passportDao;
    }

    @Override
    public List<Passport> getPassportList() throws Exception {
        return passportDao.getPassportList();
    }

    @Override
    public boolean addPassport(Passport passport) throws Exception {
        return passportDao.addPassport(passport);
    }

    @Override
    public Passport getPassportById(Long passportId) throws Exception {
        return passportDao.getPassportById(passportId);
    }

    @Override
    public boolean updatePassport(Passport passport) throws Exception {
        return passportDao.updatePassport(passport);
    }

    @Override
    public boolean deletePassport(Long passportId) throws Exception {
        return passportDao.deletePassport(passportId);
    }

    @Override
    public List<Passport> searchPassportData(String keyword) throws Exception {
        return passportDao.searchPassportData(keyword);
    }
}
