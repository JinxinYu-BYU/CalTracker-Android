package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.LogoutServiceImpl;
import me.jinxinyu.caltracker.service.request.LogoutRequest;
import me.jinxinyu.caltracker.service.response.LogoutResponse;

public class LogoutHandler implements RequestHandler<LogoutRequest, LogoutResponse> {
    @Override
    public LogoutResponse handleRequest(LogoutRequest request, Context context) {
        LogoutServiceImpl service = new LogoutServiceImpl();
        return service.logout(request);
    }
}
