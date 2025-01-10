import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Server {
    static String host = "localhost";
    static int port = 1234;

    public static void main(String[] args) throws Exception {
        Comm server = new Comm(port);
        Thread serverThread = new Thread(server);
        serverThread.start();

    }
}

class Comm implements Runnable{
    int port;
    ServerSocket server;
    Socket client;

    public Comm(int port){
       this.port = port;
    }

    @Override
    public void run() {

        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("SERVER: Error, no se ha podido crear el socket en el puerto "+ port);
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println("Esperando conexiones...");
                client = server.accept();

                //Lanza cada nueva conexión al ClientHandler para que puedan haber varias conexiones
                ClientHandler newClient = new ClientHandler(client);
                Thread threadClientNew = new Thread(newClient);
                threadClientNew.start();
            } catch (IOException e) {
                System.out.println("SERVER: Error conectando al servidor a través del puerto " + port );
                e.printStackTrace();
            }
        }
    }

    //Funciones de comunicación
    public static void sendMessage(PrintWriter pw, String text){
        pw.println(text);
    }

    public static String recieveMessage(Socket conexion){
        try {
        InputStreamReader isr = new InputStreamReader(conexion.getInputStream());
	    BufferedReader bfr = new BufferedReader(isr);
	        String answer = bfr.readLine();
			System.out.println("SERVER: Message received: " + answer);
	        return answer;
		} catch (IOException e) {
			e.printStackTrace();
            return "error recieving message";
		}
    }

    public static void sendCurrentDate(PrintWriter pw){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        pw.println(LocalDateTime.now().format(dtf));
    }

    public static String randomWait(){
        Random random = new Random();
        Integer numeroEspera = random.nextInt(1000, 3000);
        random.nextInt(1000, 3000);
        try {
            Thread.sleep(numeroEspera);
            
            
            return numeroEspera.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return"ERROR";
        }
    }

    public static void exitProgram(PrintWriter pw, Socket conection){
        sendMessage(pw, "/bye");
        try {
            conection.close();
        } catch (IOException e) {
            System.out.println("Error cerrando la conexion");
            e.printStackTrace();
        }
    }
}
