package main;

import java.util.Scanner;

import ed.tda.Cola;
import juego.ControladorJuego;
import modelo.Jugador;
import modelo.Mazo;
import util.Validaciones;

/**
 * Clase principal del proyecto
 * Esqueleto para el punto de entrada de la aplicación
 */

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean jugarNuevamente = true;
        boolean mantenerJugadores = false;

        // Creamos la cola de jugadores afuera del bucle para mantenerlos entre partidas
        Cola<Jugador> jugadores = new Cola<>(4);

        // Incio del programa
        System.out.println();
        System.out.println("     ♠️♦️♣️♥️ JUEGO DE CARTAS ♠️♦️♣️♥️ ");
        System.out.println("===========================================");
        System.out.println();

        System.out.println("Bienvenido al juego de cartas");
        System.out.println("La partida se juega con 4 jugadores");
        System.out.println();

        while (jugarNuevamente) {

            // Creamos un nuevo mazo en CADA partida
            Mazo mazo = new Mazo();
            
            if (!mantenerJugadores) {
                // si son jugadores nuevos, se crea una nueva cola de jugadores, renovamos
                jugadores = new Cola<>(4);


            System.out.println("===== REGISTRO DE JUGADORES =====");
            System.out.println();

            // Registramos los cuatro jugadores
            for (int i = 1; i <= 4; i++) {

                System.out.println("* Jugador " + i);

                String nombre = Validaciones.leerTexto(scanner, " - Nombre: ", Validaciones.LETRAS_MIN_TEXTO);
                String apellido = Validaciones.leerTexto(scanner, " - Apellido: ", Validaciones.LETRAS_MIN_TEXTO);
                int edad = Validaciones.leerEdad(scanner);

                Jugador jugador = new Jugador(nombre, apellido, edad);

                jugadores.encolar(jugador);

                System.out.println();
            }

            System.out.println("----------------------------------------------------");
            System.out.println(" Los 4 jugadores fueron registrados correctamente");
            System.out.println("----------------------------------------------------");
            System.out.println();

            } else {
                System.out.println("===== NUEVA PARTIDA =====");
                System.out.println("Preparando a los mismos jugadores...");
                
                // Vaciamos las cartas que los jugadores ganaron en la partida anterior
                for (int i = 0; i < 4; i++) {
                    Jugador jugador = jugadores.desencolar();
                    jugador.vaciarPozo();
                    jugadores.encolar(jugador); // Lo volvemos a meter en la cola
                }
            }

            // Preguntamos las rondas para esta partida
            System.out.println();
            System.out.println("===== RONDAS =====");
            int cantidadRondas = Validaciones.leerCantidadRondas(scanner);

            // Confirmamos si quieren iniciar la partida
            System.out.print("¿Desea comenzar la partida? (s/n): ");
            // Validamos que la respuesta si o si s/n
            String respuesta = Validaciones.leerRespuestaSN(scanner);

            if (respuesta.equalsIgnoreCase("s")) {
                // Arrancamos la partida usando cantidadRondas
                ControladorJuego controlador = new ControladorJuego(mazo, jugadores, cantidadRondas);
                controlador.iniciarPartida();

                // Preguntas del final (solo aparecen si efectivamente se jugó)
                System.out.println();
                System.out.print("¿Desea jugar otra partida? (s/n): ");
                // validamos la respuesta
                String respPartida = Validaciones.leerRespuestaSN(scanner);

                if (respPartida.equalsIgnoreCase("s")) {
                    System.out.print("¿Desea mantener los mismos jugadores? (s/n): ");
                    String respJugadores = Validaciones.leerRespuestaSN(scanner);
                    mantenerJugadores = respJugadores.equalsIgnoreCase("s");
                    System.out.println();
                } else {
                    jugarNuevamente = false;
                }

            } else {
                // Si se arrepiente, cancelamos la partida y salimos del bucle
                System.out.println();
                System.out.println("Partida cancelada. ¡Hasta luego!");
                jugarNuevamente = false;
            }
        } 

        
        // Cierre del programa
        System.out.println();
        System.out.println("========================================");
        System.out.println("   ♠️♦️♣️♥️ Fin del Juego ♠️♦️♣️♥️ ");
        System.out.println("========================================");

        scanner.close();
    }
}
