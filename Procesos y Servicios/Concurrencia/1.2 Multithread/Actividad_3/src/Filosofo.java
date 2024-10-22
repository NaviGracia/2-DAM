import java.util.Random;

public class Filosofo implements Runnable{
    int numFilosofo;
    boolean comiendo;

    public Filosofo(int numFilosofo, boolean comiendo) {
        this.numFilosofo = numFilosofo;
        this.comiendo = comiendo;
    }

    public void run(){
        String miNombre=Thread.currentThread().getName();
        Random generador=new Random();
        
            /* Comer*/
            // Intentar coger palillos

            System.out.println("Filosofo " + miNombre+" comiendo...");
            esperarTiempoAzar(miNombre, (1+generador.nextInt(5))*1000);
            /* Pensando...*/
            //Recordemos soltar los palillos
            System.out.println("Filosofo " + miNombre+" pensando...");
            esperarTiempoAzar(miNombre, (1+generador.nextInt(5))*1000);
        
    }

    private void esperarTiempoAzar(String miNombre, int milisegs) {
        try { Thread.sleep(milisegs);
        } catch (InterruptedException e) {
            System.out.println( miNombre+" interrumpido!!. Saliendo...");
            return;
        }
    }
}
