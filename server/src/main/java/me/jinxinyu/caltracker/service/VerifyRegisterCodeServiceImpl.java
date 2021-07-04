package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.ValidationTokenDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.VerifyRegisterCodeRequest;
import me.jinxinyu.caltracker.service.response.VerifyRegisterCodeResponse;

import java.sql.Timestamp;
import java.util.Map;


public class VerifyRegisterCodeServiceImpl implements VerifyRegisterCodeService {
    private static final long DIFF = 150000L;


    private static final String ERROR_MESSAGE = "Registration Code Error Type: %s \n" +
            "Registration Code Error Message: %s";

    @Override
    public VerifyRegisterCodeResponse verifyRegisterCode(VerifyRegisterCodeRequest request) {
        ValidationTokenDAO valiDAO = new ValidationTokenDAO();
        String token = request.getToken();
        String userAlias = request.getUserAlias();
        try {
            Map.Entry<String, String> resp = valiDAO.getToken(token);
            if (resp == null || resp.getKey() == null || resp.getValue() == null
                    || !resp.getKey().equals(userAlias)) {
                return new VerifyRegisterCodeResponse(String.format(ERROR_MESSAGE, "401", "Invalid Token"));
            }

            long tstamp = Long.parseLong(resp.getValue());
            long currTstamp = new Timestamp(System.currentTimeMillis()).getTime();

            valiDAO.deleteToken(token);

            if (currTstamp - tstamp > DIFF) {
                return new VerifyRegisterCodeResponse(String.format(ERROR_MESSAGE,
                        "401", "Your Verification Code is Expired"));
            }

        } catch (DBRemoteException e) {
            return new VerifyRegisterCodeResponse(String.format(ERROR_MESSAGE,
                    e.getErrorType(), e.getMessage()));
        }

        return new VerifyRegisterCodeResponse();
    }
}
