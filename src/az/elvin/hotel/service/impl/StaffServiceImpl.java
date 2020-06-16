package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.StaffDao;
import az.elvin.hotel.model.Staff;
import az.elvin.hotel.service.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    private StaffDao staffDao;
    public StaffServiceImpl(StaffDao staffDao) {
        this.staffDao = staffDao;
    }


    @Override
    public List<Staff> getStaffList() throws Exception {
        return staffDao.getStaffList();
    }

    @Override
    public boolean addStaff(Staff staff) throws Exception {
        return staffDao.addStaff(staff);
    }

    @Override
    public Staff getStaffById(Long staffId) throws Exception {
        return staffDao.getStaffById(staffId);
    }

    @Override
    public boolean updateStaff(Staff staff) throws Exception {
        return staffDao.updateStaff(staff);
    }

    @Override
    public boolean deleteStaff(Long staffId) throws Exception {
        return staffDao.deleteStaff(staffId);
    }

    @Override
    public List<Staff> searchStaffData(String keyword) throws Exception {
        return staffDao.searchStaffData(keyword);
    }
}
