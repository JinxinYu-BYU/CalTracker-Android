package me.jinxinyu.caltracker.dao;

import me.jinxinyu.caltracker.net.DBRemoteException;

import java.util.Map;
import java.util.Random;

public class ValidationTokenDAO {
    private static final String TABLE_NAME= "cal_vali";

    private static final Random RAND = new Random();
    private static final int MAX_NUMBER = 999999;

    public void addToken(String token, long timestamp, String alias) throws DBRemoteException {
        TokenDAO.addToken(token, timestamp, alias, TABLE_NAME);
    }

    public Map.Entry<String, String> getToken(String token) throws DBRemoteException {
        return TokenDAO.getToken(token, TABLE_NAME);
    }

    public void deleteToken(String token) throws DBRemoteException {
        TokenDAO.deleteToken(token, TABLE_NAME);
    }

    public String getRandomToken() throws DBRemoteException {
        while (true) {
            String valiToken = String.format("%06d", RAND.nextInt(MAX_NUMBER));
            if (getToken(valiToken) == null) {
                return valiToken;
            }
        }
    }
}
