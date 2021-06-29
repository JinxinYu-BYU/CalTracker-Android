package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.service.request.DeleteAccountRequest;
import me.jinxinyu.caltracker.service.response.DeleteAccountResponse;
import me.jinxinyu.caltracker.service.response.LogoutResponse;

import java.io.IOException;

public class DeleteAccountServiceImpl extends ServiceImpl implements DeleteAccountService {
    @Override
    public DeleteAccountResponse delete(DeleteAccountRequest request) {
        AuthsDAO authsDAO = new AuthsDAO();
        UserDAO userDAO = new UserDAO();

        authsDAO.deleteToken(request.getAuthToken());
        // delete user from database
        return userDAO.delete(request.getUser());
    }
}
