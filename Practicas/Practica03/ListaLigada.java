/**
 * Se implementa una lista simplemente ligada, la cuál
 * permite insertar, eliminar elementos y mostrar los 
 * elementos de la lista
 */
public class ListaLigada {
    private Nodo cabeza;   // Referencia al primer nodo de la lista.
    private int cantidad;  // Número de elementos en la lista.

    /**
     * Constructor de la clase que inicializa la cabeza en null y la cantidad en 0.
     * 
     */
    public ListaLigada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    /**
     * Método para insertar un elemento al final de la lista.
     *
     * @param elemento El valor entero que se insertará en la lista.
     */
    public void insertar(int elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            // Si la lista está vacía, el nuevo nodo se será la cabeza.
            cabeza = nuevo;
        } else {
            // En caso contrario, se recorre la lista hasta el último nodo.
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            // Agregamos el nuevo nodo al final de la lista.
            temp.siguiente = nuevo;
        }
        cantidad++; // Se incrementa el contador para el número de elementos.
    }

    /**
     * Método para eliminar el primer nodo que contenga el elemento especificado.
     *
     * @param elemento El valor entero que se desea eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminar(int elemento) {

        if (cabeza == null) {
            System.out.println("No es posible eliminar, la lista está vacía.");
            return false;
        }

        if (cabeza.elemento == elemento) {
            cabeza = cabeza.siguiente; // Actualiza la cabeza al siguiente nodo.
            cantidad--;
            return true;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.elemento != elemento) {
            actual = actual.siguiente;
        }
        // Si se encontró el elemento, lo eliminamos.
        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            cantidad--;
            return true;
        } else {
            System.out.println("No se encontró el elemento.");
            return false;
        }
    }

    /**
     * Método extra que inserta un elemento en una posición específica de la lista.
     *
     * @param elemento El valor entero a insertar.
     * @param indice   La posición en la que se desea insertar el elemento.
     */
    public void insertarIndice(int elemento, int indice) {
        // Verificamos que el índice esté dentro del rango permitido.
        if (indice < 0 || indice >= cantidad) {
            System.out.println("Índice no válido.");
            return;
        }
        Nodo nuevo = new Nodo(elemento);
        // Si se inserta en la posición 0, actualizamos la cabeza.
        if (indice == 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            // Recorremos la lista hasta llegar al nodo anterior al índice deseado.
            Nodo actual = cabeza;
            int contador = 0;
            while (contador < indice - 1) {
                actual = actual.siguiente;
                contador++;
            }
            // Ajustamos los enlaces del nuevo nodo en la posición deseada.
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        cantidad++; // Se incrementa el contador para el número de elementos.
    }

    /**
     * Método que le da a los objetos una representación en cadena, 
     * tanto cuando la lista es vacı́a como cuando tiene elementos.
     *
     * @return Una cadena que representa la lista.
     */
    @Override
    public String toString() {
        if (cabeza == null) {
            return "La lista está vacía.";
        }
        StringBuilder sb = new StringBuilder();
        Nodo temp = cabeza;
        // Recorremos la lista y se construye la cadena.
        while (temp != null) {
            sb.append(temp.elemento);
            if (temp.siguiente != null) {
                sb.append(" -> ");
            }
            temp = temp.siguiente;
        }
        return sb.toString();
    }
}

