package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.dao.ValidationTokenDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.VerifyEmailRequest;
import me.jinxinyu.caltracker.service.response.VerifyEmailResponse;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.sql.Timestamp;

public class VerifyEmailServiceImpl implements VerifyEmailService {

    private static final String ERROR_MESSAGE = "Email Verification Error Type: %s \n" +
            "Email Verification Error Message: %s";

    /** Email Content Format */
    private static final String SUBJECT = "CalTracker Email Verification Code";
    private static final String TEXT = "Your CalTracker User Email 6 Digit Verification Code is: %s";

    @Override
    public VerifyEmailResponse verifyEmail(VerifyEmailRequest request) {
        try {
            /** 1. Verify if email (alias) has already been taken */
            if (UserDAO.checkUser(request.getUsername())) {
                return new VerifyEmailResponse(String.format(ERROR_MESSAGE, "401", "Email has already registered"));
            }

            /** 2. Generate a random token and store in DB */
            ValidationTokenDAO valiDAO = new ValidationTokenDAO();
            long currTime = new Timestamp(System.currentTimeMillis()).getTime();

            String randomToken = valiDAO.getRandomToken();
            valiDAO.addToken(randomToken, currTime, request.getUsername());

            /** 3. Send an email */
            try {
                ServiceImpl.sendEmail(request.getUsername(), SUBJECT, String.format(TEXT, randomToken));
            } catch (MessagingException | FileNotFoundException e) {
                return new VerifyEmailResponse(String.format(ERROR_MESSAGE, "501", e.getMessage()));
            }
        } catch (DBRemoteException e) {
            return new VerifyEmailResponse(String.format(ERROR_MESSAGE,
                    e.getErrorType(), e.getMessage()));
        }

        return new VerifyEmailResponse();
    }
}
