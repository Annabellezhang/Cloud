package ThreadPool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Reference.SNSManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import TwitterGMap.Tweets;
import TwitterStream.SqsHelper;

import com.alchemyapi.api.AlchemyAPI;
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
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.amazonaws.services.dynamodbv2.model.UpdateTableRequest;
import com.amazonaws.services.sqs.model.Message;

public class WorkerThread implements Runnable {
	//AmazonDynamoDBClient dynamoDB;
    private String command;
    
    public WorkerThread(String s){
        this.command=s;
    }
 
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        try {
			alchemyapi();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        processCommand();
        System.out.println(" End.");
    }
 
    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public String toString(){
        return this.command;
    }
    
    public void alchemyapi() throws XPathExpressionException, IOException, SAXException, ParserConfigurationException{
    	AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("YOUR-API-KEY");
    	SNSManager sns = new SNSManager();
        //sns.subscribeToATopic("Tweets", "cz574@nyu.edu");
    	SqsHelper sqs = new SqsHelper();
        String qurl = sqs.listSQS();
        List<Message> messages = sqs.receiveSQS(qurl);
        
        for(Message t : messages){
        	Document doc = alchemyObj.TextGetTextSentiment(t.getBody());

            // parse XML result
            String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
            //String score = doc.getElementsByTagName("score").item(0).getTextContent();
            
            sns.publishToATopic("Tweets", sentiment);
            updateDynamo(t.getBody(), sentiment);
            // print results
            System.out.println("Sentiment: " + sentiment + "Text" + t.getBody());
            //System.out.println("Score: " + score);
            
        }
    }
    
    public void updateDynamo(String text, String sentiment) {
    	
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
		AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials);
		Region usEast1 = Region.getRegion(Regions.US_EAST_1);
		client.setRegion(usEast1);
		Condition scanFilterCondition = new Condition()
			.withComparisonOperator(ComparisonOperator.EQ.toString())
			.withAttributeValueList(new AttributeValue().withS(text));
		Map<String, Condition> conditions = new HashMap<String, Condition>();
		conditions.put("text", scanFilterCondition);

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
                     .withValue(new AttributeValue().withS(sentiment))
                     .withAction(AttributeAction.PUT)));
			
		}
    }
}
