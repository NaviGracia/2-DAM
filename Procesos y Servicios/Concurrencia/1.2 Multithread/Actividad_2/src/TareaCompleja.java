public class TareaCompleja implements Runnable{
    Counter c;
    
    public TareaCompleja(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        String nombreHilo = hiloActual.getName();
        c.increase();
        System.out.println( "Hilo: " + nombreHilo + " Valor Counter: " + c.getCount());
    }
}
