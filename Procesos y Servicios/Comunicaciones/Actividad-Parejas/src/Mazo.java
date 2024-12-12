import java.util.ArrayList;
import java.util.HashMap;

public class Mazo {
    private String nombre;
    private HashMap<Integer, String> cartasMazo;
    private double precio;
    

    public Mazo(String nombre, double precio) {
        this.nombre = nombre;
        this.cartasMazo = new HashMap<>();
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<Integer, String> getCartasMazo() {
        return cartasMazo;
    }

    public void setCartasMazo(HashMap<Integer, String> cartasMazo) {
        this.cartasMazo = cartasMazo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void insertarCarta(int numero, String nombreCarta){
        this.cartasMazo.put(numero, nombreCarta);
    }

    public void generarMazos(){
        generarMazoRooms();
    }

    public void generarMazoRooms(){
        
    }

    public ArrayList<String> generarCartas(){
        return new ArrayList<>();
    }
}
