package Reference;

import java.util.HashMap;
import java.util.Map;

import TwitterGMap.Tweets;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;

public class UpdateItem {
	static AmazonDynamoDBClient client;
	
	public static void main(String[] args) {
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
		client = new AmazonDynamoDBClient(credentials);
		Region usEast1 = Region.getRegion(Regions.US_EAST_1);
		client.setRegion(usEast1);
		Condition scanFilterCondition = new Condition()
			.withComparisonOperator(ComparisonOperator.EQ.toString())
			.withAttributeValueList(new AttributeValue().withS("Big D"));
		Map<String, Condition> conditions = new HashMap<String, Condition>();
		conditions.put("username", scanFilterCondition);

		ScanRequest scanRequest = new ScanRequest()
			.withTableName("tweets")
			.withScanFilter(conditions);
		ScanResult result = client.scan(scanRequest);
		System.out.println(result.getItems());
		for (int i = 0; i < result.getCount(); i++) {
			HashMap<String, AttributeValue> item = (HashMap<String, AttributeValue>) result
					.getItems().get(i);
			Tweets tweet = new Tweets();
			tweet.setUsername(item.get("username").getS());
			
			UpdateItemResult uresult = client.updateItem(new UpdateItemRequest()
            .withTableName("tweets")
            .withReturnValues(ReturnValue.ALL_NEW)
            .addKeyEntry("username", new AttributeValue().withS(item.get("username").getS()))
            .addAttributeUpdatesEntry(
                 "sentiment", new AttributeValueUpdate()
                     .withValue(new AttributeValue().withS("positive"))
                     .withAction(AttributeAction.PUT)));
			
		}
	}
}
