package me.jinxinyu.caltracker.service.request;

import me.jinxinyu.caltracker.domain.User;

public class LogoutRequest {

    private User user;
    private String authToken;

    /**
     * Creates an instance.
     *  @param user the user to be logged out.
     * @param authToken the authToken of the user to be logged out.
     */
    public LogoutRequest(User user, String authToken) {
        this.user = user;
        this.authToken = authToken;
    }

    private LogoutRequest(){}
    /**
     * Returns the username of the user to be logged out by this request.
     *
     * @return the username.
     */
    public User getUser() { return user; }

    public String getAuthToken() { return authToken; }

    public void setAuthToken(String token) { authToken = token; }
}
