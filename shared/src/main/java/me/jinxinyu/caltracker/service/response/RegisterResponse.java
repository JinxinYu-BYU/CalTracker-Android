package me.jinxinyu.caltracker.service.response;

import me.jinxinyu.caltracker.domain.User;

/**
 * User Register One-time Response
 */
public class RegisterResponse extends Response{

    private User user;
    private String authToken;

    public RegisterResponse(String message) { super(false, message); }

    public RegisterResponse(User user, String authToken) {
        super(true, null);
        this.user = user;
        this.authToken = authToken;
    }

    public User getUser() {
        return user;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
