package me.jinxinyu.caltracker.dao;

import me.jinxinyu.caltracker.service.request.GetRecordRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.GetRecordResponse;
import me.jinxinyu.caltracker.service.response.RecordResponse;

public class FavDAO {
    private static String TABLE_NAME = "cal_favfood";

    public RecordResponse addFavList(RecordRequest request) {
        return new RecordResponse(DAO.addRecord(TABLE_NAME, request));
    }

    public void updateRecords(RecordRequest request){
        //TODO could return meaningful message if needed
        //dive into the outcome
        DAO.updateRecord(TABLE_NAME, request);
    }

    public void deleteRecord(RecordRequest request){
        DAO.deleteRecord(TABLE_NAME, request);
    }

    public GetRecordResponse getRecords(GetRecordRequest request) {
        return DAO.getRecords(TABLE_NAME, request);
    }

    public RecordResponse addToCart(RecordRequest request) {
        return CartDAO.addCart(request);
    }


}
