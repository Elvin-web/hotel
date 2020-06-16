package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.ReservationDao;
import az.elvin.hotel.model.*;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {
    @Override
    public List<Reservation> getReservationList() throws Exception {
        List<Reservation> reservationList = new ArrayList<Reservation>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_reservation,r.adults,r.children,r.check_in,\n"
                + "         r.check_out,r.data_date,r.night,\n"
                + "         h.id_hotel,h.name,h.address,h.phone,h.email,\n"
                + "         g.id_guest,g.name guest_name,g.surname,\n"
                + "         rt.id_room_type,rt.room_type,\n"
                + "         bs.id_booking_status,bs.booking_status\n"
                + "         FROM reservation r\n"
                + "         INNER JOIN hotel h\n"
                + "            ON r.hotel_id = h.id_hotel\n"
                + "         INNER JOIN guest g\n"
                + "            ON r.guest_id = g.id_guest\n"
                + "         INNER JOIN booking_status bs\n"
                + "            ON r.booking_status_id = bs.id_booking_status\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.id_room_type\n"
                + "   WHERE r.active = 1\n"
                + "ORDER BY r.id_reservation";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    reservation.setAdults(rs.getLong("adults"));
                    reservation.setCheckIn(rs.getDate("check_in"));
                    reservation.setCheckOut(rs.getDate("check_out"));
                    reservation.setDate(rs.getDate("data_date"));
                    reservation.setChildren(rs.getLong("children"));
                    reservation.setNight(rs.getLong("night"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("name"));
                    hotel.setHotelAddress(rs.getString("address"));
                    hotel.setHotelPhone(rs.getString("phone"));
                    hotel.setHotelEmail(rs.getString("email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("surname"));
                    reservation.setGuest(guest);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    reservation.setRoomType(roomType);
                    BookingStatus bookingStatus = new BookingStatus();
                    bookingStatus.setId(rs.getLong("id_booking_status"));
                    bookingStatus.setBookingStatus(rs.getString("booking_status"));
                    reservation.setBookingStatus(bookingStatus);
                    reservationList.add(reservation);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return reservationList;
    }

    @Override
    public boolean addReservation(Reservation reservation) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  reservation (adults,check_in,check_out,children,guest_id,room_type_id,night,extra_bed) VALUES (?,?,?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservation.getAdults());
                ps.setDate(2, new java.sql.Date(reservation.getCheckIn().getTime()));
                ps.setDate(3, new java.sql.Date(reservation.getCheckOut().getTime()));
                ps.setLong(4, reservation.getChildren());
                ps.setLong(5, reservation.getGuest().getId());
                ps.setLong(6, reservation.getRoomType().getId());
                ps.setLong(7, reservation.getNight());
                ps.setLong(8, reservation.getExtaBed());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean addRoomReservation(Room_Reservation room_reservation) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  room_reservation (reservation_id,room_id) VALUES (?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, room_reservation.getReservation().getId());
                ps.setLong(2, room_reservation.getRoom().getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public Reservation getReservationById(Long reservationId) throws Exception {
        Reservation reservation = new Reservation();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_reservation,r.adults,r.children,r.check_in,\n"
                + "         r.check_out,r.data_date,r.night,\n"
                + "         h.id_hotel,h.name,h.address,h.phone,h.email,\n"
                + "         g.id_guest,g.name guest_name,g.surname,g.phone guest_phone,g.email guest_email,\n"
                + "         rt.id_room_type,rt.room_type,\n"
                + "         bs.id_booking_status,bs.booking_status,\n"
                + "         p.id_price,p.base_price,p.extra_bed_price,\n"
                + "         ps.id_payment_status,ps.payment_status,\n"
                + "         c.id_city,c.name,\n"
                + "         ct.id_country,ct.name\n"
                + "         FROM reservation r\n"
                + "         INNER JOIN hotel h\n"
                + "            ON r.hotel_id = h.id_hotel\n"
                + "         INNER JOIN guest g\n"
                + "            ON r.guest_id = g.id_guest\n"
                + "         INNER JOIN city c\n"
                + "            ON g.city_id = c.id_city\n"
                + "         INNER JOIN country ct\n"
                + "            ON c.country_id = ct.id_country\n"
                + "         INNER JOIN booking_status bs\n"
                + "            ON r.booking_status_id = bs.id_booking_status\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN price p\n"
                + "            ON rt.price_id = p.id_price\n"
                + "         INNER JOIN payment_status ps\n"
                + "            ON r.payment_status_id = ps.id_payment_status\n"
                + "   WHERE r.active = 1 AND r.id_reservation=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    reservation.setId(rs.getLong("id_reservation"));
                    reservation.setAdults(rs.getLong("adults"));
                    reservation.setCheckIn(rs.getDate("check_in"));
                    reservation.setCheckOut(rs.getDate("check_out"));
                    reservation.setDate(rs.getTimestamp("data_date"));
                    reservation.setChildren(rs.getLong("children"));
                    reservation.setNight(rs.getLong("night"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("name"));
                    hotel.setHotelAddress(rs.getString("address"));
                    hotel.setHotelPhone(rs.getString("phone"));
                    hotel.setHotelEmail(rs.getString("email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("surname"));
                    guest.setPhone(rs.getString("guest_phone"));
                    guest.setEmail(rs.getString("guest_email"));
                    City city=new City();
                    city.setId(rs.getLong("id_city"));
                    city.setName(rs.getString("name"));
                    Country country=new Country();
                    country.setId(rs.getLong("id_country"));
                    country.setName(rs.getString("name"));
                    city.setCountry(country);
                    guest.setCity(city);
                    reservation.setGuest(guest);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    Price price = new Price();
                    price.setId(rs.getLong("id_price"));
                    price.setBasePrice(rs.getDouble("base_price"));
                    price.setExtraBedPrice(rs.getDouble("extra_bed_price"));
                    roomType.setPrice(price);
                    reservation.setRoomType(roomType);
                    BookingStatus bookingStatus = new BookingStatus();
                    bookingStatus.setId(rs.getLong("id_booking_status"));
                    bookingStatus.setBookingStatus(rs.getString("booking_status"));
                    reservation.setBookingStatus(bookingStatus);
                    PaymentStatus paymentStatus = new PaymentStatus();
                    paymentStatus.setId(rs.getLong("id_payment_status"));
                    paymentStatus.setPaymentStatus(rs.getString("payment_status"));
                    reservation.setPaymentStatus(paymentStatus);
                } else {
                    reservation = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return reservation;
    }

    @Override
    public List<Room_Reservation> getReservationRoomById(Long reservationId) throws Exception {
        List<Room_Reservation> room_reservationList = new ArrayList<Room_Reservation>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT rr.id_room_reservation,r.id_reservation,r.night,\n"
                + "         rm.id_room,rm.number,\n"
                + "         f.id_floor,f.name,\n"
                + "         rt.id_room_type,rt.room_type\n"
                + "         FROM room_reservation rr\n"
                + "         INNER JOIN reservation r\n"
                + "            ON rr.reservation_id = r.id_reservation\n"
                + "         INNER JOIN room rm\n"
                + "            ON rr.room_id = rm.id_room\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN floor f\n"
                + "            ON rm.floor_id = f.id_floor\n"
                + "   WHERE rr.active = 1\n"
                + "AND r.id_reservation=?";

        /*String sql = "SELECT ROWNUM r ,RR.ID,R.ID RESERVATION_ID,\n"
                + "         G.ID GUEST_ID,\n"
                + "         G.GUEST_NAME GUEST_NAME,\n"
                + "         G.GUEST_SURNAME GUEST_SURNAME,\n"
                + "         D.ID DICTIONARY_ID,\n"
                + "         RM.ID ROOM_ID,\n"
                + "         RM.ROOM_NUMBER,\n"
                + "         RT.ID ROOM_TYPE_ID,\n"
                + "         RT.ROOM_TYPE,\n"
                + "         RS.ID ROOM_STATUS_ID,\n"
                + "         RS.ROOM_STATUS,\n"
                + "         F.ID FLOOR_ID,\n"
                + "         F.NAME FLOOR_NAME\n"
                + "         FROM ROOM_RESERVATION RR\n"
                + "         INNER JOIN RESERVATION R\n"
                + "            ON RR.RESERVATION_ID = R.ID\n"
                + "         INNER JOIN GUEST G\n"
                + "            ON R.GUEST_ID = G.ID\n"
                + "         INNER JOIN ROOM_TYPE RT\n"
                + "            ON R.ROOM_TYPE_ID = RT.ID\n"
                + "         INNER JOIN DICTIONARY D\n"
                + "            ON RR.DICTIONARY_ID = D.ID\n"
                + "         INNER JOIN ROOM RM\n"
                + "            ON D.ROOM_ID = RM.ID\n"
                + "         INNER JOIN FLOOR F\n"
                + "            ON RM.FLOOR_ID = F.ID\n"
                + "         INNER JOIN ROOM_STATUS RS\n"
                + "            ON RM.ROOM_STATUS_ID = RS.ID\n"
                + "   WHERE RR.ACTIVE = 1\n"
                + "AND R.ID=?";*/
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Room_Reservation room_reservation = new Room_Reservation();
                    room_reservation.setId(rs.getLong("id_room_reservation"));
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    reservation.setNight(rs.getLong("night"));
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    Room room = new Room();
                    room.setId(rs.getLong("id_room"));
                    room.setRoomNumber(rs.getLong("number"));
                    Floor floor = new Floor();
                    floor.setId(rs.getLong("id_floor"));
                    floor.setName(rs.getString("name"));
                    room.setFloor(floor);
                    room_reservation.setRoom(room);
                    room_reservation.setReservation(reservation);
                    room_reservationList.add(room_reservation);
                       /*  Guest guest = new Guest();
                    guest.setId(rs.getLong("GUEST_ID"));
                    guest.setGuestName(rs.getString("GUEST_NAME"));
                    guest.setGuestSurname(rs.getString("GUEST_SURNAME"));
                    reservation.setGuest(guest);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("ROOM_TYPE_ID"));
                    roomType.setRoomType(rs.getString("ROOM_TYPE"));
                    reservation.setRoomType(roomType);
                    Dictionary dictionary = new Dictionary();
                    dictionary.setId(rs.getLong("DICTIONARY_ID"));
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("ROOM_STATUS_ID"));
                    roomStatus.setRoomStatus(rs.getString("ROOM_STATUS"));
                    room.setRoomStatus(roomStatus);
                    room_reservation.setDictionary(dictionary);
                    dictionary.setRoom(room);*/

                } else {
                    room_reservationList = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return room_reservationList;
    }

    @Override
    public boolean updateReservation(Reservation reservation) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  reservation SET adults=?,check_in=?,check_out=?,children=?,hotel_id=?,guest_id=?,room_type_id=? WHERE id_reservation=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservation.getAdults());
                ps.setDate(2, new java.sql.Date(reservation.getCheckIn().getTime()));
                ps.setDate(3, new java.sql.Date(reservation.getCheckOut().getTime()));
                ps.setLong(4, reservation.getChildren());
                ps.setLong(5, 1);
                ps.setLong(6, reservation.getGuest().getId());
                ps.setLong(7, reservation.getRoomType().getId());
                ps.setLong(8, reservation.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean updateReservation1(Reservation reservation) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  reservation SET booking_status_id=? WHERE id_reservation=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservation.getBookingStatus().getId());
                ps.setLong(2, reservation.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean updateReservation2(Reservation reservation) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  reservation SET payment_status_id=? WHERE id_reservation=? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservation.getPaymentStatus().getId());
                ps.setLong(2, reservation.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteReservation(Long reservationId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  reservation SET active = 0 " + " WHERE id_reservation = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Reservation> searchReservationData(String keyword) throws Exception {
        List<Reservation> reservationList = new ArrayList<Reservation>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT r.id_reservation,r.adults,r.children,r.check_in,\n"
                + "         r.check_out,r.data_date,r.night,\n"
                + "         h.id_hotel,h.name,h.address,h.phone,h.email,\n"
                + "         g.id_guest,g.name guest_name,g.surname,g.phone guest_phone,g.email guest_email,\n"
                + "         rt.id_room_type,rt.room_type,\n"
                + "         bs.id_booking_status,bs.booking_status,\n"
                + "         p.id_price,p.base_price,p.extra_bed_price,\n"
                + "         D.ID DICTIONARY_ID,\n"
                + "         RM.ID ROOM_ID,\n"
                + "         RM.ROOM_NUMBER,\n"
                + "         RS.ID ROOM_STATUS_ID,\n"
                + "         RS.ROOM_STATUS\n"
                + "         FROM reservation r\n"
                + "         INNER JOIN hotel h\n"
                + "            ON r.hotel_id = h.id_hotel\n"
                + "         INNER JOIN guest g\n"
                + "            ON r.guest_id = g.id_guest\n"
                + "         INNER JOIN booking_status bs\n"
                + "            ON r.booking_status_id = bs.id_booking_status\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.id_room_type\n"
                + "         INNER JOIN price p\n"
                + "            ON rt.price_id = p.id_price\n"
                + "         INNER JOIN DICTIONARY D\n"
                + "            ON RT.DICTIONARY_ID = D.ID\n"
                + "         INNER JOIN ROOM RM\n"
                + "            ON D.ROOM_ID = RM.ID\n"
                + "         INNER JOIN ROOM_STATUS RS\n"
                + "            ON RM.ROOM_STATUS_ID = RS.ID\n"
                + "   WHERE r.active = 1 \n"
                + "AND ( LOWER(adults) LIKE LOWER (?) OR LOWER(check_in) LIKE LOWER (?)\n"
                + " OR LOWER(check_out) LIKE LOWER (?) OR LOWER(children) LIKE LOWER (?) \n"
                + "OR LOWER(name) LIKE LOWER (?) OR LOWER(address) LIKE LOWER (?)\n"
                + " OR LOWER(phone) LIKE LOWER (?) OR LOWER(guest_name) LIKE LOWER (?)\n"
                + " OR LOWER(surname) LIKE LOWER (?) OR LOWER(ROOM_NUMBER) LIKE LOWER (?)\n"
                + "OR LOWER(email) LIKE LOWER (?)OR LOWER(room_type) LIKE LOWER (?)\n"
                + " OR LOWER(ROOM_STATUS) LIKE LOWER (?)\n"
                + "OR LOWER(booking_status) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                ps.setString(6, "%" + keyword + "%");
                ps.setString(7, "%" + keyword + "%");
                ps.setString(8, "%" + keyword + "%");
                ps.setString(9, "%" + keyword + "%");
                ps.setString(10, "%" + keyword + "%");
                ps.setString(11, "%" + keyword + "%");
                ps.setString(12, "%" + keyword + "%");
                ps.setString(13, "%" + keyword + "%");
                ps.setString(14, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setR(rs.getLong("r"));
                    reservation.setId(rs.getLong("ID"));
                    reservation.setAdults(rs.getLong("ADULTS"));
                    reservation.setCheckIn(rs.getDate("CHECK_IN"));
                    reservation.setCheckOut(rs.getDate("CHECK_OUT"));
                    reservation.setChildren(rs.getLong("CHILDREN"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("HOTEL_ID"));
                    hotel.setHotelName(rs.getString("HOTEL_NAME"));
                    hotel.setHotelAddress(rs.getString("HOTEL_ADDRESS"));
                    hotel.setHotelPhone(rs.getString("HOTEL_PHONE"));
                    hotel.setHotelEmail(rs.getString("HOTEL_EMAIL"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("GUEST_ID"));
                    guest.setGuestName(rs.getString("GUEST_NAME"));
                    guest.setGuestSurname(rs.getString("GUEST_SURNAME"));
                    reservation.setGuest(guest);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("ROOM_TYPE_ID"));
                    roomType.setRoomType(rs.getString("ROOM_TYPE"));
                    Dictionary dictionary = new Dictionary();
                    dictionary.setId(rs.getLong("DICTIONARY_ID"));
                    Room room = new Room();
                    room.setId(rs.getLong("ROOM_ID"));
                    room.setRoomNumber(rs.getLong("ROOM_NUMBER"));
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.setId(rs.getLong("ROOM_STATUS_ID"));
                    roomStatus.setRoomStatus(rs.getString("ROOM_STATUS"));
                    room.setRoomStatus(roomStatus);
                    dictionary.setRoom(room);
                    //   roomType.setDictionary(dictionary);
                    reservation.setRoomType(roomType);
                    BookingStatus bookingStatus = new BookingStatus();
                    bookingStatus.setId(rs.getLong("BOOKING_STATUS_ID"));
                    bookingStatus.setBookingStatus(rs.getString("BOOKING_STATUS"));
                    reservation.setBookingStatus(bookingStatus);
                    reservationList.add(reservation);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return reservationList;
    }

    @Override
    public List<Reservation> advancedSearchReservationData(AdvancedSearch advancedSearch) throws Exception {
        List<Reservation> reservationList = new ArrayList<Reservation>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT r.id_reservation,r.adults,r.children,r.check_in,\n"
                + "         r.check_out,r.data_date,r.night,\n"
                + "         h.id_hotel,h.name,h.address,h.phone,h.email,\n"
                + "         g.id_guest,g.name guest_name,g.surname,\n"
                + "         rt.id_room_type,rt.room_type,\n"
                + "         bs.id_booking_status,bs.booking_status\n"
                + "         FROM reservation r\n"
                + "         INNER JOIN hotel h\n"
                + "            ON r.hotel_id = h.id_hotel\n"
                + "         INNER JOIN guest g\n"
                + "            ON r.guest_id = g.id_guest\n"
                + "         INNER JOIN booking_status bs\n"
                + "            ON r.booking_status_id = bs.id_booking_status\n"
                + "         INNER JOIN room_type rt\n"
                + "            ON r.room_type_id = rt.id_room_type\n"
                + "   WHERE r.active = 1\n";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (advancedSearch.getRoomTypeId() != 0) {
                    sql += " AND rt.id_room_type = " + advancedSearch.getRoomTypeId();
                }
                if (advancedSearch.getPaymentStatusId() != 0) {
                    // sql += " AND CY.ID = " + advancedSearch.getPaymentStatusId();
                }
                if (advancedSearch.getBookingStatusId() != 0) {
                    sql += " AND bs.id_booking_status = " + advancedSearch.getBookingStatusId();
                }
                if (advancedSearch.getGuestId() != 0) {
                    sql += " AND g.id_guest = " + advancedSearch.getGuestId();
                }
                if (advancedSearch.getBeginDate() != null && !advancedSearch.getBeginDate().isEmpty()) {
                    sql += " AND r.check_in >= TO_DATE('" + new java.sql.Date(df.parse(advancedSearch.getBeginDate()).getTime()) + "','YYYY-MM-DD')";
                }
                if (advancedSearch.getEndDate() != null && !advancedSearch.getEndDate().isEmpty()) {
                    sql += " AND r.check_out <= TO_DATE('" + new java.sql.Date(df.parse(advancedSearch.getEndDate()).getTime()) + "','YYYY-MM-DD')";
                }
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    reservation.setAdults(rs.getLong("adults"));
                    reservation.setCheckIn(rs.getDate("check_in"));
                    reservation.setCheckOut(rs.getDate("check_out"));
                    reservation.setDate(rs.getDate("data_date"));
                    reservation.setChildren(rs.getLong("children"));
                    reservation.setNight(rs.getLong("night"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("name"));
                    hotel.setHotelAddress(rs.getString("address"));
                    hotel.setHotelPhone(rs.getString("phone"));
                    hotel.setHotelEmail(rs.getString("email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("surname"));
                    reservation.setGuest(guest);
                    RoomType roomType = new RoomType();
                    roomType.setId(rs.getLong("id_room_type"));
                    roomType.setRoomType(rs.getString("room_type"));
                    reservation.setRoomType(roomType);
                    BookingStatus bookingStatus = new BookingStatus();
                    bookingStatus.setId(rs.getLong("id_booking_status"));
                    bookingStatus.setBookingStatus(rs.getString("booking_status"));
                    reservation.setBookingStatus(bookingStatus);
                    reservationList.add(reservation);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return reservationList;
    }

    @Override
    public Long reservationTodayAcount() throws Exception {
        Long reservationTodayAcount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  COUNT(r.id_room_reservation) AS reservationTodayCnt"
                + "    FROM room_reservation r \n"
                + "         INNER JOIN reservation rm\n"
                + "            ON r.reservation_id = rm.id_reservation\n"
                + "         INNER JOIN booking_status bs\n"
                + "            ON rm.booking_status_id = bs.id_booking_status\n"
                + "   WHERE r.active = 1 AND rm.booking_status_id=1 AND (DATEDIFF(CURDATE(),r.data_date)=0 OR DATEDIFF(CURDATE(),r.data_date)=1 )";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    reservationTodayAcount = rs.getLong("reservationTodayCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return reservationTodayAcount;
    }

    @Override
    public Long reservationAcount() throws Exception {
        Long reservationAcount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  COUNT(r.id_reservation) AS reservationCnt"
                + "    FROM reservation r\n"
                + "         INNER JOIN booking_status bs\n"
                + "            ON r.booking_status_id = bs.id_booking_status\n"
                + "   WHERE r.active = 1 AND r.booking_status_id=1\n";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    reservationAcount = rs.getLong("reservationCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return reservationAcount;
    }

    @Override
    public Long reservationGuestAcount() throws Exception {
        Long reservationGuestAcount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  COUNT(r.id_reservation) AS reservationCnt"
                + "    FROM reservation r\n"
                + "         INNER JOIN booking_status bs\n"
                + "            ON r.booking_status_id = bs.id_booking_status\n"
                + "         INNER JOIN payment_status ps\n"
                + "            ON r.payment_status_id = ps.id_payment_status\n"
                + "   WHERE r.active = 1 AND r.booking_status_id=2 AND r.payment_status_id =1\n";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    reservationGuestAcount = rs.getLong("reservationCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return reservationGuestAcount;
    }
}
