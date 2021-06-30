package me.jinxinyu.caltracker.service;



import java.io.IOException;

import me.jinxinyu.caltracker.service.request.RegisterRequest;
import me.jinxinyu.caltracker.service.response.RegisterResponse;

public interface RegisterService {
    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    RegisterResponse register(RegisterRequest request) throws IOException;
}