package me.jinxinyu.caltracker.dao;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.util.HashMap;
import java.util.Map;

public class AuthsDAO {

    private static final String TABLE_NAME = "cal_auth";
    private static final String TIMESTAMP_ATTR = "ms_time";
    private static final String TOKEN_ATTR = "authtoken";
    private static final String ALIAS_ATTR ="alias";

    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-east-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public void addToken(String token, long timestamp, String alias) {
        Table table = dynamoDB.getTable(TABLE_NAME);

        Item item = new Item()
                .withPrimaryKey(TOKEN_ATTR, token)
                .withString(ALIAS_ATTR, alias)
                .withNumber(TIMESTAMP_ATTR, timestamp);
        table.putItem(item);
    }

    public Map<String, String> getToken(String token) {
        Table table = dynamoDB.getTable(TABLE_NAME);
        Item item = table.getItem(TOKEN_ATTR, token);
        if (item == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put(ALIAS_ATTR, item.getString(ALIAS_ATTR));
        map.put(TIMESTAMP_ATTR, item.getString(TIMESTAMP_ATTR));
        return map;
    }

    public void deleteToken(String token) {
        Table table = dynamoDB.getTable(TABLE_NAME);
        table.deleteItem(TOKEN_ATTR, token);
    }
}
