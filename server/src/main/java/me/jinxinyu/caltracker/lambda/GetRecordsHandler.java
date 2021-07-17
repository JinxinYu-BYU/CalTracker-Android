package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.GetRecordsService;
import me.jinxinyu.caltracker.service.GetRecordsServiceImpl;
import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;

import java.util.LinkedList;


public class GetRecordsHandler implements RequestHandler<GetRecordsRequest, GetRecordsResponse> {
    @Override
    public GetRecordsResponse handleRequest(GetRecordsRequest request, Context context) {

        switch(request.getType())
        {
            case "track":
                return getTrack(request);
            case "cart":
                return getCart(request);
            case "fav":
                return getFavList(request);
            default:
                return new GetRecordsResponse(false,"Invalid table name", false, new LinkedList<>());
        }


    }

    private GetRecordsResponse getTrack(GetRecordsRequest request){
        GetRecordsService service;
        try {
            service = new GetRecordsServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getTrack(request);
    }

    private GetRecordsResponse getCart(GetRecordsRequest request){
        GetRecordsService service;
        try {
            service = new GetRecordsServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getCart(request);
    }

    private GetRecordsResponse getFavList(GetRecordsRequest request){
        GetRecordsService service;
        try {
            service = new GetRecordsServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getFavList(request);
    }
}
