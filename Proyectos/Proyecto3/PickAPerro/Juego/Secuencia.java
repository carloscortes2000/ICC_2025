package Juego;

import Modelos.Carta;
import Modelos.ListaCarta;

/**
 * Clase que valida la secuencia de cartas de los jugadores hasta un máximo de 1 diferencia entre cartas consecutivas.
 */
public class Secuencia {

    /**
     * Verifica que cada carta difiera en a lo mas 1 atributo de la anterior.
     * @param sec secuencia de cartas
     * @return true si válida
     */
    public boolean verificaSecuencia(ListaCarta<Carta> sec) {
        for (int i = 1; i < sec.obtenerTamaño(); i++) {
            if (sec.obtenerElemento(i).contarDiferencias(sec.obtenerElemento(i-1)) > 1) {
                return false;
            }
        }
        return true;
    }
}