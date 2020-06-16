package az.elvin.hotel.model;

import java.util.Date;

public class LoginUser {
    private Long id_hotel_login;
    private String username;
    private String password;
   // private String name;
    //private String surname;
    private Role role;
    private Staff staff;
    private Date loginDate;
    private String token;
    private Integer active;
    private Date data_date;

    public Long getId_hotel_login() {
        return id_hotel_login;
    }

    public void setId_hotel_login(Long id_hotel_login) {
        this.id_hotel_login = id_hotel_login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getData_date() {
        return data_date;
    }

    public void setData_date(Date data_date) {
        this.data_date = data_date;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id_hotel_login=" + id_hotel_login +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", staff=" + staff +
                ", loginDate=" + loginDate +
                ", token='" + token + '\'' +
                ", active=" + active +
                ", data_date=" + data_date +
                '}';
    }
}
