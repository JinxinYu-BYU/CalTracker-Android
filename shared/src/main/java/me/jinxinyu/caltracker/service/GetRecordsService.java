package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.request.GetTimedRecordRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;

public interface GetRecordsService {
    GetRecordsResponse getTrack(GetRecordsRequest request);
    GetRecordsResponse getTimedTrack(GetTimedRecordRequest request);
    GetRecordsResponse getCart(GetRecordsRequest request);
    GetRecordsResponse getFavList(GetRecordsRequest request);
}
