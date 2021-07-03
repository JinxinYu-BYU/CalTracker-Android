package me.jinxinyu.caltracker.dao;

import me.jinxinyu.caltracker.net.DBRemoteException;

import java.util.Map;

public class ValidationTokenDAO {
    private static final String TABLE_NAME= "cal_vali";

    public void addToken(String token, long timestamp, String alias) throws DBRemoteException {
        TokenDAO.addToken(token, timestamp, alias, TABLE_NAME);
    }

    public Map.Entry<String, String> getToken(String token) throws DBRemoteException {
        return TokenDAO.getToken(token, TABLE_NAME);
    }

    public void deleteToken(String token) throws DBRemoteException {
        TokenDAO.deleteToken(token, TABLE_NAME);
    }
}
