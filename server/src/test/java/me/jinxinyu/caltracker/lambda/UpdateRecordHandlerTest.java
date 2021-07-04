package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.UpdateRecordService;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRecordHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        Record record = new Record("user0", "banana", 10, 123456);
        List<RecordRequest> list = Arrays.asList(new RecordRequest(record, "abcdef", "fav"), new RecordRequest(record, "abcdef", "cart"), new RecordRequest(record, "abcdef", "track"));
        UpdateRecordHandler updateRecordHandler = new UpdateRecordHandler();
        for(RecordRequest recordRequest: list){
            updateRecordHandler.handleRequest(recordRequest, null);
        }
//        updateRecordHandler.handleRequest(new RecordRequest(record, "abcdef", "fav"), null);
    }
}