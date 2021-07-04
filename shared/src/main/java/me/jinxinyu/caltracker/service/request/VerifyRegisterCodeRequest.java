package me.jinxinyu.caltracker.service.request;

public class VerifyRegisterCodeRequest {

    private String userAlias;
    private String token;

    public VerifyRegisterCodeRequest(String userAlias, String token) {
        this.userAlias = userAlias;
        this.token = token;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public String getToken() {
        return token;
    }
}
