package me.jinxinyu.caltracker.lambda;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.net.JsonSerializer;
import me.jinxinyu.caltracker.service.request.CheckoutCartRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutCartHandlerTest {
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
//        CheckoutCartRequest checkoutCartRequest = new CheckoutCartRequest("userId", "01234");
//        String json = JsonSerializer.serialize(checkoutCartRequest);
//        System.out.println(json);
//        CheckoutCartHandler checkoutCartHandler = new CheckoutCartHandler();
//        checkoutCartHandler.handleRequest(checkoutCartRequest, context);
        Record record =  new Record("user", "item0", 0, 0 );
        RecordRequest recordRequest = new RecordRequest(record, "abcd", "cart");
        DeleteRecordHandler deleteRecordHandler = new DeleteRecordHandler();
        deleteRecordHandler.handleRequest(recordRequest, context);

    }
}