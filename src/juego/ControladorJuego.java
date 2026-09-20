package juego;

import ed.tda.Arreglo;
import ed.tda.Cola;
import modelo.Carta;
import modelo.Jugador;
import modelo.Mazo;

/**
 * Controla el desarrollo de la partida.
 */
public class ControladorJuego {

    private Mazo mazo;
    private Cola<Jugador> jugadores;
    private int cantidadRondas;

    public ControladorJuego(
            Mazo mazo,
            Cola<Jugador> jugadores,
            int cantidadRondas) {

        this.mazo = mazo;
        this.jugadores = jugadores;
        this.cantidadRondas = cantidadRondas;
    }

    /**
     * Inicia y desarrolla la partida.
     */
    public void iniciarPartida() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          INICIO DE LA PARTIDA");
        System.out.println("======================================");
        System.out.println();

        for (int ronda = 1; ronda <= cantidadRondas; ronda++) {

            if (mazo.cartasRestantes() < 4) {
                System.out.println("No hay suficientes cartas para continuar.");
                break;
            }

            jugarRonda(ronda);

            // Rota los turnos para la ronda siguiente
            if (ronda < cantidadRondas) {

                rotarTurnos();
            }
        }

        mostrarResultadosFinales();
    }

    /**
     * Rota la cola de jugadores el primero pasa al final cambiando quien tiene el primer turno en cada ronda
     */
    private void rotarTurnos() {

        jugadores.encolar(jugadores.desencolar());
    }

    /**
     * Realiza una ronda completa.
     */
    private void jugarRonda(int numeroRonda) {

        System.out.println("--------------------------------------");
        System.out.println("           RONDA " + numeroRonda + " DE " + cantidadRondas);
        System.out.println("--------------------------------------");

        
        // Muestra quién está al frente de la cola (recibe la primera carta)
        Jugador primero = jugadores.frente();
        System.out.println("Primer turno: " + primero.getNombre() + " " + primero.getApellido());

        // Arreglo para guardar los jugadores de esta ronda
        Arreglo<Jugador> jugadoresRonda = new Arreglo<>(4);

        // Arreglo para guardar las cartas de esta ronda
        Arreglo<Carta> cartasRonda = new Arreglo<>(4);

        // Cada jugador recibe una carta
        for (int i = 0; i < 4; i++) {

            Jugador jugadorActual = jugadores.desencolar();

            Carta cartaActual = mazo.sacarCarta();

            jugadoresRonda.insertar(jugadorActual);
            cartasRonda.insertar(cartaActual);

            // El jugador vuelve a la cola para la siguiente ronda
            jugadores.encolar(jugadorActual);

            System.out.println(
                    jugadorActual.getNombre()
                    + " "
                    + jugadorActual.getApellido()
                    + " recibe: "
                    + cartaActual.getValor()
                    + " de "
                    + cartaActual.getPalo()
            );
        }

        System.out.println();

        // Buscamos el valor más alto
        int mayorValor = 0;

        for (int i = 0; i < cartasRonda.longitud(); i++) {

            Carta carta = cartasRonda.obtener(i);

            if (carta.getValor() > mayorValor) {
                mayorValor = carta.getValor();
            }
        }

        // Contamos cuántas cartas tienen el valor máximo
        int cantidadMaximos = 0;

        for (int i = 0; i < cartasRonda.longitud(); i++) {

            if (cartasRonda.obtener(i).getValor() == mayorValor) {
                cantidadMaximos++;
            }
        }

        System.out.println("Mayor valor de la ronda: " + mayorValor);

        // Si existe un único ganador
        if (cantidadMaximos == 1) {

            int posicionGanador = 0;

            for (int i = 0; i < cartasRonda.longitud(); i++) {

                if (cartasRonda.obtener(i).getValor() == mayorValor) {
                    posicionGanador = i;
                    break;
                }
            }

            Jugador ganador = jugadoresRonda.obtener(posicionGanador);

            System.out.println();
            System.out.println(">>> GANADOR DE LA RONDA <<<");
            System.out.println(
                    ganador.getNombre()
                    + " "
                    + ganador.getApellido()
            );

            System.out.println("Se queda con las 4 cartas");

            // El ganador recibe las cuatro cartas
            for (int i = 0; i < cartasRonda.longitud(); i++) {

                ganador.recibirCarta(
                        cartasRonda.obtener(i)
                );
            }

        } else {

            // Si hay empate
            System.out.println();
            System.out.println(">>> EMPATE <<<");
            System.out.println(
                    "Hay " + cantidadMaximos
                    + " jugadores con el valor máximo."
            );

            System.out.println(
                    "Cada jugador conserva su propia carta."
            );

            // Cada jugador recibe su propia carta
            for (int i = 0; i < jugadoresRonda.longitud(); i++) {

                Jugador jugador = jugadoresRonda.obtener(i);
                Carta carta = cartasRonda.obtener(i);

                jugador.recibirCarta(carta);
            }
        }

        System.out.println();
    }

    /**
     * Muestra los resultados finales.
     */
    private void mostrarResultadosFinales() {

        System.out.println("======================================");
        System.out.println("          RESULTADOS FINALES");
        System.out.println("======================================");

        // Arreglo de jugadores
        Arreglo<Jugador> jugadoresFinales = new Arreglo<>(4);

        // Arreglo de puntajes
        Arreglo<Integer> puntajes = new Arreglo<>(4);

        int mayorPuntaje = 0;

        // Obtenemos los jugadores de la cola
        for (int i = 0; i < 4; i++) {

            Jugador jugador = jugadores.desencolar();

            jugadoresFinales.insertar(jugador);

            int puntaje = jugador.calcularPuntaje();

            puntajes.insertar(puntaje);

            if (puntaje > mayorPuntaje) {
                mayorPuntaje = puntaje;
            }

            // Lo volvemos a colocar en la cola
            jugadores.encolar(jugador);
        }

        // Mostramos los puntajes
        for (int i = 0; i < jugadoresFinales.longitud(); i++) {

            Jugador jugador = jugadoresFinales.obtener(i);
            int puntaje = puntajes.obtener(i);

            System.out.println(
                    jugador.getNombre()
                    + " "
                    + jugador.getApellido()
                    + ": "
                    + puntaje
                    + " puntos"
            );
        }

        System.out.println();
        System.out.println("Mayor puntaje: " + mayorPuntaje);
        System.out.println();
        System.out.println("Jugador(es) con mayor puntaje:");

        // Buscamos quiénes tienen el mayor puntaje
        for (int i = 0; i < jugadoresFinales.longitud(); i++) {

            if (puntajes.obtener(i) == mayorPuntaje) {

                Jugador jugador = jugadoresFinales.obtener(i);

                System.out.println(
                        "- "
                        + jugador.getNombre()
                        + " "
                        + jugador.getApellido()
                );
            }
        }

        System.out.println();
        System.out.println("======================================");
        System.out.println("            FIN DE LA PARTIDA");
        System.out.println("======================================");
    }
}
