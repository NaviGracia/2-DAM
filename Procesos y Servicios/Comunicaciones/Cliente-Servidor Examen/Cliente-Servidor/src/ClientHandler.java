import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket conexion;

    public ClientHandler(Socket conexion){
        this.conexion = conexion;
    }

    @Override
    public void run() {
        try(
            OutputStream os =  conexion.getOutputStream(); 
            PrintWriter pw = new PrintWriter(os,true);
            InputStreamReader isr = new InputStreamReader(conexion.getInputStream());
	        BufferedReader bfr = new BufferedReader(isr);
	        
        ) {
            System.out.println("SERVER: Cliente conectado");
            
            Boolean exitProgram = false;
            while (!exitProgram) {
                switch (Server.receive(conexion)) {
                    case "-uptime":
                        System.out.println("Sending actual time...");
                        Server.sendDate(pw);
                    break;
    
    
                    case "-reverse":
                    System.out.println("SERVER: Recibing message...");
                    Server.sendMessage(pw, Server.receive(conexion));
                    break;
    
                    case "-exit":
                    System.out.println("SERVER: Closing conection...");
                    Server.sendCiao(pw, conexion);
                    exitProgram = true;
                    break;
                
                    default:
                    Server.send(pw, "unknown_command");
                    break;
                }
            }
           

            
        } catch (Exception e) {
        }

    }
    
}