package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FavAddHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        Record record = new Record("user0", "chese", 0, 1234567);
        List<RecordRequest> list = Arrays.asList(new RecordRequest(record, "abcdef", "fav"), new RecordRequest(record, "abcdef", "cart"), new RecordRequest(record, "abcdef", "track"));
        FavAddHandler addRecordHandler = new FavAddHandler();
        for(RecordRequest recordRequest: list){
            addRecordHandler.handleRequest(recordRequest, null);
        }
    }
}