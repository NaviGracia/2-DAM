package Actividad2;

public class LanzaHilos {
    public static void main(String[] args) {
        int NUM_HILOS=10;
        Thread[] hilosAsociados;
        hilosAsociados = new Thread[NUM_HILOS];
        for (int i=0;i<NUM_HILOS;i++){
            TareaCompleja t = new TareaCompleja();
            Thread hilo = new Thread(t);
            hilo.setName("Hilo: "+i);
            hilo.start();
            hilosAsociados[i] = hilo;
        }
        /* Despues de crear todo, nos aseguramos de esperar a que todos los hilos acaben. */
        for (int i=0; i<NUM_HILOS; i++){
            Thread hilo=hilosAsociados[i];
            try { //Espera a que el hilo acabe
                hilo.join();
            } catch (InterruptedException e) {
                System.out.print("Algun hilo acabó antes de tiempo!");
            }
        }
        System.out.println("El principal ha terminado");
        }
}
