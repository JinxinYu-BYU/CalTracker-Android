package me.jinxinyu.caltracker.net;

public class DBRemoteException extends Exception {

    private final String errorType;

    public DBRemoteException(String message, String errorType) {
        super(message);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }
}
