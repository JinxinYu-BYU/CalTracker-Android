package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.ResetPasswordRequest;
import me.jinxinyu.caltracker.service.response.ResetPasswordResponse;

public interface ResetPasswordService {
    ResetPasswordResponse resetPassword(ResetPasswordRequest request);
}
