package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.dao.ValidationTokenDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.ResetPasswordRequest;
import me.jinxinyu.caltracker.service.response.ForgetPasswordResponse;
import me.jinxinyu.caltracker.service.response.ResetPasswordResponse;

import java.sql.Timestamp;
import java.util.Map;

import static me.jinxinyu.caltracker.service.ServiceImpl.generateHash;

public class ResetPasswordServiceImpl implements ResetPasswordService {
    /**
     * valid token time
     */
    private static final long DIFF = 300000L;

    private static final String ERROR_MESSAGE = "Reset Password Error Type: %s \n" +
            "Reset Password Error Message: %s";


    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
        ValidationTokenDAO valiDAO = new ValidationTokenDAO();
        String token = request.getToken();
        String userAlias = request.getUserAlias();
        try {
            /** 1. check if validation code is valid (not expire, match user) */
            Map.Entry<String, String> resp = valiDAO.getToken(token);
            if (resp == null || resp.getKey() == null || resp.getValue() == null
                    || !resp.getKey().equals(userAlias)) {
                return new ResetPasswordResponse(String.format(ERROR_MESSAGE, "401", "Invalid Token"));
            }

            long tstamp = Long.parseLong(resp.getValue());
            long currTstamp = new Timestamp(System.currentTimeMillis()).getTime();

            valiDAO.deleteToken(token);

            if (currTstamp - tstamp > DIFF) {
                return new ResetPasswordResponse(String.format(ERROR_MESSAGE,
                        "401", "Your Token is Expired"));
            }

            /** 2. Update User Info in DB with new password */
            UserDAO.resetPassword(generateHash(request.getPassword()));
            return new ResetPasswordResponse();
        } catch (DBRemoteException e) {
            return new ResetPasswordResponse(String.format(ERROR_MESSAGE,
                        e.getErrorType(), e.getMessage()));
        }
    }
}
