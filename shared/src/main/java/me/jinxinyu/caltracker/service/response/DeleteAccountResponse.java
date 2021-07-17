package me.jinxinyu.caltracker.service.response;

public class DeleteAccountResponse extends Response {

    public DeleteAccountResponse(boolean success, String message) {
        super(success, message);
    }

    public DeleteAccountResponse() {
        super(true, null);
    }

}
