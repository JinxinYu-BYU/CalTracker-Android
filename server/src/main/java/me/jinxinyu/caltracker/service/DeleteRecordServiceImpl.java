package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.dao.FavDAO;
import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public class DeleteRecordServiceImpl extends ServiceImpl implements DeleteRecordService{
    @Override
    public Response deleteTrack(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null){
            return new Response(getTrackDAO().deleteRecord(request), newToken);
        }else {
            return new Response(false, "Invalid Token!");
        }

    }

    @Override
    public Response deleteCart(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null){
            return new Response(getCartDAO().deleteRecord(request), newToken);
        }else {
            return new Response(false, "Invalid Token!");
        }
    }

    @Override
    public Response deleteFavList(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null){
            return new Response(getFavDAO().deleteRecord(request), newToken);
        }else {
            return new Response(false, "Invalid Token!");
        }
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
