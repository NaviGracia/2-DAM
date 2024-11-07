import java.util.concurrent.CyclicBarrier;

public class LanzaHilos {
    public static void main(String[] args) throws Exception {
        int napostadores=4;
        String nombres[] = new String[napostadores];
        iniciarNombres(nombres);

        //Creación de Objetos
        Apostador apostadores[] = new Apostador[napostadores];
        Banca bc;
        CyclicBarrier barrier = new CyclicBarrier(napostadores);
        bc = new Banca(apostadores);
        Thread[] hilo = new Thread[napostadores];

        //Configuramos los palillos asociados a cada filósofo
        for (int i=0;i<napostadores;i++){
            apostadores[i] = new Apostador(bc, nombres[i], barrier);
            hilo[i] = new Thread(apostadores[i]);
        }

        //Lanzamos todos los hilos
        for (int i = 0; i < napostadores; i++) {
            hilo[i].start();
        }
    }

    public static void iniciarNombres(String nombres[]){
        nombres[0] = "Aristóteles";
        nombres[1] = "Pericles";
        nombres[2] = "Sócrates";
        nombres[3] = "Eivar";
    }
}
