package me.jinxinyu.caltracker.dao;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import me.jinxinyu.caltracker.domain.Record;
import me.jinxinyu.caltracker.service.request.RecordRequest;


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
}
