package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.domain.User;
import me.jinxinyu.caltracker.service.request.LogoutRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        LogoutHandler logoutHandler = new LogoutHandler();
        LogoutRequest logoutRequest = new LogoutRequest(new User("user0", "m", "m", "m"), "abcd");
        logoutHandler.handleRequest(logoutRequest, null);
    }
}