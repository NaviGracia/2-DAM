import java.util.HashMap;

public class GestionPalillos {
    static HashMap<Integer, Boolean> palillos = new HashMap<>();

    public synchronized boolean gestionPalillos(HashMap<Integer, String> filosofos, String nombre) throws Exception {
        int comensal=0;
        boolean comiendo = false;
        for (int numFil : filosofos.keySet()) {
            if (filosofos.get(numFil).equals(nombre)) {
                comensal = numFil;
            }
        }
        switch (comensal) {
            case 1:
                if ((palillos.get(comensal) == false) && (palillos.get(5) == false)) {
                    palillos.replace(comensal, true);
                    palillos.replace(5, true);
                    comiendo = true;
                }else{System.out.println("Palillos Ocupados");}
                break;
            
            case 2:
                if ((palillos.get(comensal) == false) && (palillos.get(comensal-1) == false)) {
                    palillos.replace(comensal, true);
                    palillos.replace(comensal-1, true);
                    comiendo = true;
                }else{System.out.println("Palillos Ocupados");}
                break;
        
            case 3:
                if ((palillos.get(comensal) == false) && (palillos.get(comensal-1) == false)) {
                    palillos.replace(comensal, true);
                    palillos.replace(comensal-1, true);
                    comiendo = true;
                }else{System.out.println("Palillos Ocupados");}
                break;

            case 4:
                if ((palillos.get(comensal) == false) && (palillos.get(comensal-1) == false)) {
                    palillos.replace(comensal, true);
                    palillos.replace(comensal-1, true);
                    comiendo = true;
                }else{System.out.println("Palillos Ocupados");}
                break;

            case 5:
                if ((palillos.get(comensal) == false) && (palillos.get(1) == false)) {
                    palillos.replace(comensal, true);
                    palillos.replace(1, true);
                    comiendo = true;
                }else{System.out.println("Palillos Ocupados");}
                break;
        }
        return comiendo;
    }

   public synchronized boolean soltarPalillos(HashMap<Integer, String> filosofos, String nombre) throws Exception {
        int comensal=0;
        boolean palillosSoltados = false;
        for (int numFil : filosofos.keySet()) {
            if (filosofos.get(numFil).equals(nombre)) {
                comensal = numFil;
            }
        }
        switch (comensal) {
            case 1:
                if ((palillos.get(comensal) == true) && (palillos.get(5) == true)) {
                    palillos.replace(comensal, false);
                    palillos.replace(5, false);
                    palillosSoltados = true;
                }
                break;
            
            case 2:
                if ((palillos.get(comensal) == true) && (palillos.get(comensal-1) == true)) {
                    palillos.replace(comensal, false);
                    palillos.replace(comensal-1, false);
                    palillosSoltados = true;
                }
                break;
        
            case 3:
                if ((palillos.get(comensal) == true) && (palillos.get(comensal-1) == true)) {
                    palillos.replace(comensal, false);
                    palillos.replace(comensal-1, false);
                    palillosSoltados = true;
                }
                break;

            case 4:
                if ((palillos.get(comensal) == true) && (palillos.get(comensal-1) == true)) {
                    palillos.replace(comensal, false);
                    palillos.replace(comensal-1, false);
                    palillosSoltados = true;
                }
                break;

            case 5:
                if ((palillos.get(comensal) == true) && (palillos.get(1) == true)) {
                    palillos.replace(comensal, false);
                    palillos.replace(1, false);
                    palillosSoltados = true;
                }
                break;
        }
        return palillosSoltados;
    }

    

    public void repartirPalillos(){
        palillos.put(1, false);
        palillos.put(2, false);
        palillos.put(3, false);
        palillos.put(4, false);
        palillos.put(5, false);
        System.out.println("Palillos Repartidos");
    }
}
