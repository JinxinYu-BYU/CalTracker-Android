package me.jinxinyu.caltracker.service.response;

public class LogoutResponse extends Response {

    /**
     * Creates a response indicating that the corresponding request was invalid.
     *
     * @param message a message describing why the request was invalid.
     */
    public LogoutResponse(String message) {
        super(false, message);
    }

    public LogoutResponse() {
        super(true, null);
    }
}
