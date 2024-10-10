public class LanzaTriangulos {
    public static void main(String[] args) {
        Triangulo vHilos[] = new Triangulo[10];
        for (int i=0; i<10;i++){
            double base=(double) (Math.random()*10.4); 
            double altura=(double) (Math.random()*10); 
            vHilos[i]=new Triangulo(base, altura);
            Thread hilo=new Thread(vHilos[i]);
            hilo.setName("Triangulo " + i);

            if (i==0){
                hilo.setPriority(Thread.MAX_PRIORITY);
            } else hilo.setPriority(Thread.MIN_PRIORITY);
            hilo.start();
        }
    }
}


