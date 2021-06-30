package me.jinxinyu.caltracker.service.request;


import me.jinxinyu.caltracker.domain.Record;

public class GetTrackRecordRequest {
    private Record lastRecord;
    private String userId;
    private int limit;

    public GetTrackRecordRequest(Record lastRecord, String userId, int limit) {
        this.lastRecord = lastRecord;
        this.userId = userId;
        this.limit = limit;
    }

    public Record getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(Record lastRecord) {
        this.lastRecord = lastRecord;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
