package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.jinxinyu.caltracker.service.GetRecordsService;
import me.jinxinyu.caltracker.service.GetRecordsServiceImpl;
import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.request.GetTimedRecordRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;


public class GetRecordsBetweenTimeHandler implements RequestHandler<GetTimedRecordRequest, GetRecordsResponse> {

    @Override
    public GetRecordsResponse handleRequest(GetTimedRecordRequest request, Context context) {
        GetRecordsService service;
        try {
            service = new GetRecordsServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getTimedTrack(request);
    }
}
