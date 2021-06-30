package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.LogoutRequest;
import me.jinxinyu.caltracker.service.response.LogoutResponse;

import java.io.IOException;

public interface LogoutService {

    /**
     * Returns the successfully logged out user or a failure message.
     *
     * @param request contains the data required to fulfill the request.
     * @return the logout response.
     */
    LogoutResponse logout(LogoutRequest request) throws IOException;

}
