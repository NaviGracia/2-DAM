import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    String host = "";
    int port = 0;

    private Socket socket = null;
    private InputStreamReader isr = null;
    private BufferedReader bfr = null;

    final String errorMSG = "CLIENT ERROR";
    
    public Cliente(String host, int port){
        this.host = host;
        this.port = port;
    }

    public boolean connect(){
        try {
            socket = new Socket(host, port);
            System.out.println("CLIENT: Connected");
            return true;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public String receive(){
        try {
            isr = new InputStreamReader(socket.getInputStream());
            bfr = new BufferedReader(isr);
            String ans = bfr.readLine();
            System.out.println("CLIENT: Message Received");
            bfr.close();
            isr.close();
            return ans;
        } catch (Exception e) {
            // TODO: handle exception
            return errorMSG;
        }
    }

    public boolean send(String message){
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(message);
            pw.flush();
            System.out.println("CLIENT: Message Sent.");
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }
}

