import java.util.Scanner;

public class ProcessLauncher {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("kcalc");
            Process p = pb.start();
            String respuesta;
            do {
                System.out.println("Desea finalizar el proceso? (Y para si, N para no)");
                respuesta = sc.nextLine();
                if (respuesta.equalsIgnoreCase("y")) {
                    System.out.println("PID: " + p.pid());
                    System.out.println("INFO: " + p.info());
                    p.destroy();
                } else if (respuesta.equalsIgnoreCase("n")) {
                    Thread.sleep(10000);
                } else{
                    System.out.println("Letra mal introducida.");
                    Thread.sleep(2000);
                }
            } while (!respuesta.equalsIgnoreCase("y"));
            System.out.println("Final");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

