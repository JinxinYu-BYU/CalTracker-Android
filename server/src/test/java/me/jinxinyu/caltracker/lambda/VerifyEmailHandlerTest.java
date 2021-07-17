package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.service.request.VerifyEmailRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyEmailHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        VerifyEmailRequest verifyEmailRequest = new VerifyEmailRequest("siriusyu@live.com");
        VerifyEmailHandler verifyEmailHandler = new VerifyEmailHandler();
        verifyEmailHandler.handleRequest(verifyEmailRequest, null);
    }
}