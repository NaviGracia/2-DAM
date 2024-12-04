import java.util.Random;


public class Reloj {
    private String[] ganador = new String[3];
    private int contadorGanadores = 0;
    private int marcaAnterior = 0;
    
    public Reloj() {}

    public synchronized int generarSegundos(int numeroSiesta, String nombre, int ganadas){
        Random random = new Random();
        int seg = random.nextInt(7)+5;
        if (seg <= 10) {
            if (seg == marcaAnterior) {
                this.contadorGanadores+=1;
                this.ganador[this.contadorGanadores] = nombre;
            }else if (seg > marcaAnterior) {
                this.contadorGanadores = 0;
                this.ganador[contadorGanadores] = nombre;
                this.marcaAnterior = seg;
            }else{
                this.marcaAnterior = seg;
            }   
        }
        return seg;
    }

    public boolean comprobarGanador(String nombre){
        boolean ganador = false;
        for (int i = 0; i <= contadorGanadores; i++) {
            if (this.ganador[i].equals(nombre)) {
                ganador = true;
            }
        }
        return ganador;
    }

    public void reiniciarReloj(){
        this.marcaAnterior = 0;
        this.contadorGanadores = 0;
    }
}