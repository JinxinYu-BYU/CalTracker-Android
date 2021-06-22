package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.AddTrackRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;

public class AddTrackServiceImpl extends ServiceImpl implements AddTrackService{
    @Override
    public AddTrackResponse addTrack(AddTrackRequest addTrackRequest) {
//        validateToken(addTrackRequest.getAuthToken());
        return getTrackDAO().addTrack(addTrackRequest);
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
}
