package Modelos;

/**
 * Representa una carta del juego Pick-a-Perro con cinco atributos:
 * tamaño, color, número de brazos, usa gafas y palomitas.
 */
public class Carta {

    
    public enum Tamaño { Pequeño, Grande }

    private final Tamaño tamaño;
    private final String color;
    private final int brazos;       
    private final boolean gafas;    
    private final boolean palomitas;

    /**
     * Constructor de Carta que crea una carta con atributos dados.
     * @param tamaño tamaño del perro
     * @param color color del perro
     * @param brazos número de brazos
     * @param gafas true si lleva gafas
     * @param palomitas true si lleva palomitas
     */
    public Carta(Tamaño tamaño, String color, int brazos, boolean gafas, boolean palomitas) {
        this.tamaño = tamaño;
        this.color = color;
        this.brazos = brazos;
        this.gafas = gafas;
        this.palomitas = palomitas;
    }

    /**
     * Cuenta cuántas características difieren respecto a otra carta.
     * @param otra carta con la que se compara
     * @return número de atributos distintos
     */
    public int contarDiferencias(Carta otra) {
        int diferencias = 0;
        if (this.tamaño != otra.tamaño) {
            diferencias++;
        }
        if (!this.color.equals(otra.color)) {
            diferencias++;
        }
        if (this.brazos != otra.brazos) {
            diferencias++;
        }
        if (this.gafas != otra.gafas) {
            diferencias++;
        }
        if (this.palomitas != otra.palomitas) {
            diferencias++;
        }
        return diferencias;
    }

    /**
     * Representación en cadena de las cartas con colores y emojis en terminal .
     */
    @Override
    public String toString() {
        String colorCarta;
        switch (color.toLowerCase()) {
            case "red": {
                colorCarta = "\u001B[91mRojo\u001B[0m";
                break;
            }
            case "green": {
                colorCarta = "\u001B[92mVerde\u001B[0m";
                break;
            }
            default: {
                colorCarta = color;
                break;
            }
        }

        String brazosCarta;
        if (brazos == 1) {
            brazosCarta = "💪";
        } else {
            brazosCarta = "💪💪";
        }

        String gafasCarta = gafas ? "🤓" : "🙃";
        String palomitasCarta = palomitas ? "🍿" : "🧺";

        return String.format("[%s, %s, %s, %s, %s]", tamaño, colorCarta, brazosCarta, gafasCarta, palomitasCarta);
    }
}