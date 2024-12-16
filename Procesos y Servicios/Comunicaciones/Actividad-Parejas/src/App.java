import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    private static final int port = 1337;
    public static void main(String[] args) throws Exception {
        //Parte Servidor
        Server srv = new Server(port);
        Thread tServer = new Thread(srv);
        tServer.run();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

class Server implements Runnable{
    private ServerSocket server = null;
    private Socket client = null;
    int port = 0;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run(){
        HashMap<Integer, Mazo> mazos = new HashMap();
        mazos = generarMazos(mazos);
        InputStreamReader isr = null;
        BufferedReader reader = null;
        PrintWriter pw = null;
        OutputStream os = null;

        System.out.println("INFO: Server launching...");

        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("ERROR: Unable to open socket on TCP " + port);
            return;
        }

        while (true) {
            try {
                client = server.accept();
                System.out.println("SERVER: Connection stablished");

                isr = new InputStreamReader(client.getInputStream());
                reader = new BufferedReader(isr);

                //Read Message
                System.out.println("SERVER: Which deck format are you looking for? \n 1.Modern \n 2.Commander \n Write only the number: ");
                int option = Integer.parseInt(reader.readLine());
                //Write Answer
                os = client.getOutputStream();
                pw = new PrintWriter(os);
                switch (option) {
                    case 1:
                        mostrarMazo(mazos, 1, pw);
                        break;
                    case 2:
                        mostrarMazo(mazos, 2, pw);
                        break;
                    default:
                        break;
                }
                System.out.println("SERVER: Message received");

                
                System.out.println("SERVER: Message Sent");

                //Close Handlers
                pw.close();
                reader.close();
                isr.close();
                client.close();
            } catch (IOException e) {
                // TODO: handle exception
                System.out.println("SERVER: Failed connecting to client");
            }
        }
    }

    private void mostrarMazo(HashMap<Integer, Mazo> mazos, int numeroMazo, PrintWriter pw){
        ArrayList<Carta> cartas = mazos.get(numeroMazo).getMazo();
        System.out.println(mazos.get(numeroMazo).getNombre() + " : " + mazos.get(numeroMazo).getPrecio() + "€");
        for (int i = 0; i < cartas.size(); i++) {
            pw.write(cartas.get(i).getCantidad() + " " + cartas.get(i).getNombre());
        }
    }

    private static HashMap<Integer, Mazo> generarMazos(HashMap<Integer, Mazo> mazos){
        Mazo m = new Mazo();
        mazos.put(1, m.generarMazoLifeStealth());
        mazos.put(2, m.generarMazoCounters());
        return mazos;
    }
}