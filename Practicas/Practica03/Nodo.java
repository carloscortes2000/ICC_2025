/**
 * Representa un nodo de una lista ligada.
 * Cada nodo almacena un elemento de tipo int y una referencia al siguiente nodo.
 */
public class Nodo {
    int elemento;   // Almacena el valor entero del nodo.
    Nodo siguiente; // Referencia al siguiente nodo en la lista.

    /**
     * Constructor de la clase Nodo.
     *
     * @param elemento El valor entero que se asignará al nodo.
     */
    public Nodo(int elemento) {
        this.elemento = elemento;
        this.siguiente = null;
    }
}
