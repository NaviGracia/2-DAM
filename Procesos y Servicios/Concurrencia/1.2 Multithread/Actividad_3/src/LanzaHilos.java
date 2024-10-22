public class LanzaHilos {
    public static void main(String[] args) throws Exception {
        int filosofos=5;
        Thread[] filosofosAsociados;
        filosofosAsociados = new Thread[filosofos];
        boolean comiendo = true;
        for (int i=0;i<filosofos;i++){
            Thread hilo = new Thread(new Filosofo(filosofos, true));
            hilo.setName("Hilo: "+i);
            if (comiendo == true) {
                comiendo = false;  
                hilo.start();
                hilo.join();
            }else if(comiendo == false){
                comiendo = true;
            }
            filosofosAsociados[i] = hilo;
        }
    }
}
