package me.jinxinyu.caltracker.dao;

import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;
import me.jinxinyu.caltracker.service.response.Response;

public class FavDAO {
    private static String TABLE_NAME = "cal_favfood";

    public Response addRecord(RecordRequest request) {
        return DAO.addRecord(TABLE_NAME, request);
    }

    public Response updateRecords(RecordRequest request){
        return DAO.updateRecord(TABLE_NAME, request);
    }

    public Response deleteRecord(RecordRequest request){
        return DAO.deleteRecord(TABLE_NAME, request);
    }

    public GetRecordsResponse getRecords(GetRecordsRequest request) {
        return DAO.getRecords(TABLE_NAME, request);
    }

    public Response addToCart(RecordRequest request) {
        return CartDAO.addRecord(request);
    }

    public Response addToTrack(RecordRequest request) {
        return TrackDAO.addRecord(request);
    }


}
