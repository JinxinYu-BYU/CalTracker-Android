package me.jinxinyu.caltracker.service.request;

public class ResetPasswordRequest {

    private String userAlias;
    private String token;
    private String password;
    private String password2;

    public ResetPasswordRequest(String userAlias, String token,
                                String password, String password2) {
        this.userAlias = userAlias;
        this.token = token;
        this.password = password;
        this.password2 = password2;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}

