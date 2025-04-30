package Modelos;

import java.util.Random;

/**
 * Representa el mazo de 96 cartas.
 */
public class Baraja {

    private final ListaCarta<Carta> cartas = new ListaCarta<>();
    private final Random random;

    /**
     * Crea la baraja con semilla fija y genera las 96 cartas.
     * @param semilla semilla para reproducibilidad
     */
    public Baraja(long semilla) {
        random = new Random(semilla);
        for (Carta.Tamaño t : Carta.Tamaño.values()) {
            for (String color : new String[]{"red", "green"}) {
                for (int brazos : new int[]{1, 2}) {
                    for (boolean gafas : new boolean[]{true, false}) {
                        for (boolean palomitas : new boolean[]{true, false}) {
                            for (int copia = 0; copia < 3; copia++) {
                                cartas.agregarElemento(new Carta(t, color, brazos, gafas, palomitas));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Barajea el mazon de cartas
     */
    public void barajar() {

        int n = cartas.obtenerTamaño();
        Carta[] arreglo = new Carta[n];
        
        for (int i = 0; i < n; i++) {
            arreglo[i] = cartas.obtenerElemento(i);
        }
        
        for (int i = n - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);
            Carta temp = arreglo[i];
            arreglo[i] = arreglo[j];
            arreglo[j] = temp;
        }
        
        cartas.vaciarLista();
        
        for (Carta c : arreglo) {
            cartas.agregarElemento(c);
        }
    }

    /**
     * Toma la primera carta de la baraja.
     * @return carta o null si está vacía
     */
    public Carta robarCarta() {
        if (cartas.estaVacia()) {
            return null;
        } else {
            return cartas.eliminarElemento(0);
        }
    }

    /**
     * Cartas restantes en la baraja.
     * @return número de cartas
     */
    public int cartasRestantes() {
        return cartas.obtenerTamaño();
    }
}