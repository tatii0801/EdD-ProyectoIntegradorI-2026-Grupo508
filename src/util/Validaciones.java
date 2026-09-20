package util;

import java.util.Scanner;

/**
 * Validaciones y lectura de datos ingresados por consola
 * Todos los métodos son estáticos
 */
public class Validaciones {

    public static final int LETRAS_MIN_TEXTO = 3;
    public static final int EDAD_MINIMA = 8;
    public static final int EDAD_MAXIMA = 105;

    // Evita que se creen instancias
    private Validaciones() {
    }

    /**
     * Lee un texto ingresado por el usuario y valida que contenga solo letras y espacios, con una cantidad mínima de letras. 
     * Repite la lectura hasta que el dato sea válido
     *
     * @param scanner lector de entrada por consola
     * @param mensaje texto que se muestra al usuario al pedir el dato
     * @param minLetras cantidad mínima de letras que debe tener el texto
     * @return el texto valido ingresado, sin espacios al inicio ni al final
     */
    public static String leerTexto(Scanner scanner, String mensaje, int minLetras) {

        String texto;

        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (!esTextoValido(texto, minLetras)) {

                System.out.println("Dato invalido: use solo letras y espacios (mínimo " + minLetras + " letras)");
            }

        } while (!esTextoValido(texto, minLetras));

        return texto;
    }

    /**
     * Valida que el texto contenga solo letras y espacios, y que tenga al menos la cantidad minima de letras indicada (los espacios no se cuentan)
     *
     * @param texto texto a validar
     * @param minLetras cantidad minima de letras
     * @return true si el texto es valido, false en caso contrario
     */
    public static boolean esTextoValido(String texto, int minLetras) {

        if (texto.isEmpty()) {

            return false;
        }

        int letras = 0;

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);

            if (Character.isLetter(c)) {

                letras++;

            } else if (c != ' ') {

                return false;
            }
        }

        return letras >= minLetras;
    }

    /**
     * Lee la edad ingresada por el usuario y valida que sea un numero entero dentro del rango establecido
     * Repite la lectura hasta que sea valida
     *
     * @param scanner lector de entrada por consola
     * @return la edad valida ingresada
     */
    public static int leerEdad(Scanner scanner) {

        String texto;

        do {
            System.out.print("Edad: ");
            texto = scanner.nextLine().trim();

            if (!esEdadValida(texto)) {

                System.out.println("Edad invalida: ingrese un numero entero entre " + EDAD_MINIMA + " y " + EDAD_MAXIMA);
            }

        } while (!esEdadValida(texto));

        return Integer.parseInt(texto);
    }

    /**
     * Valida que el texto este formado solo por digitos y que su valor este dentro del rango que definimos
     *
     * @param texto texto a validar
     * @return true si la edad es valida, false en caso contrario
     */
    public static boolean esEdadValida(String texto) {

        if (texto.isEmpty() || texto.length() > 3) {

            return false;
        }

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);

            if (c < '0' || c > '9') {

                return false;
            }
        }

        int edad = Integer.parseInt(texto);

        return edad >= EDAD_MINIMA && edad <= EDAD_MAXIMA;
    }

    /**
     * RONDAS: Lee la cantidad de rondas ingresada por el usuario y valida que sea un numero entero dentro del rango establecido
     * Repite la lectura hasta que sea valida
     *
     * @param scanner lector de entrada por consola
     * @return la cantidad de rondas valida ingresada
     */
    public static int leerCantidadRondas(Scanner scanner) {
        String texto;
        do {
            System.out.print("¿Cuántas rondas desean jugar? (1 a 13. Recomendado para partida rápida: 3): ");
            texto = scanner.nextLine().trim();
            if (!esRondaValida(texto)) {
                System.out.println("* Cantidad inválida: ingrese un número entero entre 1 y 13.");
            }
        } while (!esRondaValida(texto));
        return Integer.parseInt(texto);
    }

    /**
     * Valida que el texto esté formado solo por dígitos y que su valor esté dentro del rango de 1 a 13
     * 
     * @param texto texto a validar
     * @return true si la cantidad de rondas es válida, false en caso contrario
     */
    public static boolean esRondaValida(String texto) {
        if (texto.isEmpty() || texto.length() > 2) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        int rondas = Integer.parseInt(texto);
        return rondas >= 1 && rondas <= 13;
    }
}
