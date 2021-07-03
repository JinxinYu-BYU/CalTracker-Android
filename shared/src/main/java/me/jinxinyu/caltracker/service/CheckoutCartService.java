package me.jinxinyu.caltracker.service;

import me.jinxinyu.caltracker.service.request.CheckoutCartRequest;
import me.jinxinyu.caltracker.service.response.ClearCartResponse;
import me.jinxinyu.caltracker.service.response.Response;

public interface CheckoutCartService {
    ClearCartResponse checkoutCart(CheckoutCartRequest checkoutCartRequest);
}
