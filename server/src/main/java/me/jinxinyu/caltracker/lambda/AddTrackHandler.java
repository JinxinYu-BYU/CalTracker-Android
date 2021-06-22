package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.AddTrackService;
import me.jinxinyu.caltracker.service.AddTrackServiceImpl;
import me.jinxinyu.caltracker.service.request.AddTrackRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;


public class AddTrackHandler implements RequestHandler<AddTrackRequest, AddTrackResponse> {
    @Override
    public AddTrackResponse handleRequest(AddTrackRequest request, Context context) {

        AddTrackService service;
        if (request.getRecord() == null) {
            throw new RuntimeException("400");
        }
        try {
            service = new AddTrackServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }


        return service.addTrack(request);
    }
}
