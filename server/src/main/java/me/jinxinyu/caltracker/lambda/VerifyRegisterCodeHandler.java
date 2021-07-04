package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.VerifyRegisterCodeService;
import me.jinxinyu.caltracker.service.VerifyRegisterCodeServiceImpl;
import me.jinxinyu.caltracker.service.request.VerifyRegisterCodeRequest;
import me.jinxinyu.caltracker.service.response.VerifyRegisterCodeResponse;

public class VerifyRegisterCodeHandler implements
        RequestHandler<VerifyRegisterCodeRequest, VerifyRegisterCodeResponse> {

    @Override
    public VerifyRegisterCodeResponse handleRequest(VerifyRegisterCodeRequest request, Context context) {
        VerifyRegisterCodeService service = new VerifyRegisterCodeServiceImpl();
        return service.verifyRegisterCode(request);
    }
}
