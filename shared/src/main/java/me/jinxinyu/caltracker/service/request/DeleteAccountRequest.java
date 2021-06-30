package me.jinxinyu.caltracker.service.request;

import me.jinxinyu.caltracker.domain.User;

public class DeleteAccountRequest {
    private User user;
    private String authToken;

    /**
     * Creates an instance.
     *  @param user the user to be deleted
     * @param authToken the authToken of the user to be deleted
     */
    public DeleteAccountRequest(User user, String authToken) {
        this.user = user;
        this.authToken = authToken;
    }

    private DeleteAccountRequest(){}
    /**
     * Returns the username of the user to be deleted
     *
     * @return the username.
     */
    public User getUser() { return user; }

    public String getAuthToken() { return authToken; }

    public void setAuthToken(String token) { authToken = token; }
}
