package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.ResetPasswordService;
import me.jinxinyu.caltracker.service.ResetPasswordServiceImpl;
import me.jinxinyu.caltracker.service.request.ResetPasswordRequest;
import me.jinxinyu.caltracker.service.response.ResetPasswordResponse;

public class ResetPasswordHandler implements RequestHandler<ResetPasswordRequest, ResetPasswordResponse> {
    @Override
    public ResetPasswordResponse handleRequest(ResetPasswordRequest request, Context context) {
        ResetPasswordService service = new ResetPasswordServiceImpl();
        return service.resetPassword(request);
    }
}
