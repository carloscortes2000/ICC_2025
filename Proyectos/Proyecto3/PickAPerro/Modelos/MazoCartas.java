package Modelos;

/**
 * Representa las 30 cartas disponibles en la mesa.
 */
public class MazoCartas {
    private final ListaCarta<Carta> mesa = new ListaCarta<>();

    /**
     * Inicializa la mesa con 30 cartas del mazo de cartas.
     */
    public MazoCartas(Baraja mazo) {
        for (int i = 0; i < 30; i++) {
            mesa.agregarElemento(mazo.robarCarta());
        }
    }

    /**
     * Devuelve las cartas disponibles en mesa.
     */
    public ListaCarta<Carta> obtenerCartas() {
        return mesa;
    }

    /**
     * Toma y elimina la carta en el índice indicado.
     */
    public Carta tomarCartaDeMesa(int indice) {
        return mesa.eliminarElemento(indice);
    }

    /**
     * Rellena los huecos hasta 30 cartas.
     */
    public void reponerCartas(Baraja mazo) {
        while (mesa.obtenerTamaño() < 30 && mazo.cartasRestantes() > 0) {
            mesa.agregarElemento(mazo.robarCarta());
        }
    }
}