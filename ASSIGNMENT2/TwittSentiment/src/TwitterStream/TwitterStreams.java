package TwitterStream;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreams {
    protected static DynamoDBHelper twitterDBhelper = new DynamoDBHelper(); 
    protected static KeyWordprocesser matcherHelper = new KeyWordprocesser();
    protected static SqsHelper sqsHelper = new SqsHelper();
    private static long count = 0;
	
    public static void main(String[] args) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("YZxjh5F1NRviwPNWCu8is1KmM");
        cb.setOAuthConsumerSecret("QQA4cpq5x9zMxfXJKoQQ15NFnz4Ne5tFrahuSTVFQ2YhD0uk9R");
        cb.setOAuthAccessToken("2852791373-MNid1C5U7AuuBaEz907m8iSMw1mfLhWwwc6wtVS");
        cb.setOAuthAccessTokenSecret("1tjpkrHWOkD5R5NzeFdd7VZoIdDhiQ0eyGodc60iclJYZ");
        
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        if(!twitterDBhelper.testAndCheck("tweets")) {
        	System.out.println("There is no existing tweets,create one first");
        	twitterDBhelper.createTable("tweets");        	
        }
        count = twitterDBhelper.findId("tweets") + 1;
        sqsHelper.createSQS("tweets");
        sqsHelper.listSQS();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                GeoLocation gl = status.getGeoLocation();
                	if (gl!=null && status.getUser() != null) {
                		Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
                		item.put("tweetId", new AttributeValue().withN(Long.toString(status.getId())));
                		item.put("username", new AttributeValue().withS(status.getUser().getName()));
                		item.put("text",new AttributeValue().withS(status.getText()));
                		item.put("createAt", new AttributeValue().withS(status.getCreatedAt().toString()));
                		item.put("latitude", new AttributeValue().withN(Double.toString(gl.getLatitude())));
                		item.put("longtitude", new AttributeValue().withN(Double.toString(gl.getLongitude())));
                		item.put("keyword", new AttributeValue().withS(matcherHelper.iskeyword(status.getText())));
                		item.put("url", new AttributeValue().withS(status.getSource()));
                		item.put("sentiment",new AttributeValue().withS("netural"));
                		twitterDBhelper.addItem("tweets", item);
                		sqsHelper.sendSQS(status.getText());
                		count++;
                	}              	
                
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        FilterQuery fq = new FilterQuery();
        String keywords[] = {"Friday","beauty","movie","food","game","win"};
        fq.track(keywords);
        twitterStream.addListener(listener);
        twitterStream.filter(fq);
        //twitterStream.sample();        
	}
}
