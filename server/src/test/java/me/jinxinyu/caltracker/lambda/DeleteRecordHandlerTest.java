package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteRecordHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        Record record = new Record("user0", "apple", 0, 123456);
        RecordRequest recordRequest = new RecordRequest(record, "abcdef", "cart");

        DeleteRecordHandler deleteRecordHandler = new DeleteRecordHandler();
        deleteRecordHandler.handleRequest(recordRequest, null);
    }
}