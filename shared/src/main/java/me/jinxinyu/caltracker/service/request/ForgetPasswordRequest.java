package me.jinxinyu.caltracker.service.request;

public class ForgetPasswordRequest {

    /** Username is in email format */
    private String username;

    /**
     * Allows construction of the object from Json. Private so it won't be called in normal code.
     */
    private ForgetPasswordRequest() {}

    /**
     * Creates a forget password request
     */
    public ForgetPasswordRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
