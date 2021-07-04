package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.net.JsonSerializer;
import me.jinxinyu.caltracker.service.request.GetTimedRecordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetRecordsBetweenTimeHandlerTest {
    private Context context;


    @BeforeEach
    void setUp() {
        context = new Context() {
            @Override
            public String getAwsRequestId() {
                return null;
            }

            @Override
            public String getLogGroupName() {
                return null;
            }

            @Override
            public String getLogStreamName() {
                return null;
            }

            @Override
            public String getFunctionName() {
                return null;
            }

            @Override
            public String getFunctionVersion() {
                return null;
            }

            @Override
            public String getInvokedFunctionArn() {
                return null;
            }

            @Override
            public CognitoIdentity getIdentity() {
                return null;
            }

            @Override
            public ClientContext getClientContext() {
                return null;
            }

            @Override
            public int getRemainingTimeInMillis() {
                return 0;
            }

            @Override
            public int getMemoryLimitInMB() {
                return 0;
            }

            @Override
            public LambdaLogger getLogger() {
                return null;
            }
        };
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        GetTimedRecordRequest getTimedRecordRequest = new GetTimedRecordRequest();
        Record lastRecord = new Record("user0",null, 0,51);
        getTimedRecordRequest = new GetTimedRecordRequest(lastRecord,"user0", 25, 5, 50, "abcdef");
        String json = JsonSerializer.serialize(getTimedRecordRequest);
        System.out.println(json);
        GetRecordsBetweenTimeHandler getRecordsBetweenTimeHandler = new GetRecordsBetweenTimeHandler();
        getRecordsBetweenTimeHandler.handleRequest(getTimedRecordRequest,context);
    }
}