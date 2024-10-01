import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ExamenPractico {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Ingrese el nombre del directorio en /home que desee listar:");
        String directorio = "/home/" + sc.nextLine();
        String[] listarDirectorio = {"bash", "-c", "ls -l " + directorio};
        ProcessBuilder pb = new ProcessBuilder(listarDirectorio);
        pb.redirectErrorStream(true);
        try {
            Process terminal = pb.start();
            InputStream is = terminal.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String linea;
            System.out.println("Listando directorio " + directorio);
            while((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            is.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
