public class LanzaHilos {
    public static void main(String[] args) throws Exception {
        GestionPalillos gp = new GestionPalillos();
        gp.repartirPalillos();
        
        int nfilosofos=6;
        Thread[] filosofos;
        filosofos = new Thread[nfilosofos];
        for (int i=1;i<nfilosofos;i++){
            Thread hilo = new Thread(new Filosofo());
            hilo.setName("Hilo: "+i);

            Filosofo.sentarFilosofo(i, hilo.getName());
           
            hilo.start();
        }
    }
}
