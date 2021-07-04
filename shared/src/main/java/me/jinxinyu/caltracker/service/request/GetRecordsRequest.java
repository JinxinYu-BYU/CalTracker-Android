package me.jinxinyu.caltracker.service.request;


import me.jinxinyu.caltracker.domain.Record;

public class GetRecordsRequest {
    private Record lastRecord;
    private String alias;
    private int limit;
    private String token;
    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GetRecordsRequest() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GetRecordsRequest(Record lastRecord, String alias, int limit, String token, String type) {
        this.lastRecord = lastRecord;
        this.alias = alias;
        this.limit = limit;
        this.token = token;
        this.type = type;
    }

    public Record getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(Record lastRecord) {
        this.lastRecord = lastRecord;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
