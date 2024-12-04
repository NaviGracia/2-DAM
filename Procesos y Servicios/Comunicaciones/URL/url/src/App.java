import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
         // URL a leer
        String urlString = "https://www.geeksforgeeks.org/jvm-works-jvmarchitecture/index.php";
        String outputFileName = "fichero.php";

        try {
            // Crear objeto URL
            URL url = new URL(urlString);

            // Abrir InputStream desde la URL
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Crear FileWriter para guardar el contenido
            FileWriter fileWriter = new FileWriter(outputFileName);

            // Leer y escribir línea por línea
            String line;
            while ((line = reader.readLine()) != null) {
                fileWriter.write(line + System.lineSeparator());
            }

            // Cerrar recursos
            reader.close();
            fileWriter.close();

            System.out.println("Contenido guardado en " + outputFileName);
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
