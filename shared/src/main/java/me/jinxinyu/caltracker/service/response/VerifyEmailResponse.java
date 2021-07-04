package me.jinxinyu.caltracker.service.response;

public class VerifyEmailResponse extends Response {

    public VerifyEmailResponse(String message) {
        super(false, message);
    }

    public VerifyEmailResponse() {
        super(true, null);
    }

}
