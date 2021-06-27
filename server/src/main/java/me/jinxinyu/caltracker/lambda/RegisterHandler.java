package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import me.jinxinyu.caltracker.net.JsonSerializer;
import me.jinxinyu.caltracker.service.RegisterServiceImpl;
import me.jinxinyu.caltracker.service.request.RegisterRequest;
import me.jinxinyu.caltracker.service.response.RegisterResponse;

/**
 * AWS Lambda Handler for user registration request
 */
public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {

    @Override
    public RegisterResponse handleRequest(RegisterRequest request, Context context) {
        RegisterServiceImpl service = new RegisterServiceImpl();
        return service.register(request);
    }

    public static void main(String[] args) {
        RegisterRequest r = new RegisterRequest("mike", "wu", "mike1", "123", "", 40, 180, 20);
    }
}
