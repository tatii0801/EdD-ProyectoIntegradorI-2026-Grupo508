package modelo;

import ed.tda.Arreglo;
import ed.tda.Pila;
import java.util.Random;

/**
 * Clase Mazo reimplementada utilizando el TDA Pila (LIFO) propio.
 * Administra los 52 naipes franceses requeridos por el proyecto.
 */
public class Mazo {
    private Pila<Carta> cartas;

    public Mazo() {
        inicializarYMezclar();
    }
    

    private void inicializarYMezclar() {
        Arreglo<Carta> mazoTemporal = new Arreglo<>(52);
        String[] palos = {"Trébol", "Pica", "Corazones", "Diamantes"};

        // Genera los 52 naipes (4 palos, valores del 1 al 13) con estado disponible
        for (String palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                mazoTemporal.insertar(new Carta(palo, valor, true));
            }
        }

        Random rand = new Random();
        int n = mazoTemporal.longitud();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            mazoTemporal.intercambiar(i, j);
        }

        this.cartas = new Pila<>(52);
        for (int i = 0; i < mazoTemporal.longitud(); i++) {
            this.cartas.apilar(mazoTemporal.obtener(i));
        }
    }

    /**
     * Extrae la carta ubicada en la cima de la Pila, cambia su estado a no disponible
     * y la retorna para la ronda actual.
     */
    public Carta sacarCarta() {
        if (tieneCartasDisponibles()) {
            Carta carta = cartas.desapilar();
            carta.setDisponible(false); // Actualiza el estado a "no disponible" (disponible = false)
            return carta;
        }
        return null; // Retorna null si el mazo se ha agotado
    }

    /**
     * Consulta si la Pila aún contiene naipes.
     */
    public boolean tieneCartasDisponibles() {
        return !cartas.estaVacia();
    }

    /**
     * Retorna la cantidad de naipes restantes en la Pila del mazo.
     */
    public int cartasRestantes() {
        return cartas.tamanio();
    }
}
