package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.GuestDao;
import az.elvin.hotel.model.AdvancedSearch;
import az.elvin.hotel.model.Guest;
import az.elvin.hotel.service.GuestService;

import java.util.List;

public class GuestServiceImpl implements GuestService {

    private GuestDao guestDao;

    public GuestServiceImpl(GuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    public List<Guest> getGuestList() throws Exception {
        return guestDao.getGuestList();
    }

    @Override
    public boolean addGuest(Guest guest) throws Exception {
        return guestDao.addGuest(guest);
    }

    @Override
    public Guest getGuestById(Long guestId) throws Exception {
        return guestDao.getGuestById(guestId);
    }

    @Override
    public boolean updateGuest(Guest guest) throws Exception {
        return guestDao.updateGuest(guest);
    }

    @Override
    public boolean deleteGuest(Long guestId) throws Exception {
        return guestDao.deleteGuest(guestId);
    }

    @Override
    public List<Guest> searchGuestData(String keyword) throws Exception {
        return guestDao.searchGuestData(keyword);
    }

    @Override
    public List<Guest> advancedSearchGuestData(AdvancedSearch advancedSearch) throws Exception {
        return guestDao.advancedSearchGuestData(advancedSearch);
    }

    @Override
    public Long guestAcount() throws Exception {
        return guestDao.guestAcount();
    }
}

