import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.util.Tables;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class SimpleStream {
	static int count = 10;
	static AmazonDynamoDBClient dynamoDB;
	
	private static void init() throws Exception {
        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (/Users/Annabelle/.aws/credentials).
         */
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
        dynamoDB = new AmazonDynamoDBClient(credentials);
        Region usEast1 = Region.getRegion(Regions.US_EAST_1);
        dynamoDB.setRegion(usEast1);
    }
	
	private static Map<String, AttributeValue> newItem(long Id,String username, String profilelocation, String placename, double Latitude, double Longitude, String application, String content) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("tweetId", new AttributeValue().withN(Long.toString(Id)));
        item.put("username", new AttributeValue(username));
        item.put("profilelocation", new AttributeValue(profilelocation));
        item.put("placename", new AttributeValue(placename));
        item.put("Latitude", new AttributeValue().withN(Double.toString(Latitude)));
        item.put("Longitude", new AttributeValue().withN(Double.toString(Longitude)));
        item.put("application", new AttributeValue(application));
        item.put("keyword", new AttributeValue(content));
        return item;
    }
	
    public static void main(String[] args) throws Exception {
    	init();
    	
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("GakdGIOMN1CX326yDOVBuWf93");
        cb.setOAuthConsumerSecret("geQGBa2tyXYi18H1JCRtn4jeQcDfjNap1cCCiWgrcJV7YDOnjs");
        cb.setOAuthAccessToken("2852791373-paLWIyMR00FJKm9URZVkLNmKvvsjDkXp54guw2O");
        cb.setOAuthAccessTokenSecret("vRYDiy56pygA4TY0dEcbVB3RHEeu19AH0JZWcoh0mD8Zz");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        
        /*
        try {
            Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
            //Class.forName("org.gjt.mm.mysql.Driver");
           System.out.println("Success loading Mysql Driver!");
          }
          catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
          }
        */
        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
            	User user = status.getUser();
            	
            	
				// gets Username
				String username = status.getUser().getScreenName();
				System.out.println(username);
				String profileLocation = user.getLocation();
				System.out.println(profileLocation);
				long tweetId = status.getId(); 
				System.out.println(tweetId);
				String content = status.getText();
				System.out.println(content);
				GeoLocation geolocation = status.getGeoLocation();
				double tweetLatitude = geolocation.getLatitude();
				double tweetLongitude = geolocation.getLongitude();
				
				String application = status.getSource();
				System.out.println(application);

				Place placenameJSON = status.getPlace();
				String placename = placenameJSON.getFullName();
				System.out.println(placename);
				System.out.println(tweetLatitude + "," + tweetLongitude +"\n");
				
				//Tweet tweet = new Tweet(tweetId, username, profileLocation, placename, tweetLatitude, tweetLongitude, application);
				try {
		            String tableName = "tweet";

		            // Create table if it does not exist yet
		            if (Tables.doesTableExist(dynamoDB, tableName)) {
		                System.out.println("Table " + tableName + " is already ACTIVE");
		            } else {
		                // Create a table with a primary hash key named 'name', which holds a string
		                CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
		                    .withKeySchema(new KeySchemaElement().withAttributeName("username").withKeyType(KeyType.HASH))
		                    .withAttributeDefinitions(new AttributeDefinition().withAttributeName("username").withAttributeType(ScalarAttributeType.S))
		                    .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
		                    TableDescription createdTableDescription = dynamoDB.createTable(createTableRequest).getTableDescription();
		                System.out.println("Created Table: " + createdTableDescription);

		                // Wait for it to become active
		                System.out.println("Waiting for " + tableName + " to become ACTIVE...");
		                Tables.waitForTableToBecomeActive(dynamoDB, tableName);
		            }

		            // Describe our new table
		            DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
		            TableDescription tableDescription = dynamoDB.describeTable(describeTableRequest).getTable();
		            System.out.println("Table Description: " + tableDescription);
		
		            // Add an item
		            Map<String, AttributeValue> item = newItem(tweetId,username, profileLocation, placename, tweetLatitude, tweetLongitude, application, content);
		            PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
		            PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
		            System.out.println("Result: " + putItemResult);

		        } catch (AmazonServiceException ase) {
		            System.out.println("Caught an AmazonServiceException, which means your request made it "
		                    + "to AWS, but was rejected with an error response for some reason.");
		            System.out.println("Error Message:    " + ase.getMessage());
		            System.out.println("HTTP Status Code: " + ase.getStatusCode());
		            System.out.println("AWS Error Code:   " + ase.getErrorCode());
		            System.out.println("Error Type:       " + ase.getErrorType());
		            System.out.println("Request ID:       " + ase.getRequestId());
		        } catch (AmazonClientException ace) {
		            System.out.println("Caught an AmazonClientException, which means the client encountered "
		                    + "a serious internal problem while trying to communicate with AWS, "
		                    + "such as not being able to access the network.");
		            System.out.println("Error Message: " + ace.getMessage());
		        }
				//104857600
				/*
				Connection connect;
				try {
					
					connect = DriverManager.getConnection(
					          "jdbc:mysql://localhost:3306/information_schema","root","");
					Statement stmt = connect.createStatement();
				    ResultSet rs = stmt.executeQuery("SELECT TABLE_NAME,DATA_LENGTH+INDEX_LENGTH,TABLE_ROWS FROM TABLES WHERE TABLE_SCHEMA='test' AND TABLE_NAME='tweet';");
				    Long result = (long) 0;
				    while (rs.next()) {
				    	result = rs.getLong("DATA_LENGTH+INDEX_LENGTH");
				    }
				    if(result<104857600) {//100MB
				    	
	          	    	connect = DriverManager.getConnection(
						          "jdbc:mysql://localhost:3306/twitter","root","");
	          	    	PreparedStatement Statement=connect.prepareStatement("INSERT INTO tweet VALUES(?,?,?,?,?,?,?,?,?)");
						Statement.setLong(1, tweetId);
						Statement.setString(2, username);
						Statement.setString(3, content);
						Statement.setString(4, profileLocation);
						Statement.setString(5, placename);
						Statement.setDouble(6, tweetLatitude);
						Statement.setDouble(7, tweetLongitude);
						Statement.setString(8, application);
						//Statement.setString(9, time.toString());
						Statement.executeUpdate();
	          	    //}
	          	      
            	} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
           */
            	 
            	
            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        
        FilterQuery fq = new FilterQuery();
        /*
		double lat = 53.270141;
		double longitude = -9.055488;
		double lat1 = lat - .25;
		double longitude1 = longitude - .25;
		double lat2 = lat + .25;
		double longitude2 = longitude + .25;
		
		double[][] bb= {{longitude1, lat1}, {longitude2, lat2}};
		*/
        double[][] bb= {{-180, -90}, {180, 90}};
		fq.locations(bb);
		
  
        /*
        String keywords[] = {"Obama"};

        fq.track(keywords);
        */
        twitterStream.addListener(listener);
		twitterStream.filter(fq);  
	
    }
}