package me.jinxinyu.caltracker.service;

import com.amazonaws.regions.Regions;
import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.dao.ValidationTokenDAO;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.ForgetPasswordRequest;
import me.jinxinyu.caltracker.service.response.ForgetPasswordResponse;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForgetPasswordServiceImpl extends ServiceImpl implements ForgetPasswordService {
    private static final Random RAND = new Random();
    private static final int MAX_NUMBER = 999999;

    private static final String ERROR_MESSAGE = "Forget Password Error Type: %s \n" +
            "Forget Password Error Message: %s";

    /** Email Content Format */
    private static final String SUBJECT = "CalTracker Password Reset Validation Code";
    private static final String TEXT = "Your CalTracker Account 6 Digit Password Reset Token is: %s";
    private static final String EMAIL_SENDER = "caltrackcustomerservice@gmail.com";


    @Override
    public ForgetPasswordResponse forgetPassword(ForgetPasswordRequest request) {
        try {
            /** 1. Verify the user if exists in DB */
            if (UserDAO.checkUser(request.getUsername())) {
                return new ForgetPasswordResponse(String.format(ERROR_MESSAGE,
                        "Invalid Request", "No Such User"));
            }

            /** 2.Generate a random token and store it in tha table */
            String valiToken = String.format("%06d", RAND.nextInt(MAX_NUMBER));
            ValidationTokenDAO valiDAO = new ValidationTokenDAO();
            long currTime = new Timestamp(System.currentTimeMillis()).getTime();
            // add to DB
            valiDAO.addToken(valiToken, currTime, request.getUsername());

            /** 3.Set up Email Notification and send the validation code to the user */
            try {
                AmazonSimpleEmailService client =
                        AmazonSimpleEmailServiceClientBuilder.standard()
                                .withRegion(Regions.US_EAST_2).build();

                // Use the AmazonSimpleEmailService object to send an email message
                List<String> desties = new ArrayList<>();
                desties.add(request.getUsername()); // username is the client email
                Destination destination = new Destination(desties);

                Content subject = new Content(SUBJECT);
                Content text = new Content(String.format(TEXT, valiToken));
                Body body = new Body(text);
                Message message = new Message(subject, body);
                SendEmailRequest sendEmailRequest = new SendEmailRequest(EMAIL_SENDER, destination, message);

                // send the email
                client.sendEmail(sendEmailRequest);
                return new ForgetPasswordResponse();

            } catch (Exception e) {
                return new ForgetPasswordResponse(e.getMessage());
            }
        } catch (DBRemoteException e) {
            return new ForgetPasswordResponse(String.format(ERROR_MESSAGE,
                    e.getErrorType(), e.getMessage()));
        }
    }
}
