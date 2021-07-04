package me.jinxinyu.caltracker.service.request;

import me.jinxinyu.caltracker.domain.Record;

public class RecordRequest {
    private Record record;
    private String authToken;
    private String type;

    public RecordRequest(Record record, String authToken, String type) {
        this.record = record;
        this.authToken = authToken;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RecordRequest(){}

//    public RecordRequest(Record record, String authToken) {
//        this.record = record;
//        this.authToken = authToken;
//    }

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
