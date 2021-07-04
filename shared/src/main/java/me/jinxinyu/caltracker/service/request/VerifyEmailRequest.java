package me.jinxinyu.caltracker.service.request;

public class VerifyEmailRequest {

    /** Username is in email format */
    private String username;

    /**
     * Allows construction of the object from Json. Private so it won't be called in normal code.
     */
    private VerifyEmailRequest () {}

    /**
     * Creates a forget password request
     */
    public VerifyEmailRequest (String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
