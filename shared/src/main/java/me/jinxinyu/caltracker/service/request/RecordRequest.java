package me.jinxinyu.caltracker.service.request;

import me.jinxinyu.caltracker.domain.Record;

public class RecordRequest {
    private Record record;
    private String authToken;

    public RecordRequest(){}

    public RecordRequest(Record record, String authToken) {
        this.record = record;
        this.authToken = authToken;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
