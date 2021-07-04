package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public interface FavAddService {
    Response addToTrack(RecordRequest request);
    Response addToCart(RecordRequest request);
}
