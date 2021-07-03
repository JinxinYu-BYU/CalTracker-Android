package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import me.jinxinyu.caltracker.net.JsonSerializer;
import me.jinxinyu.caltracker.service.CheckoutCartServiceImpl;
import me.jinxinyu.caltracker.service.request.CheckoutCartRequest;
import me.jinxinyu.caltracker.service.response.Response;

//This will send the request to the cart queue
public class CheckoutCartHandler implements RequestHandler<CheckoutCartRequest, Void> {
    @Override
    public Void handleRequest(CheckoutCartRequest request, Context context) {
        //TODO: update queueURL
        String queueURL = "https://sqs.us-east-2.amazonaws.com/376992233301/CartQueue";

        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(queueURL)
                    .withMessageBody(JsonSerializer.serialize(request));
            AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
            SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
            System.out.println(sendMessageResult.getMessageId());
        } catch (Exception e) {
            throw new RuntimeException("500");
        }

        return null;

    }
}
