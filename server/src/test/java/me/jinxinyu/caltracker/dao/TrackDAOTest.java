package me.jinxinyu.caltracker.dao;

import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackDAOTest {
//        void populateTrack(){
//        CartDAO cartDAO = new CartDAO();
//        for(int i = 10; i<30; i ++){
//            Record record = new Record("user2", "item"+i, i, i);
//            RecordRequest recordRequest = new RecordRequest(record, "0123");
//            cartDAO.addCart(recordRequest);
//        }
//
//    }


    @Test
    void addTrack() {
        TrackDAO trackDAO = new TrackDAO();
        for(int i = 0; i<100; i ++){
            Record record = new Record("user0", "item"+i, i, i);
            RecordRequest addTrackRequest = new RecordRequest(record, "abcde", "track");
            Response addTrackResponse = trackDAO.addRecord(addTrackRequest);
            assertTrue(addTrackResponse.getSuccess());
        }
//        Record record = new Record("abc123", "Apple", 132, new Timestamp(System.currentTimeMillis()).getTime());
//        RecordRequest addTrackRequest = new RecordRequest(record, "abcd", "track");

    }
}