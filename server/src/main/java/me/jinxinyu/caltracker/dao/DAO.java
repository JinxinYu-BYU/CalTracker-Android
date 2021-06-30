package me.jinxinyu.caltracker.dao;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.CheckoutCartRequest;
import me.jinxinyu.caltracker.service.request.GetRecordRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.GetRecordResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DAO {
    private static final String HANDLE_ATTR = "alias";
    private static final String TIME_ATTR = "ms_time";
    private static final String FOOD_NAME_ATTR = "food_name";
    private static final String CAL_ATTR = "calories";
    private static final String IMAGE_ATTR = "image_url";

    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-east-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public static boolean addRecord(String tableName, RecordRequest request) {
        Table table = dynamoDB.getTable(tableName);
        Record record = request.getRecord();
        Item item = new Item()
                .withPrimaryKey(HANDLE_ATTR, record.getAlias())
                .withString(FOOD_NAME_ATTR, record.getFoodName())
                .withNumber(CAL_ATTR, record.getCalories())
                .withNumber(TIME_ATTR, record.getTime());
        table.putItem(item);

        return true;
    }

    public static void updateRecord(String tableName, RecordRequest request){
        Table table = dynamoDB.getTable(tableName);
        //TODO can update all attributes if needed
        UpdateItemSpec updateItemSpecFollower = new UpdateItemSpec().withPrimaryKey(HANDLE_ATTR, request.getRecord().getAlias())
                .withUpdateExpression("set food_name :r, calories :e")
                .withValueMap(new ValueMap().withString(":r", request.getRecord().getFoodName()).withNumber(":e", request.getRecord().getCalories()))
                .withReturnValues(ReturnValue.UPDATED_NEW);

        try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = table.updateItem(updateItemSpecFollower);

        }
        catch (Exception e) {
            System.err.println("Unable to update " + request.getRecord().getAlias() + "'s record");
            System.err.println(e.getMessage());
        }

    }

    public static void deleteRecord(String tableName, RecordRequest request) {
        Table table = dynamoDB.getTable(tableName);
        table.deleteItem(HANDLE_ATTR, request.getRecord().getAlias(), TIME_ATTR, request.getRecord().getTime());
    }

    public static GetRecordResponse getRecords(String tableName, GetRecordRequest request) {
        Map<String, String> attrNames = new HashMap<>();
        attrNames.put("#handle", HANDLE_ATTR);

        Map<String, AttributeValue> attrValues = new HashMap<>();
        attrValues.put(":alias", new AttributeValue().withS(request.getUserId()));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(tableName)
                .withKeyConditionExpression("#handle = :userId")
                .withExpressionAttributeNames(attrNames)
                .withExpressionAttributeValues(attrValues)
                .withLimit(request.getLimit());

        if (request.getLastRecord() != null) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put(HANDLE_ATTR, new AttributeValue().withS(request.getUserId()));
            startKey.put(TIME_ATTR, new AttributeValue().withN(String.valueOf(request.getLastRecord().getTime())));

            queryRequest = queryRequest.withExclusiveStartKey(startKey);
        }

        List<Record> records = new ArrayList<>();
        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if (items != null) {
            for (Map<String, AttributeValue> item: items) {
                String userId = item.get(HANDLE_ATTR).getS();
                long timestamp = Long.parseLong(item.get(TIME_ATTR).getN());
                String foodName = item.get(FOOD_NAME_ATTR).getS();
                int calories = Integer.parseInt(item.get(CAL_ATTR).getN());
                // TODO: verify when it's null in the DB
                String image_url = item.get(IMAGE_ATTR).getS();

                records.add(new Record(userId, foodName,  calories, timestamp, image_url));
            }
        }

        boolean hasMore = false;
        Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
        if (lastKey != null) {
            hasMore = true;
        }

        return new GetRecordResponse(records, hasMore);
    }
}
