package me.jinxinyu.caltracker.service.response;

public class ForgetPasswordResponse extends Response {

    public ForgetPasswordResponse(String message) {
        super(false, message);
    }

    public ForgetPasswordResponse() {
        super(true, null);
    }
}
