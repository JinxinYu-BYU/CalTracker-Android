package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public interface AddTrackService {
    Response addTrack(RecordRequest addTrackRequest);
}
