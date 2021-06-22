package me.jinxinyu.caltracker.service.request;

import me.jinxinyu.caltracker.domain.Record;

public class AddTrackRequest {
    private Record record;
    private String authToken;

    public AddTrackRequest(){}

    public AddTrackRequest(Record record, String authToken) {
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
