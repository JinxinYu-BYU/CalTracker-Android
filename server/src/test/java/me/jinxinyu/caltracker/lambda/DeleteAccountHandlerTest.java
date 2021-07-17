package me.jinxinyu.caltracker.lambda;

import me.jinxinyu.caltracker.dao.AuthsDAO;
import me.jinxinyu.caltracker.domain.User;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.DeleteAccountRequest;
import me.jinxinyu.caltracker.service.request.RegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAccountHandlerTest {

    @BeforeEach
    void setUp() throws DBRemoteException {
//        RegisterRequest r = new RegisterRequest("mike", "wu", "user0", "123", "", 40, 180, 20);
//        RegisterHandler registerHandler = new RegisterHandler();
//        registerHandler.handleRequest(r, null);

//        AuthsDAO authsDAO = new AuthsDAO();
//        authsDAO.addToken("abcd", new Timestamp(System.currentTimeMillis()).getTime(), "user0");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequest() {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(new User("mike", "wu", "user0", "123"), "abcd");
        DeleteAccountHandler deleteAccountHandler = new DeleteAccountHandler();
        deleteAccountHandler.handleRequest(deleteAccountRequest, null);
    }
}