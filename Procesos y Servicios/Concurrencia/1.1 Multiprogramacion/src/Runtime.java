import java.io.IOException;
import java.util.*;
import java.lang.*;

public class Runtime {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try { 
            Process p = Runtime.getRuntime().exec("kcalc");
            
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
                    cont = false;
                    System.out.println("Esperamos a que el proceso muera");
                } else{
                    System.out.println("Letra mal introducida.");
                    Thread.sleep(2000);
                }
            } while (!respuesta.equalsIgnoreCase("y"));

            int result = p.waitFor();
            int result2 = p.exitValue();
            System.out.println(result == result2);
            System.out.println("Final proceso (" + p.pid() + "). Resultado: " + result + ". Información adicional: " + p.info());
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            sc.close();
        }
    }
}

