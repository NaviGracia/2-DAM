import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable {
    public static void main(String[] args) {
        Cliente client = new Cliente();
        client.run();    
    }

    Scanner sc = new Scanner(System.in);
    
    private final String serverAddress;
    private final int serverPort;

    public Cliente() {
        this.serverAddress = "192.168.56.1";
        this.serverPort = 1337;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Paso 1: Recibir mensaje de bienvenida
            System.out.println("CLIENT: MENSAJE RECIBIDO: " + in.readLine());

            // Paso 2: Enviar nombre de usuario
            String username = sc.nextLine();
            out.println(username);
            out.flush();
            System.out.println("Sending username...");

            //user: ajani | password: contadores

            // Paso 3: Enviar contraseña
            String password = sc.nextLine();
            out.println(password);
            out.flush();
            System.out.println("Sending password...");

            // Paso 4: Recibir respuesta del servidor
            String loginResponse = in.readLine();
            System.out.println("CLIENT: MENSAJE RECIBIDO: " + loginResponse);

            if (loginResponse.contains("ACCESO PERMITIDO")) {
                // Step 5: Server requests card names
                System.out.println("CLIENT: MENSAJE RECIBIDO: " + in.readLine());

                // Step 6: Send card names
                System.out.println("Sending card request...");
                out.println("Ancestor's Chosen, Lightning Bolt, Black Lotus");

                // Step 7: Receive server response with card data
                String serverResponse = in.readLine();
                System.out.println("CLIENT: MENSAJE RECIBIDO: " + serverResponse);

            } else {
                System.out.println("Login failed. Exiting client.");
            }

            socket.close();

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }
    }
}


