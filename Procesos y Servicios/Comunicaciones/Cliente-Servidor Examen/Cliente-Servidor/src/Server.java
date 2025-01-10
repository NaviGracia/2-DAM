import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    int port;
    ServerSocket server;
    Socket clientSocket;

    public Server(int port){
       this.port = port;
    }

    @Override
    public void run() {

        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("SERVER: Error, can't create socket at port: "+ port);
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println("Waiting connection...");
                clientSocket = server.accept();

                ClientHandler newClient = new ClientHandler(clientSocket);
                Thread threadClientNew = new Thread(newClient);
                threadClientNew.start();
            } catch (IOException e) {
                System.out.println("SERVER: Error connecting to Server via port:  " + port );
                e.printStackTrace();
            }
            
        }
    }

    public static void send(PrintWriter pw, String message) {
        pw.println(message);
    }

    public static boolean sendDate(PrintWriter pw) {
         pw.println(System.currentTimeMillis());
         pw.flush();
         System.out.println("SERVER: Message sent: " + System.currentTimeMillis());
         return true;
    }

    public static boolean sendCiao(PrintWriter pw, Socket conexion) {
        try {
            pw = new PrintWriter(conexion.getOutputStream());
            pw.println("Ciao");
            pw.flush();
            System.out.println("SERVER: Message sent: Ciao");
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public static boolean sendMessage(PrintWriter pw, String message) {
        message = new StringBuilder(message).reverse().toString();
        pw.println(message);
        pw.flush();
        System.out.println("SERVER: Message sent: " + message);
        return true;
    }

   public static String receive(Socket conexion) {
      try {
         InputStreamReader isr = new InputStreamReader(conexion.getInputStream());
         BufferedReader bfr = new BufferedReader(isr);
         String ans = bfr.readLine();
         System.out.println("SERVER: Message received: " + ans);
         return ans;
      } catch (IOException var2) {
         var2.printStackTrace();
         return "ERROR";
      }
   }
}
