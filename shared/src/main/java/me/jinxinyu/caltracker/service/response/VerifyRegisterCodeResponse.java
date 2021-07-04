package me.jinxinyu.caltracker.service.response;

public class VerifyRegisterCodeResponse extends Response{

    public VerifyRegisterCodeResponse(String message) {
        super(false, message);
    }

    public VerifyRegisterCodeResponse() {
        super(true, null);
    }
}
