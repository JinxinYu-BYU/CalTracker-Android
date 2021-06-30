package me.jinxinyu.caltracker.service.response;

public class RecordResponse extends Response{


    public RecordResponse() {
        super(false, "Failed to add food to the track");
    }

    public RecordResponse(boolean success) {
        super(success, "Successfully add food to the track");
    }
}
