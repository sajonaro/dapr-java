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
            
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("HTTP/1.1 200 OK");
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
                ps.println("Date: "+ dateFormat.format(new Date()));
                ps.println("Content-Length: " + content.length());
                ps.println("Content-Type: application/json");
                ps.println("Connection: Closed");
                ps.println();
                ps.println(content);    
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}