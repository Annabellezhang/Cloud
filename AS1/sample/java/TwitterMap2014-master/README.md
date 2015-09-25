TwitterMap2014
==============

Authors: Wei Dai (wd2248), Yijun Wang (yw2676)

Description
-----------
This application is based on AWS elasticbean stalk and Dynamo database. It can show twitter stream on a map according to the twitters' location. Users can select the keywords and click on the marker to see the content of the twitter.
Also support a heatmap layer, which will show the density of the twitters in any area on the map. 
In the repo are three seperate projects: TwitterMap and TwitterScrape. TwitterScrape is for using twitter API to get twitters and save it into DynamoDB. TwitterMap is for using google map api to show the result in the user's browser. DeployNewVersion is to use the Elastic Beanstalk API to create, configure, and deploy an application instance programmatically.

Required Environment and Software
---------------------------------
Demo page: http://twittermap2014-wdyw.elasticbeanstalk.com/
Libraries used in TwitterMap: Google Map API v3, AWS SDK for java, Apache commons-lang-3-3.3.2 library.
Libraries used in TwitterScrape: AWS SDK for java, twitter4j.jar library.

How to run the software
-----------------------
The App contains two parts, the first part is collecting Tweets. It's in the Twitter Scrape/twitterMap folder, it's just plain normal JAVA program, you need Twitter4j and AWS lib to use it. The second part is the server part, which is in the TwitterMap folder, you should import this part, and deploy it on the Elastic Beanstalk using Eclipse. It uses Google Map Javascript API and Apache commons-lang-3-3.3.2 library.
DeployNewVersion is to use the Elastic Beanstalk API to create, configure, and deploy an application instance programmatically.
