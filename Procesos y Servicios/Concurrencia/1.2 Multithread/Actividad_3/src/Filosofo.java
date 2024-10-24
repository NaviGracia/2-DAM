import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Filosofo implements Runnable{
    int numFilosofo;
    boolean comiendo;
    static HashMap<Integer, String> filosofos = new HashMap<>();
    GestionPalillos gp = new GestionPalillos();

    public static void sentarFilosofo(int num, String nombre){
        filosofos.put(num, nombre);
    }

    public String obtenerNombreHilo(){
        return Thread.currentThread().getName();
    };

    @Override
    public void run(){
        String miNombre = obtenerNombreHilo();
        
        Random generador=new Random();
        while (true) {
            /* Comer*/
            // Intentar coger palillos
            try {
                if(gp.gestionPalillos(filosofos, miNombre)==true){
                    System.out.println("Filosofo " + miNombre+" comiendo...");
                    esperarTiempoAzar(miNombre, (1+generador.nextInt(5))*1000);
                    /* Pensando...*/
                    //Recordemos soltar los palillos
                    if (gp.soltarPalillos(filosofos, miNombre)==true) {
                        System.out.println("Filosofo " + miNombre+" pensando...");
                        esperarTiempoAzar(miNombre, (1+generador.nextInt(5))*1000);
                    }
                }   
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private void esperarTiempoAzar(String miNombre, int milisegs) {
        try { Thread.sleep(milisegs);
        } catch (InterruptedException e) {
            System.out.println( miNombre+" interrumpido!!. Saliendo...");
            return;
        }
    }

}
