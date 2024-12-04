import java.util.concurrent.CyclicBarrier;

public class Lanzahilos {
    public static void main(String[] args) throws Exception {
        int numJugadores=6;
        String nombres[] = new String[numJugadores];
        iniciarNombres(nombres);

        //Creación de Objetos
        Jugador jugadores[] = new Jugador[numJugadores];
        Reloj rj;
        CyclicBarrier barrier = new CyclicBarrier(numJugadores);
        rj = new Reloj();
        Thread[] hilo = new Thread[numJugadores];
        //Asignamos los objetos a los hilos
        for (int i=0;i<numJugadores;i++){
            jugadores[i] = new Jugador(nombres[i], rj, barrier);
            hilo[i] = new Thread(jugadores[i]);
        }

        //Lanzamos todos los hilos
        for (int i = 0; i < numJugadores; i++) {
            hilo[i].start();
        }
    }

    public static void iniciarNombres(String nombres[]){
        nombres[0] = "Ajani";
        nombres[1] = "Tatsunori";
        nombres[2] = "Chishiro";
        nombres[3] = "Liliana";
        nombres[4] = "Jace";
        nombres[5] = "Krenkro";
    }
}
