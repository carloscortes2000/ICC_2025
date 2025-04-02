public class Main {
    public static void main(String[] args) {
        
        ListaLigada lista = new ListaLigada();

        System.out.println(lista.toString());

        lista.insertar(13);
        lista.insertar(21);
        lista.insertar(45);
        lista.insertar(67);
        lista.insertar(83);

        System.out.println("La lista actual es la siguiente: " + lista.toString());

        lista.eliminar(13);
        System.out.println("La lista actual después de eliminar la cabeza es la siguiente: " + lista.toString());

        lista.eliminar(45);
        System.out.println("La lista actual después de eliminar un elemento de en medio es la siguiente: " + lista.toString());

        lista.insertarIndice(25, 2);
        System.out.println("La lista actual después de insertar 25 en el índice 2 es la siguiente: " + lista.toString());

        lista.insertarIndice(35, 10);
        System.out.println("La lista actual después de tratar de insertar 35 en un índice no valido es la siguiente: " + lista.toString());
    }
}
