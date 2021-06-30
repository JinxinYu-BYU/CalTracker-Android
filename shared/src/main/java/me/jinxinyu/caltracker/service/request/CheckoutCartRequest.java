package me.jinxinyu.caltracker.service.request;


import me.jinxinyu.caltracker.domain.Record;

public class CheckoutCartRequest {
    private String alias;
    private String authToken;

    public CheckoutCartRequest() {
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public CheckoutCartRequest(String alias, String authToken) {
        this.alias = alias;
        this.authToken = authToken;
    }
}
