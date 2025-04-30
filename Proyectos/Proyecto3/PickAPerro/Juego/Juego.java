package Juego;

import Jugadores.Jugador;
import Jugadores.UsuarioJugador;
import Jugadores.IAJugador;
import Modelos.Baraja;
import Modelos.Carta;
import Modelos.MazoCartas;

/**
 * Controla el flujo general del juego Pick-a-Perro.
 */
public class Juego {
    private final Baraja mazo = new Baraja(12345);
    private final MazoCartas mesa;
    private final Jugador jugadorHumano = new UsuarioJugador();
    private final Jugador jugadorAI = new IAJugador();
    private final Secuencia validador = new Secuencia();
    private int puntosHumano = 0;
    private int puntosAI = 0;

    public Juego() {
        mazo.barajar();
        mesa = new MazoCartas(mazo);
    }

    /**
     * Metodo que inicia la partida de Pick-a-Perro.
     */
    public void iniciar() {
        int ronda = 1;
        while (mazo.cartasRestantes() >= 2) {
            System.out.println("--- Ronda " + ronda + " ---");
            Carta cHumano = mazo.robarCarta();
            Carta cAI = mazo.robarCarta();
            jugadorHumano.empezarNuevaRonda(cHumano);
            jugadorAI.empezarNuevaRonda(cAI);
            mesa.reponerCartas(mazo);
            ejecutarTurnos(jugadorHumano);
            ejecutarTurnos(jugadorAI);
            terminarRonda();
            ronda++;
        }
        finalizarJuego();
    }

    /**
     * Ejecuta los turnos de un jugador hasta que decida detenerse su secuencia.
     * @param p jugador
     */
    private void ejecutarTurnos(Jugador p) {
        while (true) {
            if (p.seDetuvo(mesa)) {
                break;
            }
            p.escogeCarta(mesa);
        }
    }

    /**
     * Valida las secuencias de cartas de ambos jugadores, asigna puntos y muestra el marcador.
     */
    private void terminarRonda() {

        System.out.println("Validando secuencia humana...");
        
        if (validador.verificaSecuencia(jugadorHumano.obtenerSecuencia())) {
            puntosHumano += jugadorHumano.obtenerSecuencia().obtenerTamaño();
        }
        
        System.out.println("Validando secuencia IA...");
        
        if (validador.verificaSecuencia(jugadorAI.obtenerSecuencia())) {
            puntosAI += jugadorAI.obtenerSecuencia().obtenerTamaño();
        }
        
        System.out.printf("Puntos: Humano=%d IA=%d\n", puntosHumano, puntosAI);
    }

    /**
     * Muestra el resultado final de la partida.
     */
    private void finalizarJuego() {
        System.out.println("--- Fin del juego ---");
        System.out.printf("Resultado final: Humano=%d, IA=%d\n", puntosHumano, puntosAI);
        if (puntosHumano > puntosAI) {
            System.out.println("¡Ganaste!");
        } else if (puntosAI > puntosHumano) {
            System.out.println("¡Ganó la IA!");
        } else {
            System.out.println("¡Empate!");
        }
    }
}