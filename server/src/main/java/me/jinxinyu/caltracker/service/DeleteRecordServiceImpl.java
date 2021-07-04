package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.dao.FavDAO;
import me.jinxinyu.caltracker.dao.TrackDAO;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;

public class DeleteRecordServiceImpl extends ServiceImpl implements DeleteRecordService{
    @Override
    public Response deleteTrack(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getTrackDAO().deleteRecord(request);
    }

    @Override
    public Response deleteCart(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getCartDAO().deleteRecord(request);
    }

    @Override
    public Response deleteFavList(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getFavDAO().deleteRecord(request);
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
