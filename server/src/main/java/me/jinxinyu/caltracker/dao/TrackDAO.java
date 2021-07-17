package me.jinxinyu.caltracker.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.request.GetTimedRecordRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;
import me.jinxinyu.caltracker.service.response.Response;

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
    private Table table = dynamoDB.getTable(TABLE_NAME);

    public static Response addRecord(RecordRequest request) {
        return DAO.addRecord("cal_track", request);
    }

    public GetRecordsResponse getRecords(GetRecordsRequest request) {
        return DAO.getRecords(TABLE_NAME, request);
    }

    public GetRecordsResponse getRecordsBetweenTime(GetTimedRecordRequest recordRequest){
        Map<String, AttributeValue> startKey = new HashMap<>();
        startKey.put("authtoken", new AttributeValue().withS(recordRequest.getAlias()));
        startKey.put("ms_time", new AttributeValue().withN(String.valueOf(recordRequest.getLastRecord().getTime())));

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("alias = :v_id and ms_time BETWEEN :v AND :e")
                .withValueMap(new ValueMap()
                        .withString(":v_id", recordRequest.getAlias())
                        .withNumber(":v", recordRequest.getTimeStart())
                        .withNumber(":e", recordRequest.getTimeEnd()))
                .withMaxResultSize(recordRequest.getLimit())
                .withConsistentRead(true)
                .withExclusiveStartKey(HANDLE_ATTR, recordRequest.getAlias(), TIME_ATTR, recordRequest.getLastRecord().getTime());

        ItemCollection<QueryOutcome> items = table.query(spec);
        List<Record> records = new ArrayList<>();
        for(Item item:items){
            String alias = item.getString(HANDLE_ATTR);
            long ms_time = Long.parseLong(item.getString(TIME_ATTR));
            String foodName = item.getString(FOOD_NAME_ATTR);
            int calories = item.getNumber(CAL_ATTR).byteValueExact();
            String imageURL = item.getString(IMAGE_ATTR);
            Record record = new Record(alias, foodName, calories, ms_time, imageURL);
            records.add(record);
        }
        boolean hasMore = false;
        Map<String, AttributeValue> lastKey = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
        if (lastKey != null) {
            hasMore = true;
        }
        //TODO: add a try catch for exceptions
        return new GetRecordsResponse(true, "Successfully get timed records", hasMore, records);

    }

    public Response updateTrack(RecordRequest request){
        return DAO.updateRecord(TABLE_NAME, request);

    }

    public Response deleteRecord(RecordRequest request){
        return DAO.deleteRecord(TABLE_NAME, request);
    }

    public static void addRecordBatch(List<Record> records) {

        TableWriteItems items = new TableWriteItems(TABLE_NAME);
        for (Record record : records) {
            Item item = new Item()
                    .withPrimaryKey(HANDLE_ATTR, record.getAlias())
                    .withString(FOOD_NAME_ATTR, record.getFoodName())
                    .withNumber(CAL_ATTR, record.getCalories())
                    .withNumber(TIME_ATTR, record.getTime());
            items.addItemToPut(item);


            if (items.getItemsToPut() != null && items.getItemsToPut().size() == 25) {
                loopBatchWrite(items);
                items = new TableWriteItems(TABLE_NAME);
            }
        }

        if (items.getItemsToPut() != null && items.getItemsToPut().size() > 0) {
            loopBatchWrite(items);
        }
    }


    private static void loopBatchWrite(TableWriteItems items) {

        BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(items);

        while (outcome.getUnprocessedItems().size() > 0) {
            Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();
            outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
        }
    }
}
