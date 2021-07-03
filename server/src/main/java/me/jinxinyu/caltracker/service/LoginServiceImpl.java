package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.LoginRequest;
import me.jinxinyu.caltracker.service.response.LoginResponse;

import java.sql.Timestamp;
import java.util.UUID;

public class LoginServiceImpl extends ServiceImpl implements LoginService {
    private static final String ERROR_MESSAGE = "Login Error Type: %s \nLogin Error Message: %s";

    @Override
    public LoginResponse login(LoginRequest request) {

        AuthsDAO authsDAO = new AuthsDAO();
        UserDAO userDAO = new UserDAO();

        request.setPassword(generateHash(request.getPassword()));

        try {
            LoginResponse response = userDAO.login(request);
            if (response.isSuccess()) {
                String token = UUID.randomUUID().toString();
                long currTime = new Timestamp(System.currentTimeMillis()).getTime();
                authsDAO.addToken(token, currTime, request.getUsername());


                response.setAuthToken(token);
            }

            return response;
        } catch (DBRemoteException e) {
            return new LoginResponse(String.format(ERROR_MESSAGE, e.getErrorType(), e.getMessage()));
        }
    }
}
