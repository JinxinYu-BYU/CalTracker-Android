package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.DeleteAccountService;
import me.jinxinyu.caltracker.service.DeleteAccountServiceImpl;
import me.jinxinyu.caltracker.service.LogoutServiceImpl;
import me.jinxinyu.caltracker.service.request.DeleteAccountRequest;
import me.jinxinyu.caltracker.service.response.DeleteAccountResponse;

public class DeleteAccountHandler implements RequestHandler<DeleteAccountRequest, DeleteAccountResponse> {
    @Override
    public DeleteAccountResponse handleRequest(DeleteAccountRequest request, Context context) {
        DeleteAccountService service = new DeleteAccountServiceImpl();
        return service.delete(request);
    }
}
