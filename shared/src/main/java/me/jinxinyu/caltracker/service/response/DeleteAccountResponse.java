package me.jinxinyu.caltracker.service.response;

public class DeleteAccountResponse extends Response {

    public DeleteAccountResponse(String message) {
        super(false, message);
    }

    public DeleteAccountResponse() {
        super(true, null);
    }

}
