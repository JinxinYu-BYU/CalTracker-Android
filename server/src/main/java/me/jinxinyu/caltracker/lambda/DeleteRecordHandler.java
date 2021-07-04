package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.DeleteRecordService;
import me.jinxinyu.caltracker.service.DeleteRecordServiceImpl;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;


public class DeleteRecordHandler implements RequestHandler<RecordRequest, Response> {
    @Override
    public Response handleRequest(RecordRequest request, Context context) {

        switch(request.getType())
        {
            case "track":
                return deleteTrack(request);
            case "cart":
                return deleteCart(request);
            case "fav":
                return deleteFavList(request);
            default:
                return new Response(false,"Invalid table name");
        }


    }

    private Response deleteTrack(RecordRequest request){
        DeleteRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new DeleteRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.deleteTrack(request);
    }

    private Response deleteCart(RecordRequest request){
        DeleteRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new DeleteRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.deleteCart(request);
    }

    private Response deleteFavList(RecordRequest request){
        DeleteRecordService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new DeleteRecordServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.deleteFavList(request);
    }
}
