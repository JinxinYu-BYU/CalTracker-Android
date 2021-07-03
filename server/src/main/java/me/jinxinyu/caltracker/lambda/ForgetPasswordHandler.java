package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.ForgetPasswordService;
import me.jinxinyu.caltracker.service.ForgetPasswordServiceImpl;
import me.jinxinyu.caltracker.service.request.ForgetPasswordRequest;
import me.jinxinyu.caltracker.service.response.ForgetPasswordResponse;


public class ForgetPasswordHandler implements RequestHandler<ForgetPasswordRequest, ForgetPasswordResponse> {

    @Override
    public ForgetPasswordResponse handleRequest(ForgetPasswordRequest request, Context context) {
        ForgetPasswordService service = new ForgetPasswordServiceImpl();
        return service.forgetPassword(request);
    }
}
