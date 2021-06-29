package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.service.request.LoginRequest;
import me.jinxinyu.caltracker.service.response.LoginResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

public class LoginServiceImpl extends ServiceImpl implements LoginService {

    @Override
    public LoginResponse login(LoginRequest request) {

        AuthsDAO authsDAO = new AuthsDAO();
        UserDAO userDAO = new UserDAO();

        request.setPassword(generateHash(request.getPassword()));

        LoginResponse response = userDAO.login(request);

        if (response.isSuccess()) {
            String token = UUID.randomUUID().toString();
            long currTime = new Timestamp(System.currentTimeMillis()).getTime();
            authsDAO.addToken(token, currTime);
            response.setAuthToken(token);
        }

        return response;
    }
}
