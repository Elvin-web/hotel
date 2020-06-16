package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.LoginDao;
import az.elvin.hotel.model.LoginUser;
import az.elvin.hotel.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public LoginUser login(String username, String password) throws Exception {
        return  loginDao.login(username, password);
    }

    @Override
    public LoginUser checkEmail(String email) throws Exception {
        return loginDao.checkEmail(email);
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        return loginDao.changePassword(password,token);
    }

    @Override
    public boolean updateToken(String token) throws Exception {
        return loginDao.updateToken(token);
    }

    @Override
    public boolean updateTokenById(String token, long id) throws Exception {
        return loginDao.updateTokenById(token,id);
    }
}
