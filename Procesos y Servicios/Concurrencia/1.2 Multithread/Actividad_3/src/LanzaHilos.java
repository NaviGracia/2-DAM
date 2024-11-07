public class LanzaHilos {
    public static void main(String[] args) throws Exception {
        int nfilosofos=5;
        String nombres[] = new String[nfilosofos];
        iniciarNombres(nombres);

        //Creación de Objetos
        Filosofo filosofos[] = new Filosofo[nfilosofos];
        GestionPalillos gp;
        gp = new GestionPalillos(nfilosofos);
        Thread[] hilo = new Thread[nfilosofos];

        //Configuramos los palillos asociados a cada filósofo
        for (int i=1;i<nfilosofos;i++){
            filosofos[i] = new Filosofo(gp, i, i-1, nombres[i]);
            hilo[i] = new Thread(filosofos[i]);
        }

        //Filosófo 0 caso especial, se hace por separado
        filosofos[0] = new Filosofo(gp, 0 , nfilosofos - 1, nombres[0]);
        hilo[0] = new Thread(filosofos[0]);

        //Lanzamos todos los hilos
        for (int i = 0; i < nfilosofos; i++) {
            hilo[i].start();
        }

        //Esperando finalización de hijos
        for (int i = 0; i < nfilosofos; i++) {
            try {
                hilo[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Detención de hilos después de 10s
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println("Fallo a la espera de main");
        }
        for (int i = 0; i < hilo.length; i++) {
            hilo[i].interrupt();
        }
    }

    public static void iniciarNombres(String nombres[]){
        nombres[0] = "Aristóteles";
        nombres[1] = "Pericles";
        nombres[2] = "Sócrates";
        nombres[3] = "J. César";
        nombres[4] = "Eivar";
    }
}
