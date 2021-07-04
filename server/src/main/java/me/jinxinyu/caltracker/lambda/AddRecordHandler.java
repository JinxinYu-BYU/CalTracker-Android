package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.AddRecordService;
import me.jinxinyu.caltracker.service.AddRecordServiceImpl;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;


public class AddRecordHandler implements RequestHandler<RecordRequest, Response> {
    @Override
    public Response handleRequest(RecordRequest request, Context context) {

        switch(request.getType())
        {
            case "track":
                return addTrack(request);
            case "cart":
                return addCart(request);
            case "fav":
                return addFavList(request);
            default:
                return new Response(false,"Invalid table name");
        }


    }

    private Response addTrack(RecordRequest request){
        AddRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new AddRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.addTrack(request);
    }

    private Response addCart(RecordRequest request){
        AddRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new AddRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.addCart(request);
    }

    private Response addFavList(RecordRequest request){
        AddRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new AddRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.addFavList(request);
    }
}
