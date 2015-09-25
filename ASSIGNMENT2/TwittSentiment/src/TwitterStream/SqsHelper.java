package TwitterStream;

import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SqsHelper {
	protected static AmazonSQS sqs;
	public String myQueueUrl;
	public SqsHelper() {
		AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/daniel/.aws/credentials), and is in valid format.",
                    e);
        }
        sqs = new AmazonSQSClient(credentials);
        Region usEast1 = Region.getRegion(Regions.US_EAST_1);
        sqs.setRegion(usEast1);
	}
	
	public void createSQS(String Qname){
		// Create a queue
        System.out.println("Creating a new SQS queue called MyQueue.\n");
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(Qname);
        myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
	}
	
	public String listSQS(){
		// List queues
		String urls=null;
        //System.out.println("Listing all queues in your account.\n");
        urls = sqs.listQueues().getQueueUrls().get(0);
        /*
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
            System.out.println("  QueueUrl: " + queueUrl);
            urls = queueUrl;
        }
        */
        return urls;
	}
	
	public void sendSQS(String text){
		// Send a message
        System.out.println("Sending a message to MyQueue.\n");
        sqs.sendMessage(new SendMessageRequest(myQueueUrl, text));
	}
	
	public List<Message> receiveSQS(String myUrl){
		// Receive messages
        System.out.println("Receiving messages from MyQueue.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myUrl).withMaxNumberOfMessages(10).withWaitTimeSeconds(20);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        for (Message message : messages) {
            //System.out.println("  Message");
            //System.out.println("    MessageId:     " + message.getMessageId());
            //System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            //System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());
            /*
            for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                System.out.println("  Attribute");
                System.out.println("    Name:  " + entry.getKey());
                System.out.println("    Value: " + entry.getValue());
            }
            */
        }
        System.out.println();
        return messages;
	}
	
}
