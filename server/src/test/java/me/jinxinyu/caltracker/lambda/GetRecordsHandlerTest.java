package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetRecordsHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        List<GetRecordsRequest>list = Arrays.asList(new GetRecordsRequest(new Record(), "user0", 20, "abcdef", "fav"), new GetRecordsRequest(new Record(), "user0", 20, "abcdef", "track"), new GetRecordsRequest(new Record(), "user0", 20, "abcdef", "cart"));
        GetRecordsHandler getRecordsHandler = new GetRecordsHandler();
        for(GetRecordsRequest getRecordsRequest: list){
            GetRecordsResponse getRecordsResponse = getRecordsHandler.handleRequest(getRecordsRequest, null);
            System.out.println("  ");
        }
    }
}