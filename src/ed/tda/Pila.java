package ed.tda;

/**
 * TDA Pila basado internamente en TDA Arreglo (LIFO - Last In, First Out).
 * Utilizado para el Pozo de Cartas de cada jugador.
 * @param <T> Tipo de dato en la pila.
 */
public class Pila<T> {
    private Arreglo<T> arreglo;

    public Pila(int capacidadMax) {
        this.arreglo = new Arreglo<>(capacidadMax);
    }

    /**
     * Apila (inserta) un elemento en la cima de la pila.
     */
    public void apilar(T elemento) {
        if (estaLlena()) {
            throw new IllegalStateException("La pila está llena (Overflow).");
        }
        arreglo.insertar(elemento);
    }

    /**
     * Desapila y retorna el elemento en la cima de la pila.
     */
    public T desapilar() {
        if (estaVacia()) {

            throw new IllegalStateException("La pila está vacía (Underflow)");
        }
        
        return arreglo.eliminarUltimo();
    }

    /**
     * Retorna el elemento en la cima sin removerlo.
     */
    public T verCima() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return arreglo.obtener(arreglo.longitud() - 1);
    }

    public boolean estaVacia() {
        return arreglo.estaVacio();
    }

    public boolean estaLlena() {
        return arreglo.estaLleno();
    }

    public int tamanio() {
        return arreglo.longitud();
    }
}
