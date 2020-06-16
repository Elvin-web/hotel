package az.elvin.hotel.dao;

import az.elvin.hotel.model.AdvancedSearch;
import az.elvin.hotel.model.Guest;

import java.util.List;

public interface GuestDao {
    List<Guest> getGuestList() throws Exception;

    boolean addGuest(Guest guest) throws Exception;

    Guest getGuestById(Long guestId) throws Exception;

    boolean updateGuest(Guest guest) throws Exception;

    boolean deleteGuest(Long guestId) throws Exception;

    List<Guest> searchGuestData(String keyword) throws Exception;

    List<Guest> advancedSearchGuestData(AdvancedSearch advancedSearch) throws Exception;
    Long guestAcount() throws Exception;
}
