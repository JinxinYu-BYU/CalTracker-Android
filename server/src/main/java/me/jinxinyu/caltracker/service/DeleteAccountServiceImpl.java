package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.DeleteAccountRequest;
import me.jinxinyu.caltracker.service.response.ClearCartResponse;
import me.jinxinyu.caltracker.service.response.DeleteAccountResponse;
import me.jinxinyu.caltracker.service.response.LogoutResponse;

import java.io.IOException;

public class DeleteAccountServiceImpl extends ServiceImpl implements DeleteAccountService {
    private static final String ERROR_MESSAGE = "Delete Account Error Type: %s \n" +
                                                    "Delete Account Error Message: %s";

    @Override
    public DeleteAccountResponse delete(DeleteAccountRequest request) {
        AuthsDAO authsDAO = new AuthsDAO();
        UserDAO userDAO = new UserDAO();

        String newToken = validateToken(request.getAuthToken(), request.getUser().getAlias());
        if(newToken != null){
            try {
                authsDAO.deleteToken(request.getAuthToken());
                // delete user from database
                return userDAO.delete(request.getUser());
            } catch(DBRemoteException e) {
                return new DeleteAccountResponse(false,String.format(ERROR_MESSAGE,
                        e.getErrorType(), e.getMessage()));
            }
        }else {
            return new DeleteAccountResponse(false, "Invalid Token!");
        }

    }
}
