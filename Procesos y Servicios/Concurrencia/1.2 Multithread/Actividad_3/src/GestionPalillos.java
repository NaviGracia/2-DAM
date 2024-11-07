import java.util.HashMap;

public class GestionPalillos {
    boolean palillos[];

    public GestionPalillos(int numPalillos){
        palillos = new boolean[numPalillos];
        for (int i = 0; i < numPalillos; i++) {
            palillos[i] = true;
        }
    }

    public synchronized boolean cogerPalillos(int pos1, int pos2, String nombre) {
        boolean puedeComer = false;
        if (palillos[pos1] && palillos[pos2]) {
            palillos[pos1] = false;
            palillos[pos2] = false;
            puedeComer = true;
            System.out.println(nombre + " coge los palillos");
        }
        return puedeComer;
    }

    public synchronized void liberarPalillos(int pos1, int pos2, String nombre) {
        palillos[pos1] = true;
        palillos[pos2] = true;
        System.out.println(nombre + " libera los palillos");
    }
}