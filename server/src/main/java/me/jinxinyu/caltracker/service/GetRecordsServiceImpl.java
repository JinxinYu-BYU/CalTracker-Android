package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.dao.FavDAO;
import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.GetTimedRecordRequest;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;
import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;
import me.jinxinyu.caltracker.service.response.Response;

import java.util.ArrayList;


public class GetRecordsServiceImpl extends ServiceImpl implements GetRecordsService{
    @Override
    public GetRecordsResponse getTrack(GetRecordsRequest request) {
        String newToken = validateToken(request.getToken(), request.getAlias());
        if(newToken != null){
            return new GetRecordsResponse(getTrackDAO().getRecords(request), newToken);
        }else {
            return new GetRecordsResponse(false, "Invalid Token", false, new ArrayList<>());
        }

    }

    @Override
    public GetRecordsResponse getTimedTrack(GetTimedRecordRequest request) {

        String newToken = validateToken(request.getToken(), request.getAlias());
        if(newToken != null){
            return new GetRecordsResponse(getTrackDAO().getRecordsBetweenTime(request), newToken);
        }else {
            return new GetRecordsResponse(false, "Invalid Token", false, new ArrayList<>());
        }
    }

    @Override
    public GetRecordsResponse getCart(GetRecordsRequest request) {
        String newToken = validateToken(request.getToken(), request.getAlias());
        if(newToken != null){
            return new GetRecordsResponse(getCartDAO().getRecords(request), newToken);
        }else {
            return new GetRecordsResponse(false, "Invalid Token", false, new ArrayList<>());
        }
    }

    @Override
    public GetRecordsResponse getFavList(GetRecordsRequest request) {
        String newToken = validateToken(request.getToken(), request.getAlias());
        if(newToken != null){
            return new GetRecordsResponse(getFavDAO().getRecords(request), newToken);
        }else {
            return new GetRecordsResponse(false, "Invalid Token", false, new ArrayList<>());
        }
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
