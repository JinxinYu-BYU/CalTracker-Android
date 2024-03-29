package me.jinxinyu.caltracker.dao;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import me.jinxinyu.caltracker.domain.User;
import me.jinxinyu.caltracker.net.DBRemoteException;
import me.jinxinyu.caltracker.service.request.LoginRequest;
import me.jinxinyu.caltracker.service.request.RegisterRequest;
import me.jinxinyu.caltracker.service.response.DeleteAccountResponse;
import me.jinxinyu.caltracker.service.response.LoginResponse;
import me.jinxinyu.caltracker.service.response.RegisterResponse;

/**
 * Representation of user model
 *
 * @author Valentine Wu
 */
public class UserDAO {

    /** Table Name for DynamoDB */
    private static final String TABLE_NAME = "cal_users";

    /** Primary table key and required attribute */
    private static final String HANDLE_ATTR = "alias";

    /** Optional table attribtues */
    private static final String FNAME_ATTR = "first_name";
    private static final String LNAME_ATTR = "last_name";
    private static final String IMAGE_ATTR = "image_url";
    private static final String PASSWORD_ATTR = "password";
    private static final String HEIGHT_ATTR = "height";
    private static final String WEIGHT_ATTR = "weight";
    private static final String AGE_ATTR = "age";

    /** Build DynamoDB client */
    private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion("us-east-2")
            .build();
    private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

    private static final Table TABLE = dynamoDB.getTable(TABLE_NAME);

    /**
     * User Login Function
     */
    public static LoginResponse login(LoginRequest request) throws DBRemoteException {
        try {
            Item item = TABLE.getItem(HANDLE_ATTR, request.getUsername());
            if (item == null) return new LoginResponse("Invalid username");

            // Verify password
            if (!item.getString(PASSWORD_ATTR).equals(request.getPassword())) {
                return new LoginResponse("Invalid password");
            }

            String alias = item.getString(HANDLE_ATTR);
            String firstName = item.getString(FNAME_ATTR);
            String lastName = item.getString(LNAME_ATTR);
            String imageUrl = item.getString(IMAGE_ATTR);
            int weight = item.getInt(WEIGHT_ATTR);
            int height = item.getInt(HEIGHT_ATTR);
            int age = item.getInt(AGE_ATTR);

            return new LoginResponse(new User(firstName, lastName,
                    alias, imageUrl, weight, height, age), "token");
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }

    }

    public RegisterResponse register(RegisterRequest request) throws DBRemoteException {
        try {

            // Check if username has already been taken
            if (TABLE.getItem(HANDLE_ATTR, request.getUserName()) != null) {
                return new RegisterResponse("Username already exists");
            }

            Item account = new Item()
                    .withPrimaryKey(HANDLE_ATTR, request.getUserName())
                    .withString(FNAME_ATTR, request.getFirstName())
                    .withString(LNAME_ATTR, request.getLastName())
                    .withString(PASSWORD_ATTR, request.getPassword())
                    .withString(IMAGE_ATTR, request.getImageUrl())
                    .withInt(WEIGHT_ATTR, request.getWeight())
                    .withInt(HEIGHT_ATTR, request.getHeight())
                    .withInt(AGE_ATTR, request.getAge());
            TABLE.putItem(account);

            User user = new User(request.getFirstName(), request.getLastName(), request.getUserName(),
                    request.getImageUrl(), request.getWeight(), request.getHeight(), request.getAge());

            return new RegisterResponse(user, "token");
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }
    }

    // May need modifications
    public DeleteAccountResponse delete(User user) throws DBRemoteException {
        try {
            TABLE.deleteItem(HANDLE_ATTR, user.getAlias());
            return new DeleteAccountResponse();
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }
    }

    public static boolean checkUser(String username) throws DBRemoteException {
        try {
            return TABLE.getItem(HANDLE_ATTR, username) == null ? false : true;
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }
    }

    public static void resetPassword(String alias, String password) throws DBRemoteException {
        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(HANDLE_ATTR, alias)
                    .withUpdateExpression("set password = :r")
                    .withValueMap(new ValueMap().withString(":r", password))
                    .withReturnValues(ReturnValue.UPDATED_NEW);
            UpdateItemOutcome outcome = TABLE.updateItem(updateItemSpec);
        } catch (AmazonServiceException ase) {
            throw new DBRemoteException(ase.getMessage(), "Service Exception: " + ase.getErrorType());
        } catch (AmazonClientException ace) {
            throw new DBRemoteException(ace.getMessage(), "Client Exception");
        }
    }





}
