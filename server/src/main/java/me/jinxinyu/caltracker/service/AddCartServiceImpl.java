package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;
import me.jinxinyu.caltracker.service.response.RecordResponse;

public class AddCartServiceImpl extends ServiceImpl implements AddTrackService{
    @Override
    public RecordResponse addTrack(RecordRequest addTrackRequest) {
//        validateToken(addTrackRequest.getAuthToken());
        return getTrackDAO().addTrack(addTrackRequest);
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
}
