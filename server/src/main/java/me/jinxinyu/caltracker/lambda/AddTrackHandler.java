package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.AddTrackService;
import me.jinxinyu.caltracker.service.AddTrackServiceImpl;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.RecordResponse;


public class AddTrackHandler implements RequestHandler<RecordRequest, RecordResponse> {
    @Override
    public RecordResponse handleRequest(RecordRequest request, Context context) {

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
