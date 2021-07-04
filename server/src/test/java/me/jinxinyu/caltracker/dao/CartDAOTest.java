package me.jinxinyu.caltracker.dao;

import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.net.JsonSerializer;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

class CartDAOTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkoutCart() throws DBRemoteException {
//        CheckoutCartRequest checkoutCartRequest = new CheckoutCartRequest("user", "01234");
//        CartDAO cartDAO = new CartDAO();
//        cartDAO.checkoutCart(checkoutCartRequest);
//        GetRecordsRequest getRecordRequest = new GetRecordsRequest(null, "user", 10);
//        cartDAO.getRecords(getRecordRequest);

        AuthsDAO authsDAO = new AuthsDAO();
        authsDAO.addToken("abcd", new Timestamp(System.currentTimeMillis()).getTime(), "user");
        Record record =  new Record("user", "item0", 0, 0 );
        RecordRequest recordRequest = new RecordRequest(record, "abcd", "cart");
        String json = JsonSerializer.serialize(recordRequest);
        System.out.println(json);

    }
}