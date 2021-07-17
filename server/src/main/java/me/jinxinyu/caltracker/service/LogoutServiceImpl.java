package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.LogoutRequest;
import me.jinxinyu.caltracker.service.response.LoginResponse;
import me.jinxinyu.caltracker.service.response.LogoutResponse;

import java.io.IOException;

public class LogoutServiceImpl extends ServiceImpl implements LogoutService {
    private static final String ERROR_MESSAGE = "Logout Error Type: %s \nLogout Error Message: %s";

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        try {
            AuthsDAO authsDAO = new AuthsDAO();
            //TODO: verify if the user matches the token owner
            authsDAO.deleteToken(request.getAuthToken());
            return new LogoutResponse();
        } catch (DBRemoteException e) {
            return new LogoutResponse(String.format(ERROR_MESSAGE, e.getErrorType(), e.getMessage()));
        }
    }
}
