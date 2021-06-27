package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;
import me.jinxinyu.caltracker.service.response.RecordResponse;

public interface AddTrackService {
    RecordResponse addTrack(RecordRequest addTrackRequest);
}
