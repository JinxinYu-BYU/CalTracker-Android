package me.jinxinyu.caltracker.service.response;

public class AddTrackResponse extends Response{


    public AddTrackResponse() {
        super("Failed to add food to the track");
    }

    public AddTrackResponse(boolean success) {
        super(success, "Successfully add food to the track");
    }
}
