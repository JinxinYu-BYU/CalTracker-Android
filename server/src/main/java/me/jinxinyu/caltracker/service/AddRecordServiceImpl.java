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
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getTrackDAO().addRecord(request);
    }

    @Override
    public Response addCart(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getCartDAO().addRecord(request);
    }

    @Override
    public Response addFavList(RecordRequest request) {
        validateToken(request.getAuthToken(), request.getRecord().getAlias());
        return getFavDAO().addRecord(request);
    }

    public void batchTrackWrite(PostBatchRequest request) {
        validateToken(request.getToken(), request.getAlias());
        getTrackDAO().addRecordBatch(request.getRecords());
    }

    private TrackDAO getTrackDAO() {return new TrackDAO();}
    private CartDAO  getCartDAO(){return new CartDAO();}
    private FavDAO getFavDAO() {return new FavDAO();}
}
