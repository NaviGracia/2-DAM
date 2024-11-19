import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Amigo implements Runnable{
    private String nombre;
    private String jugada;
    private CyclicBarrier barrier;

    public Amigo(String nombre, CyclicBarrier barrier) {
        this.nombre = nombre;
        this.barrier = barrier;
    }

    @Override
    public void run(){
        Random random = new Random();
        
        // Generar un número aleatorio entre 1, 2 y 3
        int numero = random.nextInt(3) + 1;

        switch (numero) {
            case 1:
                this.jugada = "Piedra";
                break;
            case 2:
                this.jugada = "Papel";
                break;
            case 3:
                this.jugada = "Tijera";
                break;
        }
        esperarTiempoAzar();
        System.out.println(nombre + " ha acabado de pensar a las " + sacarFechaActual());
        try {
            barrier.await();   
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nombre + ": " + jugada);
        
    }

    private void esperarTiempoAzar() {
        int s = new Random().nextInt(10) + 1;
        try { 
            Thread.sleep(s * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String sacarFechaActual(){
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = fechaActual.format(formato);
        return fechaFormateada;
    }
}
