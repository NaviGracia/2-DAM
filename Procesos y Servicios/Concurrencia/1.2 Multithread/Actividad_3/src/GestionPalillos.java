public class GestionPalillos {
    static boolean bol;
    static Thread hilo; 
    
    public GestionPalillos(boolean bol, Thread hilo) {
        this.bol = bol;
        this.hilo = hilo;
    }

    public synchronized boolean gestionPalillos() throws Exception {
        
        return bol;
    }
}
