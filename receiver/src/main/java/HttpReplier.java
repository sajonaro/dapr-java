import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class HttpReplier implements Runnable {
    private Socket socket;
    private String content;

    public HttpReplier(Socket socket, String content) {
        this.socket = socket;
        this.content = content;
    }

    public void run() {
        try {
            //quick and dirty way of only accepting GET requests to /api/ping
            // BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // String requestLine = reader.readLine();
            // if (requestLine.startsWith("GET /api/ping")) {
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("HTTP/1.1 200 OK");
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
                ps.println("Date: "+ dateFormat.format(new Date()));
                ps.println("Content-Length: " + content.length());
                ps.println("Content-Type: application/json");
                ps.println("Connection: Closed");
                ps.println();
                ps.println(content);    
            // }
            // else {
            //     // Send 404 error
            //     PrintStream ps = new PrintStream(socket.getOutputStream());
            //     ps.println("HTTP/1.1 404 Not Found");
            //     ps.close();
            // }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}