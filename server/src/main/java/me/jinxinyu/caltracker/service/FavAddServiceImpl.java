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
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getFavDAO().addToTrack(request);
    }

    @Override
    public Response addToCart(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getFavDAO().addToCart(request);
    }

    private FavDAO getFavDAO() {return new FavDAO();}
}
