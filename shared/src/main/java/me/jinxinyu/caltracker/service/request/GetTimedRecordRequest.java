package me.jinxinyu.caltracker.service.request;


import me.jinxinyu.caltracker.domain.Record;

public class GetTimedRecordRequest {
    private Record lastRecord;
    private String userId;
    private int limit;
    private int timeStart;
    private int timeEnd;

    public GetTimedRecordRequest() {
    }

    public GetTimedRecordRequest(Record lastRecord, String userId, int limit, int timeStart, int timeEnd) {
        this.lastRecord = lastRecord;
        this.userId = userId;
        this.limit = limit;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
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
