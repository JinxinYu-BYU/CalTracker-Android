package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.service.request.VerifyRegisterCodeRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyRegisterCodeHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        VerifyRegisterCodeRequest verifyRegisterCodeRequest = new VerifyRegisterCodeRequest("siriusyu@live.com", "693456");
        VerifyRegisterCodeHandler verifyRegisterCodeHandler = new VerifyRegisterCodeHandler();
        verifyRegisterCodeHandler.handleRequest(verifyRegisterCodeRequest, null);
    }
}