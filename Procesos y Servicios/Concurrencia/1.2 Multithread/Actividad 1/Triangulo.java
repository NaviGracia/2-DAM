import java.math.BigDecimal;
import java.math.RoundingMode;

class Triangulo implements Runnable{
    double base;
    double altura;

    public Triangulo(double base, double altura) {
        this.base = round(base, 2);
        this.altura = round(altura, 2);
    }

    @Override public void run() {
        double num=0;
        Thread hilo=Thread.currentThread();
        String miNombre=hilo.getName();
        
        System.out.println("Calculando Área " + miNombre + " con Base: " + base + " y Altura: " + altura);
        try {
            double numDivisionArea = 2;
            double area = (base*altura)/numDivisionArea; 
            System.out.println( "Área " + miNombre + ": " + round(area, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        num++;
        
        System.out.println(miNombre + " Terminado");
        try {
            hilo.join();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

            BigDecimal bd = BigDecimal.valueOf(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
    }
}