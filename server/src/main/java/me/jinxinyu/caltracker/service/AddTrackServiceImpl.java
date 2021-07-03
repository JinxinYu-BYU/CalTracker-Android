package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;
import me.jinxinyu.caltracker.service.response.RecordResponse;

public class AddTrackServiceImpl extends ServiceImpl implements AddTrackService{
    @Override
    public RecordResponse addTrack(RecordRequest addTrackRequest) {
//        validateToken(addTrackRequest.getAuthToken());
        return getTrackDAO().addTrack(addTrackRequest);
    }

    public void batchFeedUpdate(PostBatchRequest request) {
        validateToken(request.getToken(), request.getAlias());
        getTrackDAO().addRecordBatch(request.getRecords());
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
}
