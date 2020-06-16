package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.PaymentDao;
import az.elvin.hotel.model.*;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public List<Payment> getPaymentList() throws Exception {
        List<Payment> paymentList = new ArrayList<Payment>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,\n"
                + "         r.id_reservation,\n"
                + "         pt.id_pay_type,pt.pay_type\n"
                + "    FROM payment p\n"
                + "         INNER JOIN pay_type pt\n"
                + "            ON p.pay_type_id = pt.id_pay_type\n"
                + "         INNER JOIN reservation r\n"
                + "            ON p.reservation_id = r.id_reservation\n"
                + "   WHERE p.active = 1\n"
                + "ORDER BY p.id_payment";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payment.setPayType(payType);
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    payment.setReservation(reservation);
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public List<Payment> getPaymentListByGuestId(Long guestId) throws Exception {
        List<Payment> paymentList = new ArrayList<Payment>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,\n"
                + "         r.id_reservation,\n"
                + "         pt.id_pay_type,pt.pay_type,\n" +
                "       p.data_date,\n" +
                "       h.id_hotel, h.name hotel_name,h.address hotel_address,h.phone hotel_phone,h.email hotel_email,\n" +
                "       g.id_guest,g.name guest_name,g.surname guest_surname,g.phone,g.email\n"
                + "    FROM payment p\n"
                + "         INNER JOIN pay_type pt\n"
                + "            ON p.pay_type_id = pt.id_pay_type\n"
                + "         INNER JOIN reservation r\n"
                + "            ON p.reservation_id = r.id_reservation\n" +
                "       INNER JOIN hotel h\n" +
                "          ON r.hotel_id = h.id_hotel\n" +
                "       INNER JOIN guest g\n" +
                "          ON r.guest_id = g.id_guest\n" +
                "   WHERE p.active = 1 AND g.id_guest = ?\n";


        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, guestId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setDate(rs.getDate("data_date"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payment.setPayType(payType);
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("hotel_name"));
                    hotel.setHotelAddress(rs.getString("hotel_address"));
                    hotel.setHotelPhone(rs.getString("hotel_phone"));
                    hotel.setHotelEmail(rs.getString("hotel_email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("guest_surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    reservation.setGuest(guest);
                    payment.setReservation(reservation);
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public List<Payment> getPaymentListByReservationId(Long reservationId) throws Exception {
        List<Payment> paymentList = new ArrayList<Payment>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,\n"
                + "         r.id_reservation,p.all_night_cost,\n"
                + "         pt.id_pay_type,pt.pay_type,\n" +
                "       p.data_date,\n" +
                "       h.id_hotel, h.name hotel_name,h.address hotel_address,h.phone hotel_phone,h.email hotel_email,\n" +
                "       g.id_guest,g.name guest_name,g.surname guest_surname,g.phone,g.email\n" +
                "    FROM payment p\n" +
                "         INNER JOIN pay_type pt\n" +
                "            ON p.pay_type_id = pt.id_pay_type\n" +
                "         INNER JOIN reservation r\n" +
                "            ON p.reservation_id = r.id_reservation\n" +
                "       INNER JOIN hotel h\n" +
                "          ON r.hotel_id = h.id_hotel\n" +
                "       INNER JOIN guest g\n" +
                "          ON r.guest_id = g.id_guest\n" +
                "   WHERE p.active = 1 AND r.id_reservation = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setDate(rs.getDate("data_date"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    payment.setAllNightCost(rs.getDouble("all_night_cost"));
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payment.setPayType(payType);
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("hotel_name"));
                    hotel.setHotelAddress(rs.getString("hotel_address"));
                    hotel.setHotelPhone(rs.getString("hotel_phone"));
                    hotel.setHotelEmail(rs.getString("hotel_email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("guest_surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    reservation.setGuest(guest);
                    payment.setReservation(reservation);
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  payment (reservation_id,all_night_cost,pending,sum,services_cost,tax_cost) " + " VALUES (?,?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, payment.getReservation().getId());
                ps.setDouble(2, payment.getAllNightCost());
                ps.setDouble(3, payment.getPending());
                ps.setDouble(4, payment.getSum());
                ps.setDouble(5, 0);
                ps.setDouble(6,payment.getTaxCost());
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
        /*boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  payment (amount,reservation_id,pay_type_id,invoice_number,added_date) " + " VALUES (?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setDouble(1, payment.getPaymentAmount());
                ps.setLong(2, payment.getReservation().getId());
                ps.setLong(3, payment.getPayType().getId());
                ps.setLong(4, payment.getInvoiceNumber());
                ps.setDate(5, new java.sql.Date(payment.getAddedDate().getTime()));
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
        return result;*/
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,p.pending,\n"
                + "         r.id_reservation,\n"
                + "         pt.id_pay_type,pt.pay_type,\n" +
                "       p.data_date,\n" +
                "       h.id_hotel, h.name hotel_name,h.address hotel_address,h.phone hotel_phone,h.email hotel_email,\n" +
                "       g.id_guest,g.name guest_name,g.surname guest_surname,g.phone,g.email\n"
                + "    FROM payment p\n"
                + "         INNER JOIN pay_type pt\n"
                + "            ON p.pay_type_id = pt.id_pay_type\n"
                + "         INNER JOIN reservation r\n"
                + "            ON p.reservation_id = r.id_reservation\n" +
                "       INNER JOIN hotel h\n" +
                "          ON r.hotel_id = h.id_hotel\n" +
                "       INNER JOIN guest g\n" +
                "          ON r.guest_id = g.id_guest\n" +
                "   WHERE p.active = 1 AND p.id_payment = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setDate(rs.getTimestamp("data_date"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    payment.setPending(rs.getDouble("pending"));
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payment.setPayType(payType);
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("hotel_name"));
                    hotel.setHotelAddress(rs.getString("hotel_address"));
                    hotel.setHotelPhone(rs.getString("hotel_phone"));
                    hotel.setHotelEmail(rs.getString("hotel_email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("guest_surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    reservation.setGuest(guest);
                    payment.setReservation(reservation);

                } else {
                    payment = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payment;
    }
    @Override
    public Payment getPaymentByReservationId(Long reservationId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,p.data_date,\n"
                + "         r.id_reservation,\n"
                + "         pt.id_pay_type,pt.pay_type,\n" +
                "       h.id_hotel, h.name hotel_name,h.address hotel_address,h.phone hotel_phone,h.email hotel_email,\n" +
                "       g.id_guest,g.name guest_name,g.surname guest_surname,g.phone,g.email\n"
                + "    FROM payment p\n"
                + "         INNER JOIN pay_type pt\n"
                + "            ON p.pay_type_id = pt.id_pay_type\n"
                + "         INNER JOIN reservation r\n"
                + "            ON p.reservation_id = r.id_reservation\n" +
                "       INNER JOIN hotel h\n" +
                "          ON r.hotel_id = h.id_hotel\n" +
                "       INNER JOIN guest g\n" +
                "          ON r.guest_id = g.id_guest\n" +
                "   WHERE p.active = 1 AND p.reservation_id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setDate(rs.getDate("data_date"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payment.setPayType(payType);
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("hotel_name"));
                    hotel.setHotelAddress(rs.getString("hotel_address"));
                    hotel.setHotelPhone(rs.getString("hotel_phone"));
                    hotel.setHotelEmail(rs.getString("hotel_email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("guest_surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    reservation.setGuest(guest);
                    payment.setReservation(reservation);

                } else {
                    payment = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payment;
    }
    @Override
    public Payment getPaymentByReservationId1(Long reservationId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,p.data_date,p.pending,p.services_cost,p.sum,\n"
                + "         r.id_reservation,\n"
                + "       h.id_hotel, h.name hotel_name,h.address hotel_address,h.phone hotel_phone,h.email hotel_email,\n" +
                "       g.id_guest,g.name guest_name,g.surname guest_surname,g.phone,g.email\n"
                + "    FROM payment p\n"
                + "         INNER JOIN reservation r\n"
                + "            ON p.reservation_id = r.id_reservation\n" +
                "       INNER JOIN hotel h\n" +
                "          ON r.hotel_id = h.id_hotel\n" +
                "       INNER JOIN guest g\n" +
                "          ON r.guest_id = g.id_guest\n" +
                "   WHERE p.active = 1 AND p.reservation_id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setDate(rs.getDate("data_date"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    payment.setPending(rs.getDouble("pending"));
                    payment.setServicesCost(rs.getDouble("services_cost"));
                    payment.setSum(rs.getDouble("sum"));
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getLong("id_hotel"));
                    hotel.setHotelName(rs.getString("hotel_name"));
                    hotel.setHotelAddress(rs.getString("hotel_address"));
                    hotel.setHotelPhone(rs.getString("hotel_phone"));
                    hotel.setHotelEmail(rs.getString("hotel_email"));
                    reservation.setHotel(hotel);
                    Guest guest = new Guest();
                    guest.setId(rs.getLong("id_guest"));
                    guest.setGuestName(rs.getString("guest_name"));
                    guest.setGuestSurname(rs.getString("guest_surname"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    reservation.setGuest(guest);
                    payment.setReservation(reservation);

                } else {
                    payment = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payment;
    }
    @Override
    public Payment getPaymentByReservationId2(Long reservationId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,p.pending,p.all_night_cost,p.services_cost,p.sum,p.tax_cost,\n"
                + "         r.id_reservation\n"
                + "    FROM payment p\n"
                + "         INNER JOIN reservation r\n"
                + "            ON p.reservation_id = r.id_reservation\n" +
                "   WHERE p.active = 1 AND p.reservation_id=?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, reservationId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    payment.setPending(rs.getDouble("pending"));
                    payment.setAllNightCost(rs.getDouble("all_night_cost"));
                    payment.setServicesCost(rs.getDouble("services_cost"));
                    payment.setTaxCost(rs.getDouble("tax_cost"));
                    payment.setSum(rs.getDouble("sum"));
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    payment.setReservation(reservation);

                } else {
                    payment = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payment;
    }
    @Override
    public boolean updatePayment(Payment payment) throws Exception {

        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  payment SET amount=?,reservation_id=?,pay_type_id=?,added_date=?,pending=?   WHERE id_payment=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setDouble(1, payment.getPaymentAmount());
                ps.setLong(2, payment.getReservation().getId());
                ps.setLong(3, payment.getPayType().getId());
                ps.setDate(4, new java.sql.Date(payment.getAddedDate().getTime()));
                ps.setDouble(5, payment.getPending());
                ps.setLong(6, payment.getId());
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
    public boolean updatePayment1(Payment payment) throws Exception {

        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  payment SET sum=?,reservation_id=?,pending=?,services_cost=?,tax_cost=?   WHERE id_payment=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setDouble(1, payment.getSum());
                ps.setLong(2, payment.getReservation().getId());
                ps.setDouble(3, payment.getPending());
                ps.setDouble(4,payment.getServicesCost());
                ps.setDouble(5,payment.getTaxCost());
                ps.setLong(6, payment.getId());
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
    public boolean deletePayment(Long paymentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  payment SET active = 0 " + " WHERE id_payment = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentId);
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
    public List<Payment> searchPaymentData(String keyword) throws Exception {
        List<Payment> paymentList = new ArrayList<Payment>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.id_payment,p.amount,p.added_date,\n"
                + "         r.id_reservation,\n"
                + "         pt.id_pay_type,pt.pay_type\n"
                + "    FROM payment p\n"

                + "         INNER JOIN pay_type pt\n"
                + "            ON p.pay_type_id = pt.id_pay_type\n"
                + "         INNER JOIN reservation r\n"
                + "            ON p.reservation_id = r.id_reservation\n"
                + "   WHERE p.active = 1\n"
                + "AND ( LOWER(pay_type) LIKE LOWER (?) OR LOWER(amount) LIKE LOWER (?)\n "

                + "OR LOWER(id_reservation) LIKE LOWER (?) OR LOWER(added_date) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("id_payment"));
                    payment.setPaymentAmount(rs.getDouble("amount"));
                    payment.setAddedDate(rs.getDate("added_date"));
                    PayType payType = new PayType();
                    payType.setId(rs.getLong("id_pay_type"));
                    payType.setPayType(rs.getString("pay_type"));
                    payment.setPayType(payType);
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id_reservation"));
                    payment.setReservation(reservation);

                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public Long ToDayRevenueCount() throws Exception {
        Long ToDayRevenueCount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  SUM(amount) AS amountTodayCnt"
                + "    FROM payment p \n"
                + "   WHERE p.active = 1  AND (DATEDIFF(CURDATE(),p.data_date)=0 OR DATEDIFF(CURDATE(),p.data_date)=1 )";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ToDayRevenueCount = rs.getLong("amountTodayCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return ToDayRevenueCount;
    }

    @Override
    public Long ToWeekIncomeCount() throws Exception {
        Long ToWeekIncomeCount = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  SUM(amount) AS amountTodayCnt"
                + "    FROM payment p \n"
                + "   WHERE p.active = 1  AND (p.data_date > NOW()-INTERVAL 1 WEEK)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ToWeekIncomeCount = rs.getLong("amountTodayCnt");
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return ToWeekIncomeCount;
    }
}
