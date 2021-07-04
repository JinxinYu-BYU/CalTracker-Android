package me.jinxinyu.caltracker.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.CheckoutCartRequest;
import me.jinxinyu.caltracker.service.request.GetRecordsRequest;
import me.jinxinyu.caltracker.service.request.RecordRequest;
import me.jinxinyu.caltracker.service.response.ClearCartResponse;
import me.jinxinyu.caltracker.service.response.GetRecordsResponse;
import me.jinxinyu.caltracker.service.response.Response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDAO{
    private static String TABLE_NAME = "cal_cart";
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

    private Table table = dynamoDB.getTable(TABLE_NAME);

    public static Response addRecord(RecordRequest request) {
        return DAO.addRecord(TABLE_NAME, request);
    }

    public Response updateRecords(RecordRequest request){
        return DAO.updateRecord(TABLE_NAME, request);
    }

    public Response deleteRecord(RecordRequest request){
        return DAO.deleteRecord(TABLE_NAME, request);
    }

    public ClearCartResponse  checkoutCart(CheckoutCartRequest checkoutCartRequest){
        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("alias = :v_id")
                .withValueMap(new ValueMap()
                        .withString(":v_id", checkoutCartRequest.getAlias()))
                .withMaxResultSize(25)
                .withConsistentRead(true)
                .withExclusiveStartKey(HANDLE_ATTR, checkoutCartRequest.getAlias(), TIME_ATTR, checkoutCartRequest.getLastRecord().getTime());

        ItemCollection<QueryOutcome> items = table.query(spec);

        List<Record> records = new ArrayList<>();
        for(Item item:items){
            String alias = item.getString(HANDLE_ATTR);
            long ms_time = new Timestamp(System.currentTimeMillis()).getTime();
            String foodName = item.getString(FOOD_NAME_ATTR);
            int calories = item.getNumber(CAL_ATTR).byteValueExact();
            String imageURL = item.getString(IMAGE_ATTR);
            Record record = new Record(alias, foodName, calories, ms_time, imageURL);
            records.add(record);
            DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey(HANDLE_ATTR, record.getAlias(), TIME_ATTR, record.getTime());
            try {
                System.out.println("Attempting a conditional delete...");
                table.deleteItem(deleteItemSpec);
                System.out.println("DeleteItem succeeded");
            }
            catch (Exception e) {
                System.err.println("Unable to delete item: " + record.getAlias() + " " + record.getTime());
                System.err.println(e.getMessage());
            }
        }
        boolean hasMore = false;
        try{
            Map<String, AttributeValue> lastKey = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
            if (lastKey != null) {
                hasMore = true;
            }
        }catch (Exception ignored){

        }


        return new ClearCartResponse(records,hasMore);


    }

    public GetRecordsResponse getRecords(GetRecordsRequest request) {
        return DAO.getRecords(TABLE_NAME, request);
    }


}
