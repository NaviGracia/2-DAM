import java.util.concurrent.CyclicBarrier;

public class Quedada {
    public static void main(String[] args) throws Exception {
        int nAmigos=3;
        String nombres[] = new String[nAmigos];
        iniciarNombres(nombres);

        //Creación de Objetos
        Amigo amigos[] = new Amigo[nAmigos];
        CyclicBarrier barrier = new CyclicBarrier(nAmigos);
        Thread[] hilo = new Thread[nAmigos];

        //Insertamos datos de Objetos y anclamos al hilo
        for (int i=0;i<nAmigos;i++){
            amigos[i] = new Amigo(nombres[i], barrier);
            hilo[i] = new Thread(amigos[i]);
        }

        //Lanzamos todos los hilos
        for (int i = 0; i < nAmigos; i++) {
            hilo[i].start();
        }
    }

    public static void iniciarNombres(String nombres[]){
        nombres[0] = "Managorger Hydra";
        nombres[1] = "Ajani";
        nombres[2] = "Ragavan";
    }
}
