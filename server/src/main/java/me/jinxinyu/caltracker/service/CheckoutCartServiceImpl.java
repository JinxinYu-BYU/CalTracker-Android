package me.jinxinyu.caltracker.service;


import me.jinxinyu.caltracker.dao.CartDAO;
import me.jinxinyu.caltracker.service.request.CheckoutCartRequest;
import me.jinxinyu.caltracker.service.response.ClearCartResponse;
import me.jinxinyu.caltracker.service.response.Response;

public class CheckoutCartServiceImpl extends ServiceImpl implements CheckoutCartService {
    private CartDAO getCartDAO(){
        return new CartDAO();
    }

    @Override
    public ClearCartResponse checkoutCart(CheckoutCartRequest checkoutCartRequest) {
        String newToken = validateToken(checkoutCartRequest.getAuthToken(), checkoutCartRequest.getAlias());
        if(newToken != null){
            return new ClearCartResponse(getCartDAO().checkoutCart(checkoutCartRequest), newToken);
        }else {
            return new ClearCartResponse(false, "invalid token");
        }
    }
}
