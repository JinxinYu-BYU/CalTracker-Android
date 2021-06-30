package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.LoginServiceImpl;
import me.jinxinyu.caltracker.service.request.LoginRequest;
import me.jinxinyu.caltracker.service.response.LoginResponse;

/**
 * AWS Lambda Handler for user login request
 */
public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {
    @Override
    public LoginResponse handleRequest(LoginRequest loginRequest, Context context) {
        LoginServiceImpl loginService = new LoginServiceImpl();
        return loginService.login(loginRequest);
    }
}
