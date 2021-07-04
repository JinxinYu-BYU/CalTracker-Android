package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.VerifyRegisterCodeRequest;
import me.jinxinyu.caltracker.service.response.VerifyRegisterCodeResponse;

public interface VerifyRegisterCodeService {

    VerifyRegisterCodeResponse verifyRegisterCode(VerifyRegisterCodeRequest request);
}
