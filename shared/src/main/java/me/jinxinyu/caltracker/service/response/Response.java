package me.jinxinyu.caltracker.service.response;

public class Response {

    private boolean success;
    private String message;
    private String token;

//
//    Response(String message) {
//        this(false, null);
//    }

//    public Response(boolean success) {
//        this.success = success;
//    }

    /**
     * Creates an instance.
     *
     * @param success the success indicator.
     * @param message the error message.
     */
    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(Response response, String token) {
        this.success = response.success;
        this.message = response.message;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    /**
     * Indicates whether the response represents a successful result.
     *
     * @return the success indicator.
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * The error message for unsuccessful results.
     *
     * @return an error message or null if the response indicates a successful result.
     */
    public String getMessage() {
        return message;
    }
}
