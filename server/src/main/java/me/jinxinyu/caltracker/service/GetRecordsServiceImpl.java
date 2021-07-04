package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.dao.FavDAO;
import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.GetTimedRecordRequest;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;
import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;


public class GetRecordsServiceImpl extends ServiceImpl implements GetRecordsService{
    @Override
    public GetRecordsResponse getTrack(GetRecordsRequest request) {
        validateToken(request.getToken(), request.getAlias());
        return getTrackDAO().getRecords(request);
    }

    @Override
    public GetRecordsResponse getTimedTrack(GetTimedRecordRequest request) {
        validateToken(request.getToken(), request.getAlias());
        return getTrackDAO().getRecordsBetweenTime(request);
    }

    @Override
    public GetRecordsResponse getCart(GetRecordsRequest request) {
        validateToken(request.getToken(), request.getAlias());
        return getCartDAO().getRecords(request);
    }

    @Override
    public GetRecordsResponse getFavList(GetRecordsRequest request) {
        validateToken(request.getToken(), request.getAlias());
        return getFavDAO().getRecords(request);
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
