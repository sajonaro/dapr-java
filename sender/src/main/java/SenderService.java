import java.net.*;
import java.io.*;

public class SenderService {
  public static void main(String[] args) {

    try {
      
      URL url = new URL("http://localhost:3500/v1.0/invoke/receiver/method/api/ping");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
  
      int responseCode = connection.getResponseCode();
      System.out.println("Response Code: " + responseCode);
  
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
  
      while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
      }
      in.close();
  
      System.out.println("Response: " + response.toString());

    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  
  }
}