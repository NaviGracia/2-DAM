import java.io.IOException;
import java.util.Scanner;

public class JavaApp {
     static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("kcalc");
            Process p = pb.start();
            
            boolean cont = true;
            do {
                System.out.println("Desea finalizar el proceso? (Y para si, N para no)");
                String respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("y")) {
                    cont=false;
                    System.out.println("Proceso matado:");
                    System.out.println("PID: " + p.pid());
                    System.out.println("INFO: " + p.info());
                    p.destroy();
                } else if (respuesta.equalsIgnoreCase("n")) {
                    Thread.sleep(10000);
                } else{
                    System.out.println("Letra mal introducida.");
                    Thread.sleep(2000);
                }
            } while (!respuesta.equalsIgnoreCase("y"));
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
