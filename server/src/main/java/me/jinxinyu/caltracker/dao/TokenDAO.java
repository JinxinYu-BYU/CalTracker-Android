package me.jinxinyu.caltracker.dao;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import me.jinxinyu.caltracker.net.DBRemoteException;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * A general DB utility class for interating data with token related tables.
 */
public class TokenDAO {

    private static final String TS_ATTR = "ms_time";
    private static final String TOKEN_ATTR = "authtoken";
    private static final String ALIAS_ATTR = "alias";

    /** DynamoDB service initialization */
    private static final AmazonDynamoDB DB_SERVICE = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-east-2")
            .build();
    private static DynamoDB DYNAMO_DB = new DynamoDB(DB_SERVICE);

    /**
     * Add a new auth token into DB
     * @param token random generated auth token
     * @param timestamp in milliseconds
     * @param tableName dynamoDB table name
     */
    static void addToken(String token, long timestamp, String alias, String tableName) throws DBRemoteException {
        Table table = DYNAMO_DB.getTable(tableName);
        try {
            Item item = new Item()
                    .withPrimaryKey(TOKEN_ATTR, token)
                    .withString(ALIAS_ATTR, alias)
                    .withNumber(TS_ATTR, timestamp);
            table.putItem(item);
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }
    }

    /**
     * Get the generation time of the token
     * @param token user auth token
     * @param tableName dynamoDB table name
     * @return user alias mapped with the timestamp of auth token in map.entry format
     */
    static Map.Entry<String, String> getToken(String token, String tableName) throws DBRemoteException {
        try {
            Table table = DYNAMO_DB.getTable(tableName);
            Item item = table.getItem(TOKEN_ATTR, token);
            if (item == null) {
                return null;
            }
            return new AbstractMap.SimpleEntry<>(item.getString(ALIAS_ATTR), item.getString(TS_ATTR));
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }

    }

    /**
     * Delete the designated token in DB
     */
    static void deleteToken(String token, String tableName) throws DBRemoteException {
        try {
            Table table = DYNAMO_DB.getTable(tableName);
            table.deleteItem(TOKEN_ATTR, token);
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }
    }
}
