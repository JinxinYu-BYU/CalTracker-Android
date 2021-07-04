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
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getTrackDAO().updateTrack(request);
    }

    @Override
    public Response updateCart(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getCartDAO().updateRecords(request);
    }

    @Override
    public Response updateFavList(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getFavDAO().updateRecords(request);
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
