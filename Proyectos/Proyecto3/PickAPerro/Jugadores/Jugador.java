package Jugadores;

import Modelos.Carta;
import Modelos.MazoCartas;
import Modelos.ListaCarta;

/**
 * Interfaz para un jugador.
 */
public interface Jugador {

    /**
     * Inicializa la ronda con la carta inicial.
     * @param inicial carta de inicio
     */
    void empezarNuevaRonda(Carta inicial);

    /**
     * Indica si el jugador decide terminar su secuencia.
     * @param mesa estado actual de la mesa
     * @return true si detiene su secuencia
     */
    boolean seDetuvo(MazoCartas mesa);

    /**
     * Elige una carta de la mesa para agregar a su secuencia.
     * @param mesa estado actual de la mesa
     * @return carta tomada o null si no toma
     */
    Carta escogeCarta(MazoCartas mesa);

    /**
     * Obtiene la secuencia de cartas acumulada por el jugador.
     * @return lista de cartas de la secuencia
     */
    ListaCarta<Carta> obtenerSecuencia();
}