package TwitterGMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

public class DataHelper {

	private AmazonDynamoDBClient dynamoDB;

	public DataHelper() {
		
		dynamoDB = new AmazonDynamoDBClient(new AWSCredentialsProviderChain(
	            new InstanceProfileCredentialsProvider(),
	            new ClasspathPropertiesFileCredentialsProvider()));
		Region usEast1 = Region.getRegion(Regions.US_EAST_1);
		dynamoDB.setRegion(usEast1); 
	}

	public ArrayList<Tweets> getAllData() {
		String tableName = "tweets";
		HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
		ScanRequest scanRequest = new ScanRequest(tableName)
				.withScanFilter(scanFilter);
		ScanResult scanResult = dynamoDB.scan(scanRequest);
		ArrayList<Tweets> twitList = new ArrayList<Tweets>();
		List<Map<String, AttributeValue>> resultList = scanResult.getItems();
		for (Map<String, AttributeValue> res : resultList) {
			Tweets twit = new Tweets();
			twit.setId(res.get("tweetId").getN());
			twit.setKeyword(res.get("keyword").getS());
			twit.setLatitude(res.get("latitude").getN());
			twit.setLongtitude(res.get("longtitude").getN());
			String text = res.get("text").getS();
			twit.setText(text);
			twit.setTimestamp(res.get("createAt").getS());
			twit.setUrl(res.get("url").getS());
			String username =  res.get("username").getS();
			twit.setUsername(username);
			String sentiment = res.get("sentiment").getS();
			twit.setSentiment(sentiment);
			twitList.add(twit);
		}

		return twitList;
	}
}

