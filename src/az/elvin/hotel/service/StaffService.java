package az.elvin.hotel.service;

import az.elvin.hotel.model.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getStaffList() throws Exception;

    boolean addStaff(Staff staff) throws Exception;

    Staff getStaffById(Long staffId) throws Exception;

    boolean updateStaff(Staff staff) throws Exception;

    boolean deleteStaff(Long staffId) throws Exception;

    List<Staff> searchStaffData(String keyword) throws Exception;
}
