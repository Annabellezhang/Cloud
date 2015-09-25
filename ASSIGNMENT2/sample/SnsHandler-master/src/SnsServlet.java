import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

public class SnsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getAnonymousLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Scan request into a string
            Scanner scanner = new Scanner(request.getInputStream());
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }

            // Parse the JSON message
            InputStream stream = new ByteArrayInputStream(builder.toString().getBytes());
            Map<String, String> message = new ObjectMapper().readValue(stream, Map.class);

            // Confirm the subscription
            if (message.get("Type").equals("SubscriptionConfirmation")) {
                
                new URL(message.get("SubscribeURL")).openStream();
                log.info("Confirmed: " + message.get("TopicArn"));

            } else if (message.get("Type").equals("Notification")) {
                log.info("Received: " + message.get("Message"));
            }
            log.info(builder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

