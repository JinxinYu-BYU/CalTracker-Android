package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.service.request.ForgetPasswordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForgetPasswordHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest("siriusyu@live.com");
        ForgetPasswordHandler forgetPasswordHandler = new ForgetPasswordHandler();
        forgetPasswordHandler.handleRequest(forgetPasswordRequest, null);
    }
}