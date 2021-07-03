package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import me.jinxinyu.caltracker.net.JsonSerializer;
import me.jinxinyu.caltracker.service.AddTrackServiceImpl;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;


public class TrackQueueHandler implements RequestHandler<SQSEvent, Void> {
    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        AddTrackServiceImpl service;
        try {
            service = new AddTrackServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }

        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            System.out.println(msg.getBody());
            PostBatchRequest body = JsonSerializer.deserialize(msg.getBody(), PostBatchRequest.class);

            service.batchFeedUpdate(body);
        }
        return null;
    }
}
