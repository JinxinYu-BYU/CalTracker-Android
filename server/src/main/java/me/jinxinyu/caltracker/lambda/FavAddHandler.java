package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.FavAddService;
import me.jinxinyu.caltracker.service.FavAddServiceImpl;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;


public class FavAddHandler implements RequestHandler<RecordRequest, Response> {
    @Override
    public Response handleRequest(RecordRequest request, Context context) {

        switch(request.getType())
        {
            case "track":
                return addToTrack(request);
            case "cart":
                return addToCart(request);
            default:
                return new Response(false,"Invalid table name");
        }


    }

    private Response addToTrack(RecordRequest request){
        FavAddService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new FavAddServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.addToTrack(request);
    }

    private Response addToCart(RecordRequest request){
        FavAddService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new FavAddServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.addToCart(request);
    }
    
}
