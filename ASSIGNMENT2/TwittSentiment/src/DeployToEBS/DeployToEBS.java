package DeployToEBS;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClient;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentResult;
import com.amazonaws.services.elasticbeanstalk.model.S3Location;
import com.amazonaws.services.elasticbeanstalk.model.UpdateEnvironmentRequest;
import com.amazonaws.services.elasticbeanstalk.model.UpdateEnvironmentResult;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClient;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersResult;
import com.amazonaws.services.s3.model.ProgressEvent;
import com.amazonaws.services.s3.model.ProgressListener;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

public class DeployToEBS {

	private static final Log log = LogFactory.getLog(DeployToEBS.class);
	
	private AWSCredentials credentials;

	private String versionLabel;
	private String bucketName;
	private String warName;
	
	private Upload upload;
	public AWSElasticBeanstalk beanstalk;
	
	public DeployToEBS(String versionLabel, String bucketName, String warName) throws Exception {
		
		/****
		 * get credentials of AWS
		 */
		System.out.println("Get Credentials!");
		credentials = new PropertiesCredentials(DeployToEBS.class
                .getResourceAsStream("AwsCredentials.properties"));

		beanstalk = new AWSElasticBeanstalkClient(credentials);
		
		this.versionLabel = versionLabel;
		this.bucketName = bucketName;
		this.warName = warName;

	}
	
	public void execute(final String fileName, final FinishedListener listener) 
		throws FileNotFoundException {
		/***
		 * Upload project war to S3
		 */
		System.out.println("Upload project to S3 bucket!");
        File warToUpload = new File(fileName);
        if (!warToUpload.exists()) {
        	throw new FileNotFoundException();
        }

        /***
         * A ProgressListener will receive updates about the uploading of the file
         * and when it is finished
         */
        System.out.println("ProgressListener");
        ProgressListener progressListener = new ProgressListener() {
            public void progressChanged(ProgressEvent progressEvent) {
                if (upload == null) return;
                
                switch (progressEvent.getEventCode()) {
                case ProgressEvent.COMPLETED_EVENT_CODE:
                    /**
                     * create the new version in Beanstalk
                     */
                	System.out.println("Create new version!");
                	log.info("War "+ fileName+" has been uploaded.");

                	try {
	                	createApplicationVersion();
	            		deployVersion();
                	} catch (AmazonClientException e) {
                		if (listener !=null) listener.failed(e);
                	}
                    break;
                case ProgressEvent.FAILED_EVENT_CODE:
                    try {
                        AmazonClientException e = upload.waitForException();
                        log.error("Unable to upload file "+fileName+" to Amazon S3: " + e.getMessage(), e); 
                    } catch (InterruptedException e) {}
                    break;
                }
            }
        };
        
        TransferManager transferManager = new TransferManager(credentials);

        PutObjectRequest request = new PutObjectRequest(
                bucketName, warName, warToUpload)
            .withProgressListener(progressListener);
        
        /***
         * create the S3 bucket if it doesn't exist
         */
        createS3Bucket(transferManager);
        
        log.info("Uploading war to S3...");
        upload = transferManager.upload(request);
	}

	public void execute(final String fileName) throws FileNotFoundException {

		execute(fileName, null);

      }

	/**
	 * Create an Elastic Beanstalk application version for this war,
	 * indicating its location in S3.
	 */
	private void createApplicationVersion() throws AmazonClientException {
		System.out.println("Create Application Version!");
		SimpleDateFormat simDateFormat = new SimpleDateFormat("dd MMM yyy HH:mm");
		
		log.info("Creating application version " + versionLabel);

		CreateApplicationVersionRequest request = 
			new CreateApplicationVersionRequest("AssignmentOne", versionLabel);
		request.setDescription("War created by TwitterMap on " + simDateFormat.format(new Date()));
		request.setSourceBundle(new S3Location(bucketName, warName));
		
		CreateApplicationVersionResult result = beanstalk.createApplicationVersion(request);
		
		log.info("Version " + result.getApplicationVersion().getVersionLabel() + " has been created.");
		log.info("Version details: " + result.getApplicationVersion());

	}
		
	private void deployVersion() throws AmazonClientException {
		System.out.println("UpdateEnv");
		String environmentName = "assignmentone-env";
		log.info("Updating environment "+environmentName+" with version " +versionLabel);
		UpdateEnvironmentRequest request = new UpdateEnvironmentRequest();
		request.setEnvironmentName(environmentName);
		request.setVersionLabel(versionLabel);
		
		UpdateEnvironmentResult result = beanstalk.updateEnvironment(request);
		log.info("SUCCESS! Version deploy requested. Test environment is now " + result.getStatus());
		
	}

    private void createS3Bucket(TransferManager transferManager) {
        try {
            if (transferManager.getAmazonS3Client().doesBucketExist(bucketName) == false) {
                transferManager.getAmazonS3Client().createBucket(bucketName);
            }
        } catch (AmazonClientException ace) {
            log.error("Unable to create a new Amazon S3 bucket: " + ace.getMessage(), ace);
        }
    }
    
	public static void main(String[] args) throws Exception {


		FinishedListener listener = new FinishedListener() {
			@Override
			public void succeeded() {
				System.exit(0);
			}
			@Override
			public void failed(Throwable e) {
				System.exit(1);
			}
		};
		
		String fileName = "/Users/Annabelle/Documents/TwitterMap.war";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
			String versionLabel = "TwitterMap_" + dateFormat.format(new Date());
			
			DeployToEBS deployer = new DeployToEBS(
					versionLabel,
					"assignmentone-zhang-liu",
					versionLabel + ".war");
			
			deployer.execute(fileName, listener);
		} catch (FileNotFoundException e) {
        	System.out.println("Sorry, the file "+fileName+" doesn't exist");
        	System.exit(0);
		}

	}
	
	private interface FinishedListener {
		void succeeded();
		void failed(Throwable e);
	}

}

