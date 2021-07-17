package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.service.ServiceImpl;
import me.jinxinyu.caltracker.service.request.ResetPasswordRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResetPasswordHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest("siriusyu@live.com", "803710", "abcdefg", "abcdefg");
        ResetPasswordHandler resetPasswordHandler = new ResetPasswordHandler();
        resetPasswordHandler.handleRequest(resetPasswordRequest, null);
        String password = ServiceImpl.generateHash("abcdefg");
        System.out.println(password);
    }
}