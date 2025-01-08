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

            System.out.println("CLIENT: Sending username...");
            //Mandar usuario
            send();

            //Recibir mensaje de usuario
            System.out.println("CLIENT: " + receive());

            System.out.println("CLIENT: Sending password...");
            //Mandar password
            send();

            
            String loginResponse = receive();
            System.out.println("CLIENT: " + loginResponse);

            if (loginResponse.contains("ACCESO PERMITIDO")) {
                System.out.println("CLIENT: " + receive());

                System.out.println("CLIENT: Sending card request...");
                //Mandamos las cartas a buscar
                send();

                String serverResponse;
                while ((serverResponse = receive()) != null) {
                    System.out.println("CLIENT: " + serverResponse);
                }
            } else {
                System.out.println("CLIENT: Login failed. Exiting...");
            }

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