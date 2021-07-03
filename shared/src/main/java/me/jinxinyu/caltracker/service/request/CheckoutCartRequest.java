package me.jinxinyu.caltracker.service.request;


import me.jinxinyu.caltracker.domain.Record;

public class CheckoutCartRequest {
    private String alias;
    private Record lastRecord = new Record(null, null, 0, 0L);
    private String authToken;

    public CheckoutCartRequest(String alias, Record lastRecord, String authToken) {
        this.alias = alias;
        this.lastRecord = lastRecord;
        this.authToken = authToken;
    }

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

    public Record getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(Record lastRecord) {
        this.lastRecord = lastRecord;
    }
}
