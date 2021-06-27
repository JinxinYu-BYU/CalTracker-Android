package me.jinxinyu.caltracker.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.AddTrackRequest;
import me.jinxinyu.caltracker.service.response.AddTrackResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackDAO {
    private static final String TableName = "cal_track";

    private static final String HandleAttr = "alias";
    private static final String TimestampAttr = "ms_time";
    private static final String FNameAttr = "food_name";
    private static final String CalAttr = "calories";
    private static final String ImageAttr = "image_url";



    // DynamoDB client
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-east-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    public AddTrackResponse addTrack(AddTrackRequest request) {
        Table table = dynamoDB.getTable(TableName);
        Record record = request.getRecord();
        Item item = new Item()
                .withPrimaryKey(HandleAttr, record.getUserId())
                .withString(FNameAttr, record.getFoodName())
                .withNumber(CalAttr, record.getCalories())
                .withNumber(TimestampAttr, record.getTime());
        table.putItem(item);

        return new AddTrackResponse(true);
    }

//    public StoryResponse getStory(StoryRequest request) {
//        Map<String, String> attrNames = new HashMap<>();
//        attrNames.put("#handle", HandleAttr);
//
//        Map<String, AttributeValue> attrValues = new HashMap<>();
//        attrValues.put(":alias", new AttributeValue().withS(request.getAlias()));
//
//        QueryRequest queryRequest = new QueryRequest()
//                .withTableName(TableName)
//                .withKeyConditionExpression("#handle = :alias")
//                .withExpressionAttributeNames(attrNames)
//                .withExpressionAttributeValues(attrValues)
//                .withLimit(request.getLimit());
//
//        if (request.getLastStatus() != null) {
//            Map<String, AttributeValue> startKey = new HashMap<>();
//            startKey.put(HandleAttr, new AttributeValue().withS(request.getAlias()));
//            startKey.put(TimestampAttr, new AttributeValue().withN(String.valueOf(request.getLastStatus().getLocalTimeDate())));
//
//            queryRequest = queryRequest.withExclusiveStartKey(startKey);
//        }
//
//        List<Status> tweets = new ArrayList<>();
//        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
//        List<Map<String, AttributeValue>> items = queryResult.getItems();
//        if (items != null) {
//            for (Map<String, AttributeValue> item: items) {
//                String alias = item.get(HandleAttr).getS();
//                long timestamp = Long.parseLong(item.get(TimestampAttr).getN());
//                String content = item.get(ContentAttr).getS();
//                String first_name = item.get(FNameAttr).getS();
//                String last_name = item.get(LNameAttr).getS();
//                String image_url = item.get(ImageAttr).getS();
//
//                User author = new User(first_name, last_name, alias, image_url);
//                tweets.add(new Status(content, timestamp, author));
//            }
//        }
//
//        boolean hasMore = false;
//        Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
//        if (lastKey != null) {
//            hasMore = true;
//        }
//
//        return new StoryResponse(tweets, hasMore);
//    }
}
