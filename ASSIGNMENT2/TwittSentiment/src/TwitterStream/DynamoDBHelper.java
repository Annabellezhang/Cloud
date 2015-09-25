package TwitterStream;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.util.Tables;

public class DynamoDBHelper {
	protected static AmazonDynamoDBClient DynamoClient;
	public DynamoDBHelper() {
		AWSCredentials credentials = null;
		try {
			credentials = new ProfileCredentialsProvider("default").getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException(
					"Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/Annabelle/.aws/credentials), and is in valid format.",
                    e);
		}
		DynamoClient = new AmazonDynamoDBClient(credentials);
		Region usEast1 = Region.getRegion(Regions.US_EAST_1);
		DynamoClient.setRegion(usEast1);
	}
	public boolean testAndCheck(String tableName) {
		if(Tables.doesTableExist(DynamoClient, tableName)) {
			return true;
		}
		return false;
	}
	
	public void createTable(String tableName){
		// Create table if it does not exist yet
        if (Tables.doesTableExist(DynamoClient, tableName)) {
            System.out.println("Table " + tableName + " is already ACTIVE");
        } else {
            // Create a table with a primary hash key named 'name', which holds a string
            CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
                .withKeySchema(new KeySchemaElement().withAttributeName("username").withKeyType(KeyType.HASH))
                .withAttributeDefinitions(new AttributeDefinition().withAttributeName("username").withAttributeType(ScalarAttributeType.S))
                .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
                TableDescription createdTableDescription = DynamoClient.createTable(createTableRequest).getTableDescription();
            System.out.println("Created Table: " + createdTableDescription);
            // Wait for it to become active
            System.out.println("Waiting for " + tableName + " to become ACTIVE...");
            Tables.waitForTableToBecomeActive(DynamoClient, tableName);
        }

        // Describe our new table
        DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
        TableDescription tableDescription = DynamoClient.describeTable(describeTableRequest).getTable();
        System.out.println("Table Description: " + tableDescription);       
	}
	
	public long describeTableSize(String tableName){
		DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
        TableDescription tableDescription = DynamoClient.describeTable(describeTableRequest).getTable(); 
        System.out.println("Table Description: " + tableDescription.getTableSizeBytes()); 
        return tableDescription.getTableSizeBytes();
	}
	
	public long describeTableCount(String tableName){
		DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
        TableDescription tableDescription = DynamoClient.describeTable(describeTableRequest).getTable(); 
        System.out.println("Table Description: " + tableDescription.getItemCount()); 
        return tableDescription.getItemCount();
	}
	
	public boolean addItem(String tableName,Map<String, AttributeValue> item) {
		PutItemRequest putItemRequest = new PutItemRequest(tableName,item);
		DynamoClient.putItem(putItemRequest);
		return true;
	}
	
	public ScanResult searchItem(String tableName,Map<String, Condition> scanFilter , ArrayList<String> attribute) {
		ScanRequest scanRequest = new ScanRequest(tableName).withScanFilter(scanFilter);
		ScanResult rs = DynamoClient.scan(scanRequest);
		return rs;
	}
	
	public boolean deleteItem(String tableName, Map<String,AttributeValue> item) {
		DeleteItemRequest deleteItemRequest = new DeleteItemRequest().withTableName(tableName).withKey(item);
		DynamoClient.deleteItem(deleteItemRequest);
		return true;
	}
	
	public long findId(String tableName) {
		long max = 0;
		HashMap<String, Condition> scanfilter = new HashMap<String,Condition>();
		ScanResult sResult = DynamoClient.scan(new ScanRequest(tableName).withScanFilter(scanfilter).withAttributesToGet("id"));
		List<Map<String,AttributeValue>> list = sResult.getItems();
		for (Map<String, AttributeValue> map : list) {
			long temp = Long.parseLong(map.get("id").getN());
			if (temp  > max) {
				max = temp;
			}
		}
		return max;
	}
}

