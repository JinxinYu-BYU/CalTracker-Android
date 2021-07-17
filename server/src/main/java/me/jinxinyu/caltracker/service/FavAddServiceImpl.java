package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.dao.FavDAO;
import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public class FavAddServiceImpl extends ServiceImpl implements FavAddService{
    @Override
    public Response addToTrack(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null){
            return new Response(getFavDAO().addToTrack(request), newToken);
        }else {
            return new Response(false, "Invalid Token!");
        }
    }

    @Override
    public Response addToCart(RecordRequest request) {
        String newToken = validateToken(request.getAuthToken(), request.getRecord().getAlias());
        if(newToken != null){
            return new Response(getFavDAO().addToCart(request), newToken);
        }else {
            return new Response(false, "Invalid Token!");
        }
    }

    private FavDAO getFavDAO() {return new FavDAO();}
}
