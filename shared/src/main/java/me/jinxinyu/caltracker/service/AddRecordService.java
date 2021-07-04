package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public interface AddRecordService {
    Response addTrack(RecordRequest request);
    Response addCart(RecordRequest request);
    Response addFavList(RecordRequest request);
}
