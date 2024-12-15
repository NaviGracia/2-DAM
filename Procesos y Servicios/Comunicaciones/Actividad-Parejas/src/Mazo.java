import java.util.ArrayList;

public class Mazo {
    private String nombre;
    private ArrayList<Carta> mazo = new ArrayList<>();
    private double precio;
    

    public Mazo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public ArrayList<Carta> getMazo() {
        return mazo;
    }

    public void setMazo(ArrayList<Carta> mazo) {
        this.mazo = mazo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void generarMazos(){
        generarMazoLifeStealth();
    }

    public void generarMazoLifeStealth(){
        ArrayList<Carta> mazoLifeStealth = new ArrayList<>();
        mazoLifeStealth.add(new Carta(1, "Bloodthirsty Conqueror"));
        this.setMazo(mazoLifeStealth);
    }
}
