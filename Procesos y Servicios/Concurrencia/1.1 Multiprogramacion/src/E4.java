import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class E4 {
    public static void main(String[] args) {
        String[] comandos = {"bash", "-c", "ls -l"};
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.redirectErrorStream(true);
        try {
            Process terminal = pb.start();
            InputStream is = terminal.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String linea;
            
            while((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            is.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
