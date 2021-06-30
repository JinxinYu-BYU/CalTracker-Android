package me.jinxinyu.caltracker.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.dao.UserDAO;
import me.jinxinyu.caltracker.service.request.RegisterRequest;
import me.jinxinyu.caltracker.service.response.RegisterResponse;

public class RegisterServiceImpl extends ServiceImpl implements RegisterService {

    @Override
    public RegisterResponse register(RegisterRequest request){

        AuthsDAO authsDAO = new AuthsDAO();
        UserDAO userDAO = new UserDAO();

        RegisterRequest hashedRequest;
        try {
            // hash the password
            request.setPassword(generateHash(request.getPassword()));
            // upload image
            uploadImage(request.getImageUrl(), request.getUserName());
        } catch (IOException e) {
            throw new RuntimeException("500");
        }

        RegisterResponse response = userDAO.register(request);
        if (response.isSuccess()) {
            String token = UUID.randomUUID().toString();
            long currTime = new Timestamp(System.currentTimeMillis()).getTime();
            authsDAO.addToken(token, currTime, request.getUserName());
            response.setAuthToken(token);
        }
        return response;
    }
}
