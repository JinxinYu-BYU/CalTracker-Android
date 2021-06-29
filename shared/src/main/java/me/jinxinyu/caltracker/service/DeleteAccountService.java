package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.DeleteAccountRequest;
import me.jinxinyu.caltracker.service.response.DeleteAccountResponse;

import java.io.IOException;

public interface DeleteAccountService {

    public DeleteAccountResponse delete(DeleteAccountRequest request);
}
