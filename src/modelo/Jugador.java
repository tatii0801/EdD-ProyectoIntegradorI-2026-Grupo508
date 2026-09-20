package modelo;

import ed.tda.Pila;

/**
 * Representa a un jugador de la partida.
 */
public class Jugador {

    private String nombre;
    private String apellido;
    private int edad;

    private Pila<Carta> pozo;

    /**
     * Constructor del jugador.
     */
    public Jugador(String nombre, String apellido, int edad) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;

        // Un jugador puede llegar a recibir hasta 52 cartas
        this.pozo = new Pila<>(52);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    /**
     * Agrega una carta al pozo del jugador.
     */
    public void recibirCarta(Carta carta) {
        pozo.apilar(carta);
    }

    /**
     * Calcula el puntaje sumando los valores de las cartas.
     *
     * Las cartas se sacan temporalmente del pozo utilizando
     * una pila auxiliar y luego se vuelven a colocar.
     */
    public int calcularPuntaje() {

        int puntaje = 0;

        Pila<Carta> auxiliar = new Pila<>(52);

        // Sacamos las cartas y calculamos el puntaje
        while (!pozo.estaVacia()) {

            Carta carta = pozo.desapilar();

            puntaje += carta.getValor();

            auxiliar.apilar(carta);
        }

        // Volvemos a colocar las cartas en el pozo
        while (!auxiliar.estaVacia()) {

            pozo.apilar(auxiliar.desapilar());
        }

        return puntaje;
    }

     /**
     *Vacía el pozo de cartas del jugador.
     * Es necesario si se reutilizan los jugadores para una nueva partida.
     */
    
    public void vaciarPozo() {
        while (!pozo.estaVacia()) {
            pozo.desapilar();
        }
    }
}
