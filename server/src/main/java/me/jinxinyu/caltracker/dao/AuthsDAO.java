package me.jinxinyu.caltracker.dao;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import me.jinxinyu.caltracker.net.DBRemoteException;

import java.util.Map;

public class AuthsDAO {

    private static final String TABLE_NAME= "cal_auth";

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
