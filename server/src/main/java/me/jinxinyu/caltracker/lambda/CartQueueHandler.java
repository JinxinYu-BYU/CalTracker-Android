package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.net.JsonSerializer;
import me.jinxinyu.caltracker.service.CheckoutCartServiceImpl;
import me.jinxinyu.caltracker.service.request.CheckoutCartRequest;
import me.jinxinyu.caltracker.service.request.PostBatchRequest;
import me.jinxinyu.caltracker.service.response.ClearCartResponse;

public class CartQueueHandler implements RequestHandler<SQSEvent, Void> {
    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        //TODO: update the queue url later
        String queueURL = "https://sqs.us-east-2.amazonaws.com/376992233301/TrackQueue";

        CheckoutCartServiceImpl service = new CheckoutCartServiceImpl();
        int page_size = 25; //batch write max limit size
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            System.out.println("message body in SQS: " + msg.getBody());
            CheckoutCartRequest request = JsonSerializer.deserialize(msg.getBody(), CheckoutCartRequest.class);
            Record lastRecord = new Record();
            lastRecord.setAlias(null);
            while (true) {
                CheckoutCartRequest checkoutCartRequest = new CheckoutCartRequest(request.getAlias(),lastRecord, request.getAuthToken());
                ClearCartResponse response = service.checkoutCart(checkoutCartRequest);
                if (!response.isSuccess()) {
                    throw new RuntimeException("500");
                }
                PostBatchRequest batch = new PostBatchRequest(response.getRecordss(), request.getAuthToken());
                SendMessageRequest sendMessageRequest = new SendMessageRequest()
                        .withQueueUrl(queueURL)
                        .withMessageBody(JsonSerializer.serialize(batch));
                AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
                SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
                System.out.println(sendMessageResult.getMessageId());
                if (!response.getHasMorePages()) {
                    break;
                }
                try {
                    lastRecord = response.getRecordss().get(page_size-1);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
        return null;
    }
}
