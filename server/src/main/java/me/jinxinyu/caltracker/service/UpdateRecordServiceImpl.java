package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.dao.FavDAO;
import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public class UpdateRecordServiceImpl extends ServiceImpl implements UpdateRecordService{
    @Override
    public Response updateTrack(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null)
            return new Response(getTrackDAO().updateTrack(request), newToken);
        else
            return new Response(false, "Invalid Token!");
    }

    @Override
    public Response updateCart(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null)
            return new Response(getCartDAO().updateRecords(request), newToken);
        else
            return new Response(false, "Invalid Token!");
    }

    @Override
    public Response updateFavList(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null)
            return new Response(getFavDAO().updateRecords(request), newToken);
        else
            return new Response(false, "Invalid Token!");
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
