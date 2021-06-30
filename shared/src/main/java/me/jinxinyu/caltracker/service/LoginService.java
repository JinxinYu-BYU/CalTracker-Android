package me.jinxinyu.caltracker.service;

import java.io.IOException;

import me.jinxinyu.caltracker.service.request.LoginRequest;
import me.jinxinyu.caltracker.service.response.LoginResponse;

/**
 * Interface for login service
 *
 * @author Valentine
 */
public interface LoginService {

    /** User Login Action */
    LoginResponse login(LoginRequest request) throws IOException;


}
