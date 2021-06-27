package me.jinxinyu.caltracker.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.GetRecordRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;
import me.jinxinyu.caltracker.service.response.GetRecordResponse;
import me.jinxinyu.caltracker.service.response.RecordResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackDAO {
    private static String TABLE_NAME = "cal_track";

    private static final String HANDLE_ATTR = "alias";
    private static final String TIME_ATTR = "ms_time";
    private static final String FOOD_NAME_ATTR = "food_name";
    private static final String CAL_ATTR = "calories";
    private static final String IMAGE_ATTR = "image_url";


    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-east-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public RecordResponse addTrack(RecordRequest request) {
        return new RecordResponse(DAO.addRecord("cal_track", request));
    }

    public GetRecordResponse getRecords(GetRecordRequest request) {
        Map<String, String> attrNames = new HashMap<>();
        attrNames.put("#handle", HANDLE_ATTR);

        Map<String, AttributeValue> attrValues = new HashMap<>();
        attrValues.put(":alias", new AttributeValue().withS(request.getUserId()));

        QueryRequest queryRequest = new QueryRequest()
                .withTableName(TABLE_NAME)
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

    public void updateRecords(RecordRequest request){
       //TODO could return meaningful message if needed
        //dive into the outcome

        DAO.updateRecord(TABLE_NAME, request);

    }

    public void deleteRecord(RecordRequest request){
        DAO.deleteRecord(TABLE_NAME, request);
    }
}
