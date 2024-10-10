package Actividad2;
public class TareaCompleja implements Runnable{
    Counter c;
    
    public TareaCompleja() {
        this.c = new Counter();
    }

    @Override
    public void run() {
        c.increase();
        Thread hiloActual = Thread.currentThread();
        String nombreHilo = hiloActual.getName();
        System.out.println( "Hilo: " + nombreHilo + " Valor Counter: " + c.getCount());
    }
}
