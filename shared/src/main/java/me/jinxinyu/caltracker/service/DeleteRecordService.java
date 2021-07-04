package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public interface DeleteRecordService {
    Response deleteTrack(RecordRequest request);
    Response deleteCart(RecordRequest request);
    Response deleteFavList(RecordRequest request);
}
