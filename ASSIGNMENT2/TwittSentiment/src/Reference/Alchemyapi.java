package Reference;

import com.alchemyapi.api.AlchemyAPI;
import com.amazonaws.services.sqs.model.Message;
import Reference.SNSManager;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import TwitterStream.SqsHelper;

public class Alchemyapi {
	//AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("API KEY");
	//static SqsHelper sqs;
    //static String qurl;
   
	public static void main(String[] args) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("YOUR-API-KEY");
        SNSManager sns = new SNSManager();
        sns.subscribeToATopic("Tweets", "cz574@nyu.edu");
        SqsHelper sqs = new SqsHelper();
        String qurl = sqs.listSQS();
        List<Message> messages = sqs.receiveSQS(qurl);
        
        for(Message t : messages){
        	Document doc = alchemyObj.TextGetTextSentiment(t.getBody());

            // parse XML result
            String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
            //String score = doc.getElementsByTagName("score").item(0).getTextContent();
            sns.publishToATopic("Tweets", sentiment);
            // print results
            System.out.println("Sentiment: " + sentiment);
            //System.out.println("Score: " + score);
            
        }
        /*****
        // load text
        String text = readFile("text_files/text.txt", Charset.defaultCharset());

        // analyze text
        Document doc = alchemyObj.TextGetTextSentiment(text);

        // parse XML result
        String sentiment = doc.getElementsByTagName("type").item(0).getTextContent();
        String score = doc.getElementsByTagName("score").item(0).getTextContent();

        // print results
        System.out.println("Sentiment: " + sentiment);
        System.out.println("Score: " + score);
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
    ****/
	}
}
