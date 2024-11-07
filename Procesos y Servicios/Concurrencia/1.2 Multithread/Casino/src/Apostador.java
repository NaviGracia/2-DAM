import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Apostador implements Runnable{
    Banca bc;
    String nombre;
    int apuesta;
    int saldo;
    CyclicBarrier barrier;

    public Apostador(Banca bc, String nombre, CyclicBarrier barrier) {
        this.bc = bc;
        this.nombre = nombre;
        this.saldo = 1000;
        this.barrier = barrier;
    }

    @Override
    public void run(){
        while (true) {
            if(saldo == 0){
                saldo -= 10;
                apuesta = (int) (Math.random() * 37);
                try {
                    if (bc.comprobarApuesta(apuesta)==true) {
                        saldo += 360;
                        System.out.println( nombre + ": Apuesta Ganada | Saldo: " + saldo);
                    }else{
                        System.out.println( nombre + ": Apuesta Perdida | Saldo: " + saldo);
                    }
                    barrier.await();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }
}
