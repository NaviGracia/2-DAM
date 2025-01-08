import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();  
        cliente.run();  
    }

    private final String serverAddress;
    private final int serverPort;

    private Socket socket = null;
    private InputStreamReader isr = null;
    private BufferedReader bfr = null;

    Scanner sc = new Scanner(System.in);
    final String errorMSG = "CLIENT ERROR";

    // Constructor
    public Cliente() {
        this.serverAddress = "192.168.67.184";
        this.serverPort = 1337;
    }

    @Override
    public void run() {
        try{
            connect();

            // Recibir el mensaje de WELCOME
            System.out.println("CLIENT: " + receive());

            //Mandar usuario
            send();
            System.out.println("CLIENT: Sending username...");

            //Recibir mensaje de usuario
            System.out.println("CLIENT: " + receive());

            //Mandar password
            send();
            System.out.println("CLIENT: Sending password...");
            
            String loginResponse = receive();
            System.out.println("CLIENT: " + loginResponse);

            if (loginResponse.contains("ACCESO PERMITIDO")) {
                System.out.println("CLIENT: " + receive());

                //Mandamos las cartas a buscar
                send();
                System.out.println("CLIENT: Sending card request...");
                
                while (bfr.readLine()!=null) {
                    System.out.println(bfr.readLine());
                }
                System.out.println("CLIENT: Message Received");
            } else {
                System.out.println("CLIENT: Login failed. Exiting...");
            }
            bfr.close();
            isr.close();
        } catch (Exception e) {
            System.err.println("CLIENT: Error communicating with server: " + e.getMessage());
        }
    }

    public boolean connect(){
        try {
            socket = new Socket(serverAddress, serverPort);
            System.out.println("CLIENT: Connected");
            return true;
        } catch (IOException e) {
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
            return ans;
        } catch (Exception e) {
            return errorMSG;
        }
    }

    public boolean send(){
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(sc.nextLine());
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