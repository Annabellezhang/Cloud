import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClient;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentResult;
import com.amazonaws.services.elasticbeanstalk.model.EnvironmentTier;
import com.amazonaws.services.elasticbeanstalk.model.ListAvailableSolutionStacksResult;
import com.amazonaws.services.elasticbeanstalk.model.S3Location;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClient;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersResult;


public class AssignmentOne {
	
	private static AmazonEC2 ec2;
	private static AWSElasticBeanstalk beanstalk;
	private static AmazonElasticLoadBalancing bsclient;
	public static EnvironmentTier tier = new EnvironmentTier().withName("Web Server").withType("Standard").withVersion("1.0");
	
	public static void main(String[] args) throws Exception {
		
   	 AWSCredentials credentials = new PropertiesCredentials(
   			 AssignmentOne.class.getResourceAsStream("AwsCredentials.properties"));
   	 		
   	 		 beanstalk = new AWSElasticBeanstalkClient(credentials);
   	 		 bsclient = new AmazonElasticLoadBalancingClient(credentials);
   	 		 bsclient.setEndpoint("elasticloadbalancing.us-east-1.amazonaws.com");
   	 		 ec2 = new AmazonEC2Client(credentials);
   	 		 // Find an AZ to use
   	 	    final DescribeAvailabilityZonesResult azResult = ec2.describeAvailabilityZones();

   	 	    final String availabilityZone = azResult.getAvailabilityZones().get(0).getZoneName();
   	 		 // Find solution stack
   	 	    ListAvailableSolutionStacksResult ss = beanstalk.listAvailableSolutionStacks();
   	 	    System.out.println(ss.getSolutionStacks());
   	 	      	 	
 			//# Create an application

   	 		System.out.println("# Create an Amazon EBS application"); 
   	 		CreateApplicationRequest createapplicationrequest = new CreateApplicationRequest();
   	 		createapplicationrequest.withApplicationName("AssignmentOne")
   	 								.withDescription("My Java assignment one.");
   	 		CreateApplicationResult createapplicationresult = beanstalk.createApplication(createapplicationrequest);
   	 		
   	 	// # Create an applicationVersion
	          
   	 		System.out.println("# Create an Amazon EBS applicationVersion"); 
   	 		CreateApplicationVersionRequest cavRequest = new CreateApplicationVersionRequest()
   	 				.withApplicationName("AssignmentOne")
   	 				.withSourceBundle(new S3Location("assignmentone-zhang-liu", "twitterMap.zip"))
   	 				.withVersionLabel("twitterMap");
   	 		CreateApplicationVersionResult cavResult = beanstalk.createApplicationVersion(cavRequest);
	
   	 		
   	 		// # Create an environment

   	 		System.out.println("# Create an Amazon EBS environment"); 
   	 		CreateEnvironmentRequest cer = new CreateEnvironmentRequest();
   	 		cer.withApplicationName("AssignmentOne")
   	 			.withEnvironmentName("assignmentone-env")
   	 			//.withTier(tier)
   	 			.withVersionLabel("twitterMap")
   	 			.withSolutionStackName("64bit Amazon Linux 2014.09 v1.0.9 running Node.js");
   	 		
   	 		CreateEnvironmentResult ceresult = beanstalk.createEnvironment(cer);
   	 		Thread.currentThread().sleep(50000);
   	 		System.out.println("Waiting...");
   	 		
   	 		//  # loadbalancer
 	        
   	 		
   	 		System.out.println("# Get information of an Amazon EBS loadbalancer"); 
   	 		DescribeLoadBalancersResult lb = bsclient.describeLoadBalancers();
	 	    System.out.println(lb);
	 	    /*
   	 		CreateLoadBalancerRequest createLoadBalancerRequest = new CreateLoadBalancerRequest();
   	 		createLoadBalancerRequest.setLoadBalancerName("ASSIGNMENTONE");
   	 		List<Listener> listeners = new ArrayList<Listener>(1);
   	 		listeners.add(new Listener("HTTP", 8888, 8888));
   	 		createLoadBalancerRequest.withAvailabilityZones(availabilityZone);
   	 		createLoadBalancerRequest.setListeners(listeners);
   	 		try{
   	 			CreateLoadBalancerResult lbResult = bsclient.createLoadBalancer(createLoadBalancerRequest);
   	 		} catch (AmazonServiceException e){
   	 			e.printStackTrace();
   	 		} catch (AmazonClientException e){
   	 			e.printStackTrace();
   	 		}
   	 		*/
   	 	    
   	 		System.out.println("SUCCESS");
	}
}
