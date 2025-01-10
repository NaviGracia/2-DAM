import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
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
            Comm.sendMessage(pw, "Bienvenido");
            Boolean exitProgram = false;
            while (!exitProgram) {
                switch (Comm.recieveMessage(conexion)) {
                    case "/time":
                    System.out.println("Enviando la hora actual...");
                        Comm.sendCurrentDate(pw);
                    break;
    
    
                    case "/wait":
                    System.out.println("SERVER: Esperando tiempo random...");
                        Comm.sendMessage(pw, Comm.randomWait());
                    break;
    
                    case "/quit":
                    System.out.println("SERVER: Cerrando la conexión...");
                    Comm.exitProgram(pw,conexion);
                    exitProgram = true;
                    break;
                
                    default:
                    Comm.sendMessage(pw, "not_recognized");
                    break;
                }
            }     
        } catch (Exception e) {
            
        }

    }
}
