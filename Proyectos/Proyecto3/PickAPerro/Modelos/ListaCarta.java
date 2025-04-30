package Modelos;

/**
 * Implementación propia de lista ligada genérica.
 */
public class ListaCarta<T> {

    /** Clase que representa un nodo de una lista */
    private static class Nodo<T> {

        T valor;
        Nodo<T> siguiente;

        Nodo(T v) { 
            valor = v; 
            siguiente = null; 
        }
    }

    private Nodo<T> cabeza, cola;
    private int tamaño;

    /** Crea una lista vacía */
    public ListaCarta() {
        cabeza = cola = null;
        tamaño = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * @param elemento elemento a agregar
     */
    public void agregarElemento(T elemento) {
        Nodo<T> nodo = new Nodo<>(elemento);
        if (cola != null) {
            cola.siguiente = nodo;
        }
        cola = nodo;
        if (cabeza == null) {
            cabeza = nodo;
        }
        tamaño++;
    }

    /**
     * Obtiene el elemento en la posición indicada.
     * @param indice índice (0-based)
     * @return elemento en indice
     */
    public T obtenerElemento(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException();
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    /**
     * Elimina y devuelve el elemento en la posición indice.
     * @param indice posición a eliminar
     * @return elemento eliminado
     */
    public T eliminarElemento(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException();
        }
        if (indice == 0) {
            T valor = cabeza.valor;
            cabeza = cabeza.siguiente;
            if (cabeza == null) {
                cola = null;
            }
            tamaño--;
            return valor;
        }
        Nodo<T> previo = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            previo = previo.siguiente;
        }
        Nodo<T> aEliminar = previo.siguiente;
        previo.siguiente = aEliminar.siguiente;
        if (aEliminar == cola) {
            cola = previo;
        }
        tamaño--;
        return aEliminar.valor;
    }

    /**
     * Vacía completamente la lista.
     */
    public void vaciarLista() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    /**
     * Tamaño actual de la lista.
     * @return número de elementos
     */
    public int obtenerTamaño() {
        return tamaño;
    }

    /**
     * Indica si la lista está vacía.
     * @return true si vacía
     */
    public boolean estaVacia() {
        return tamaño == 0;
    }
}