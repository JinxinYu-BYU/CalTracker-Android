package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.dao.ValidationTokenDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.ForgetPasswordRequest;
import me.jinxinyu.caltracker.service.response.ForgetPasswordResponse;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Random;

import static javax.mail.Message.RecipientType.TO;

public class ForgetPasswordServiceImpl extends ServiceImpl implements ForgetPasswordService {
    private static final String ERROR_MESSAGE = "Forget Password Error Type: %s \n" +
            "Forget Password Error Message: %s";

    /** Email Content Format */
    private static final String SUBJECT = "CalTracker Password Reset Validation Code";
    private static final String TEXT = "Your CalTracker Account 6 Digit Password Reset Token is: %s";


    @Override
    public ForgetPasswordResponse forgetPassword(ForgetPasswordRequest request) {
        try {
            /** 1. Verify the user if exists in DB */
            if (!UserDAO.checkUser(request.getUsername())) {
                return new ForgetPasswordResponse(String.format(ERROR_MESSAGE,
                        "Invalid Request", "No Such User"));
            }

            /** 2.Generate a random token and store it in tha table */
            ValidationTokenDAO valiDAO = new ValidationTokenDAO();
            long currTime = new Timestamp(System.currentTimeMillis()).getTime();

            String randomToken = valiDAO.getRandomToken();
            valiDAO.addToken(randomToken, currTime, request.getUsername());

            /** 3.Set up Email Notification and send the validation code to the user */
            try {
                ServiceImpl.sendEmail(request.getUsername(), SUBJECT, String.format(TEXT, randomToken));
            } catch (MessagingException | FileNotFoundException e) {
                return new ForgetPasswordResponse(String.format(ERROR_MESSAGE, "501", e.getMessage()));
            }
        } catch (DBRemoteException e) {
            return new ForgetPasswordResponse(String.format(ERROR_MESSAGE,
                    e.getErrorType(), e.getMessage()));
        }

        return new ForgetPasswordResponse();
    }


}
