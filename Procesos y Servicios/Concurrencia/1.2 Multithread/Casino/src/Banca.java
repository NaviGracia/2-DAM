import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;

public class Banca {
    int nRuleta;
    int saldo;
    Apostador apostadores[] = new Apostador[4];
    
    public Banca(Apostador apostadores[]) {
        this.nRuleta = (int) (Math.random() * 37);
        this.saldo = 50000;
        this.apostadores = apostadores;
    }

    public synchronized boolean comprobarApuesta(int nApuesta) throws Exception{
        boolean acierto = false;
        if (nRuleta == nApuesta) {
            acierto = true;
        }else{
            this.saldo+=10;
        }
        return acierto;
    }

    public void girarRuleta(){
        this.nRuleta = (int) (Math.random() * 37);
    }
}