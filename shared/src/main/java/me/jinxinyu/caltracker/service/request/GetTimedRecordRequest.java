package me.jinxinyu.caltracker.service.request;


import me.jinxinyu.caltracker.domain.Record;

public class GetTimedRecordRequest {
    private Record lastRecord;
    private String alias;
    private int limit;
    private int timeStart;
    private int timeEnd;
    private String token;

    public GetTimedRecordRequest(Record lastRecord, String alias, int limit, int timeStart, int timeEnd, String token) {
        this.lastRecord = lastRecord;
        this.alias = alias;
        this.limit = limit;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.token = token;
        if(lastRecord.getTime() < timeStart){
            this.lastRecord.setTime(timeStart);
        }
        //this request should not be sent by the front in this case.
        // but I will leave the check here for the time of being
        if(lastRecord.getTime() > timeEnd){
            this.lastRecord.setTime(timeEnd);
        }

    }

    public GetTimedRecordRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
