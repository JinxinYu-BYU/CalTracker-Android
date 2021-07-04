package me.jinxinyu.caltracker.service.response;

public class ResetPasswordResponse extends Response {

    public ResetPasswordResponse(String message) {
        super(false, message);
    }

    public ResetPasswordResponse() {
        super(true, null);
    }

}
