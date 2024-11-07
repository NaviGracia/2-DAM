import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Filosofo implements Runnable{
    GestionPalillos gp;
    int palIzq, palDer;
    String nombre;
    int cuentaComidas;
    boolean doYourThing;

    public Filosofo(GestionPalillos gp, int palIzq, int palDer, String nombre) {
        this.gp = gp;
        this.palIzq = palIzq;
        this.palDer = palDer;
        this.nombre = nombre;
        this.cuentaComidas = 0;
        this.doYourThing = true;
    }

    @Override
    public void run(){
        while (doYourThing) {
            if (this.gp.cogerPalillos(palIzq, palDer, this.nombre)) {
                comer();
                this.gp.liberarPalillos(palIzq, palDer, this.nombre);
                pensar();
            }
            if (Thread.interrupted()) {
                doYourThing = false;
            }
        }
    }

    private void comer(){
        this.cuentaComidas++;
        System.out.println(this.nombre + " comiendo");
        esperarTiempoAzar();
    }

    private void pensar(){
        System.out.println(this.nombre + " pensando");
        esperarTiempoAzar();
    }

    private void esperarTiempoAzar() {
        int s = new Random().nextInt(4) + 1;
        try { 
            Thread.sleep(s * 1000);
        } catch (InterruptedException e) {
            doYourThing = false;
        }
    }

}
