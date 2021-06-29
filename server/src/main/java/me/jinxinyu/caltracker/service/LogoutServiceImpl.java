package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.service.request.LogoutRequest;
import me.jinxinyu.caltracker.service.response.LogoutResponse;

import java.io.IOException;

public class LogoutServiceImpl extends ServiceImpl implements LogoutService {
    @Override
    public LogoutResponse logout(LogoutRequest request) {
        AuthsDAO authsDAO = new AuthsDAO();
        authsDAO.deleteToken(request.getAuthToken());
        return new LogoutResponse();
    }
}
