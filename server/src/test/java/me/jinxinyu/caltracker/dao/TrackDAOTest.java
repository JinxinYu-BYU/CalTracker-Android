package me.jinxinyu.caltracker.dao;

import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;
import me.jinxinyu.caltracker.service.response.RecordResponse;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TrackDAOTest {

    @Test
    void addTrack() {
        TrackDAO trackDAO = new TrackDAO();
        Record record = new Record("abc123", "Apple", 132, new Timestamp(System.currentTimeMillis()).getTime());
        RecordRequest addTrackRequest = new RecordRequest(record, "abcd");
        RecordResponse addTrackResponse = trackDAO.addTrack(addTrackRequest);
        assertTrue(addTrackResponse.isSuccess());
    }
}