/*
 * Copyright 2010 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * Modified by Sambit Sahu
 * Modified by Kyung-Hwa Kim (kk2515@columbia.edu)
 * 
 * 
 */
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;


public class AwsSample {

    /*
     * Important: Be sure to fill in your AWS access credentials in the
     *            AwsCredentials.properties file before you try to run this
     *            sample.
     * http://aws.amazon.com/security-credentials
     */

    static AmazonEC2      ec2;
    
    private static String IP;
    
    public static void main(String[] args) throws Exception {


    	 AWSCredentials credentials = new PropertiesCredentials(
    			 AwsSample.class.getResourceAsStream("AwsCredentials.properties"));

         /*********************************************
          * 
          *  #1 Create Amazon Client object
          *  
          *********************************************/
    	 System.out.println("#1 Create Amazon Client object");
         ec2 = new AmazonEC2Client(credentials);
         
         /*********************************************
          * Added By Chenyun Zhang
          *  # Create an Amazon EC2 Security Group
          *  
          *********************************************/
         System.out.println("#1⃣ Create an Amazon EC2 Security Group"); 
         CreateSecurityGroupRequest createSecurityGroupRequest = 
     			new CreateSecurityGroupRequest();
     		        	
     		createSecurityGroupRequest.withGroupName("JavaSecurityGroup")
     			.withDescription("My Java Security Group");
     
     	CreateSecurityGroupResult createSecurityGroupResult = 
     				  ec2.createSecurityGroup(createSecurityGroupRequest);
     	
     	/*********************************************
         * Added By Chenyun Zhang
         *  # Authorize Security Group Ingress
         *  
         *********************************************/	
     	System.out.println("#2⃣ Authorize Security Group Ingress");
     	
     	ArrayList<IpPermission> ipPermission = 
     			new ArrayList<IpPermission>();
     		    	
     	//SSH
        IpPermission ipssh=new IpPermission();
        ipssh.setIpProtocol("tcp");
        ipssh.setFromPort(new Integer(22));
        ipssh.setToPort(new Integer(22));
        //ipssh.withIpRanges(ipRanges);
        ipssh.withIpRanges("72.69.22.123/32");
        ipPermission.add(ipssh);
         
        //HTTP
        IpPermission iphttp=new IpPermission();
        
        iphttp.setIpProtocol("tcp");
        iphttp.setFromPort(new Integer(80));
        iphttp.setToPort(new Integer(80));
        iphttp.withIpRanges("0.0.0.0/0");
        ipPermission.add(iphttp);
         
         
         //TCP
        IpPermission iptcp=new IpPermission();
        iptcp.setIpProtocol("tcp");
        iptcp.setFromPort(new Integer(49152));
        iptcp.setToPort(new Integer(49152));
        iptcp.withIpRanges("0.0.0.0/0");
        ipPermission.add(iptcp);
     	
     	AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest =
     				new AuthorizeSecurityGroupIngressRequest();
     			    	
     			authorizeSecurityGroupIngressRequest.withGroupName("JavaSecurityGroup")
     			                                    .withIpPermissions(ipPermission);
     	
     	ec2.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
     	
     	/*********************************************
         * Added By Chenyun Zhang
         *  # Create a Key Pair
         *  
         *********************************************/
     	System.out.println("#3⃣ Create a Key Pair");
     	
     	CreateKeyPairRequest createKeyPairRequest =
     			  new CreateKeyPairRequest();

     			createKeyPairRequest.withKeyName("HW2");
     	
     	CreateKeyPairResult createKeyPairResult =
     					  ec2.createKeyPair(createKeyPairRequest);
     	
     	KeyPair keyPair = new KeyPair();

     	keyPair = createKeyPairResult.getKeyPair();

     	String privateKey = keyPair.getKeyMaterial();
     	
     	//Calling createKeyPair is the only way to obtain the private key programmatically.
     	/*********************************************
         * Added By Chenyun Zhang
         *  # Download KeyPair
         *  
         *********************************************/
     	PrintWriter Storekey = new PrintWriter("/Users/Annabelle/Documents/NYU-POLY/3/Cloud Computing/HW2" + "/" + "Hw2" + ".pem", "UTF-8");
     	Storekey.print(privateKey);
     	Storekey.close();
     	System.out.println("Already store the key!");
     	
        try {
        	
        	/*********************************************
        	 * 
             *  #2 Describe Availability Zones.
             *  
             *********************************************/
        	System.out.println("#2 Describe Availability Zones.");
            DescribeAvailabilityZonesResult availabilityZonesResult = ec2.describeAvailabilityZones();
            System.out.println("You have access to " + availabilityZonesResult.getAvailabilityZones().size() +
                    " Availability Zones.");

            /*********************************************
             * 
             *  #3 Describe Available Images
             *  
             *********************************************/
            System.out.println("#3 Describe Available Images");
            DescribeImagesResult dir = ec2.describeImages();
            List<Image> images = dir.getImages();
            System.out.println("You have " + images.size() + " Amazon images");
            
            
            /*********************************************
             *                 
             *  #4 Describe Key Pair
             *                 
             *********************************************/
            System.out.println("#9 Describe Key Pair");
            DescribeKeyPairsResult dkr = ec2.describeKeyPairs();
            System.out.println(dkr.toString());
            
            /*********************************************
             * 
             *  #5 Describe Current Instances
             *  
             *********************************************/
            System.out.println("#4 Describe Current Instances");
            DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();
            List<Reservation> reservations = describeInstancesRequest.getReservations();
            Set<Instance> instances = new HashSet<Instance>();
            // add all instances to a Set.
            for (Reservation reservation : reservations) {
            	instances.addAll(reservation.getInstances());
            }
            
            System.out.println("You have " + instances.size() + " Amazon EC2 instance(s).");
            for (Instance ins : instances){
            	
            	// instance id
            	String instanceId = ins.getInstanceId();
            	
            	// instance state
            	InstanceState is = ins.getState();
            	System.out.println(instanceId+" "+is.getName());
            }
     
            /*********************************************
             * 
             *  #6 Create an Instance
             *  
             *********************************************/
            System.out.println("#5 Create an Instance");
            String imageId = "ami-76f0061f"; //Basic 64-bit Amazon Linux AMI
            int minInstanceCount = 1; // create 1 instance
            int maxInstanceCount = 1;
            //RunInstancesRequest rir = new RunInstancesRequest(imageId, minInstanceCount, maxInstanceCount);
            RunInstancesRequest rir=new RunInstancesRequest();
            rir.withImageId(imageId)
            .withInstanceType("t1.micro")
            .withMinCount(minInstanceCount)
            .withMaxCount(maxInstanceCount)
            .withKeyName("HW2")
            .withSecurityGroups("JavaSecurityGroup");           
            RunInstancesResult result = ec2.runInstances(rir);
            
            /*********************************************
             * Added by Chenyun Zhang
             *  # Get the public Ip address
             *  
             *********************************************/
            //get instanceId from the result
            List<Instance> resultInstance = result.getReservation().getInstances();
            String createdInstanceId = null;
            for (Instance ins : resultInstance){
            	createdInstanceId = ins.getInstanceId();
            	System.out.println("New instance has been created: "+ins.getInstanceId());
            	
            	 //DescribeInstancesRequest and get ip
            	String createdInstanceIp=null;
            	while(createdInstanceIp==null)
            	{
            		System.out.println("Please waiting for 10 seconds!");
            		Thread.sleep(10000);
            		
            		DescribeInstancesRequest newdescribeInstances =new DescribeInstancesRequest();
            		DescribeInstancesResult newdescribeInstancesRequest=ec2.describeInstances(newdescribeInstances);
                    List<Reservation> newreservations = newdescribeInstancesRequest.getReservations();
                    Set<Instance> allinstances = new HashSet<Instance>();
                    for (Reservation reservation : newreservations) {
                    	allinstances.addAll(reservation.getInstances());
                      }
                      
                    for (Instance myinst : allinstances){
                      	String instanceId = myinst.getInstanceId();
                      	if(instanceId.equals(createdInstanceId))
                      	{
                      		createdInstanceIp=myinst.getPublicIpAddress();
                      	}
                      }
                     
                    
                    
            	}
            	System.out.println("Already get the Ip!");
            	System.out.println("New instance's ip address is:"+createdInstanceIp);
            	IP=createdInstanceIp;
            }
            
            
            /*********************************************
             * 
             *  #7 Create a 'tag' for the new instance.
             *  
             *********************************************/
            System.out.println("#6 Create a 'tag' for the new instance.");
            List<String> resources = new LinkedList<String>();
            List<Tag> tags = new LinkedList<Tag>();
            Tag nameTag = new Tag("Name", "MyFirstInstance");
            
            resources.add(createdInstanceId);
            tags.add(nameTag);
            
            CreateTagsRequest ctr = new CreateTagsRequest(resources, tags);
            ec2.createTags(ctr);
            
            
            /*********************************************
             *  Added By Chenyun Zhang
             *  # SSH connect into EC2
             *  
             *********************************************/
            
            Thread.sleep(100000);
            ssh con=new ssh();
            con.sshcon(IP);
            
            /*********************************************
             * 
             *  #8 Stop/Start an Instance
             *  
             *********************************************/
            System.out.println("#7 Stop the Instance");
            List<String> instanceIds = new LinkedList<String>();
            instanceIds.add(createdInstanceId);
            
            //stop
            StopInstancesRequest stopIR = new StopInstancesRequest(instanceIds);
            //ec2.stopInstances(stopIR);
            
            //start
            StartInstancesRequest startIR = new StartInstancesRequest(instanceIds);
            //ec2.startInstances(startIR);
            
            
            /*********************************************
             * 
             *  #9 Terminate an Instance
             *  
             *********************************************/
            System.out.println("#8 Terminate the Instance");
            TerminateInstancesRequest tir = new TerminateInstancesRequest(instanceIds);
            //ec2.terminateInstances(tir);
            
                        
            /*********************************************
             *  
             *  #10 shutdown client object
             *  
             *********************************************/
            ec2.shutdown();
            
            
            
        } catch (AmazonServiceException ase) {
                System.out.println("Caught Exception: " + ase.getMessage());
                System.out.println("Reponse Status Code: " + ase.getStatusCode());
                System.out.println("Error Code: " + ase.getErrorCode());
                System.out.println("Request ID: " + ase.getRequestId());
        }

        
    }
}
