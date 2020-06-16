package az.elvin.hotel.dao;

import az.elvin.hotel.model.Passport;

import java.util.List;

public interface PassportDao {
    List<Passport> getPassportList() throws Exception;

    boolean addPassport(Passport passport) throws Exception;

    Passport getPassportById(Long passportId) throws Exception;

    boolean updatePassport(Passport passport) throws Exception;

    boolean deletePassport(Long passportId) throws Exception;

    List<Passport> searchPassportData(String keyword) throws Exception;
}
