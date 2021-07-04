package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.VerifyEmailService;
import me.jinxinyu.caltracker.service.VerifyEmailServiceImpl;
import me.jinxinyu.caltracker.service.request.VerifyEmailRequest;
import me.jinxinyu.caltracker.service.response.VerifyEmailResponse;

public class VerifyEmailHandler implements RequestHandler<VerifyEmailRequest, VerifyEmailResponse> {

    @Override
    public VerifyEmailResponse handleRequest(VerifyEmailRequest request, Context context) {
        VerifyEmailService service = new VerifyEmailServiceImpl();
        return service.verifyEmail(request);
    }
}
