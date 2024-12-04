import java.util.concurrent.CyclicBarrier;


public class Jugador implements Runnable{
    private String nombre;
    private int segundos;
    private Reloj reloj;
    public CyclicBarrier barrier;
    public int ganadas;

    public Jugador(String nombre, Reloj reloj, CyclicBarrier barrier) {
        this.nombre = nombre;
        this.reloj = reloj;
        this.barrier = barrier;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            this.segundos = reloj.generarSegundos(i, this.nombre, this.ganadas);
            try {
                Thread.sleep(this.segundos * 1000);
                barrier.await();
                if (reloj.comprobarGanador(this.nombre) == true) {
                    System.out.println("En la ronda " + i + " " + this.nombre + " ha ganado esperando " + this.segundos + " segundos");
                    this.ganadas+=1;
                }
                barrier.await();
                reloj.reiniciarReloj();
            } catch (Exception e) {
                System.out.println("Excepción en la barrera");
            }   
        }
        System.out.println(this.nombre + " ha ganado " + this.ganadas + " veces.");
    }
}
