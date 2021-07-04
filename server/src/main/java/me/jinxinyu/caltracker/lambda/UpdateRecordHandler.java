package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.UpdateRecordService;
import me.jinxinyu.caltracker.service.UpdateRecordServiceImpl;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;


public class UpdateRecordHandler implements RequestHandler<RecordRequest, Response> {
    @Override
    public Response handleRequest(RecordRequest request, Context context) {

        switch(request.getType())
        {
            case "track":
                return updateTrack(request);
            case "cart":
                return updateCart(request);
            case "fav":
                return updateFavList(request);
            default:
                return new Response(false,"Invalid table name");
        }


    }

    private Response updateTrack(RecordRequest request){
        UpdateRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new UpdateRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.updateTrack(request);
    }

    private Response updateCart(RecordRequest request){
        UpdateRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new UpdateRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.updateCart(request);
    }

    private Response updateFavList(RecordRequest request){
        UpdateRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new UpdateRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.updateFavList(request);
    }
}
