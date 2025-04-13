import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import java.io.IOException;
import java.net.*;
import org.json.JSONObject;

public class ReceiverService {
  public static void main(String[] args) {
    try {
      
        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
        }

        ServerSocket ss = new ServerSocket(Integer.parseInt(port));
        
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\"response\": \"pong\"}");

        while (true) {
            Socket s = ss.accept();
            Thread t = new Thread(new HttpReplier(s, jsonBuilder.toString()));
            t.start();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}}