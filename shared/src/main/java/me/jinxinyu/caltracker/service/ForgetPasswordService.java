package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.ForgetPasswordRequest;
import me.jinxinyu.caltracker.service.request.LogoutRequest;
import me.jinxinyu.caltracker.service.response.ForgetPasswordResponse;

import java.io.IOException;

public interface ForgetPasswordService {

    /**
     * Returns the successfully forget password service or a failure message.
     *
     * @param request contains the data required to fulfill the request.
     * @return the logout response.
     */
    ForgetPasswordResponse forgetPassword(ForgetPasswordRequest request);

}
