package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.VerifyEmailRequest;
import me.jinxinyu.caltracker.service.response.VerifyEmailResponse;

public interface VerifyEmailService {

    VerifyEmailResponse verifyEmail(VerifyEmailRequest request);
}
