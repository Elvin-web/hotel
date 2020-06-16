package az.elvin.hotel.dao;

import az.elvin.hotel.model.LoginUser;

public interface LoginDao {

    LoginUser login(String username, String password) throws Exception;

    LoginUser checkEmail(String email) throws Exception;
    boolean changePassword(String password, String token) throws Exception;

    boolean updateToken(String token) throws Exception;

    boolean updateTokenById(String token, long id) throws Exception;
}

