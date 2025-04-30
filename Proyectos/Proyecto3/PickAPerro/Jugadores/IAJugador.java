package Jugadores;

import Modelos.Carta;
import Modelos.ListaCarta;
import Modelos.MazoCartas;

/**
 * Jugador artificial que elige cartas válidas automáticamente.
 */
public class IAJugador implements Jugador {
    private final ListaCarta<Carta> secuencia = new ListaCarta<>();

    @Override
    public void empezarNuevaRonda(Carta inicial) {
        secuencia.vaciarLista();
        secuencia.agregarElemento(inicial);
        System.out.println("Carta inicial IA: " + inicial);
    }

    @Override
    public boolean seDetuvo(MazoCartas mesa) {
        Carta ultima = secuencia.obtenerElemento(secuencia.obtenerTamaño() - 1);
        ListaCarta<Carta> disponibles = mesa.obtenerCartas();
        for (int i = 0; i < disponibles.obtenerTamaño(); i++) {
            Carta c = disponibles.obtenerElemento(i);
            if (c.contarDiferencias(ultima) <= 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Carta escogeCarta(MazoCartas mesa) {
        Carta ultima = secuencia.obtenerElemento(secuencia.obtenerTamaño() - 1);
        for (int i = 0; i < mesa.obtenerCartas().obtenerTamaño(); i++) {
            Carta c = mesa.obtenerCartas().obtenerElemento(i);
            if (c.contarDiferencias(ultima) <= 1) {
                secuencia.agregarElemento(c);
                return mesa.tomarCartaDeMesa(i);
            }
        }
        return null;
    }

    @Override
    public ListaCarta<Carta> obtenerSecuencia() {
        return secuencia;
    }
}