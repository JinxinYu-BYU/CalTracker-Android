package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.AddTrackRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;

public interface AddTrackService {
    AddTrackResponse addTrack(AddTrackRequest addTrackRequest);
}
