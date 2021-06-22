package me.jinxinyu.caltracker.dao;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class  AuthsDAO {

    private static final String TableName = "cal_auth";
    private static final String TimestampAttr = "time";
    private static final String TokenAttr = "token";



    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-east-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public void addToken(String token, long timestamp) {
        Table table = dynamoDB.getTable(TableName);

        Item item = new Item()
                .withPrimaryKey(TokenAttr, token)
                .withNumber(TimestampAttr, timestamp);
        table.putItem(item);
    }

    public String getToken(String token) {
        Table table = dynamoDB.getTable(TableName);
        Item item = table.getItem(TokenAttr, token);
        if (item == null) {
            return null;
        }

        return item.getString(TimestampAttr);
    }

    public void deleteToken(String token) {
        Table table = dynamoDB.getTable(TableName);
        table.deleteItem(TokenAttr, token);
    }
}
