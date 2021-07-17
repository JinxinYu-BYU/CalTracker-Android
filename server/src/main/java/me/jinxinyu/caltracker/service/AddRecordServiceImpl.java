package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.dao.FavDAO;
import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public class AddRecordServiceImpl extends ServiceImpl implements AddRecordService{
    @Override
    public Response addTrack(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null)
            return new Response(getTrackDAO().addRecord(request), newToken);
        else
            return new Response(false, "Invalid Token!");
    }

    @Override
    public Response addCart(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null)
            return new Response(getCartDAO().addRecord(request), newToken);
        else
            return new Response(false, "Invalid Token!");
    }

    @Override
    public Response addFavList(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null)
            return new Response(getFavDAO().addRecord(request), newToken);
        else
            return new Response(false, "Invalid Token!");
    }

    public void batchTrackWrite(PostBatchRequest request) {
        String newToken = validateToken(request.getToken(), request.getAlias());
        if(newToken != null)
            getTrackDAO().addRecordBatch(request.getRecords());
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
