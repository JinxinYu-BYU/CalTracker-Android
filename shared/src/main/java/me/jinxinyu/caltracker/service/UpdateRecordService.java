package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public interface UpdateRecordService {
    Response updateTrack(RecordRequest deleteTrackRequest);
    Response updateCart(RecordRequest deleteTrackRequest);
    Response updateFavList(RecordRequest deleteTrackRequest);
}
