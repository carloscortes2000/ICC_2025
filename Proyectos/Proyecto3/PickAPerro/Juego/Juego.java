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
    private final Jugador jugadorIA = new IAJugador();
    private final Secuencia validador = new Secuencia();
    private int puntosHumano = 0;
    private int puntosIA = 0;

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
        System.out.println("\n--- Ronda " + ronda + " ---");
        Carta cHumano = mazo.robarCarta();
        Carta cAI     = mazo.robarCarta();
        jugadorHumano.empezarNuevaRonda(cHumano);
        jugadorIA.empezarNuevaRonda(cAI);
        mesa.reponerCartas(mazo);

        boolean humanoParo = false;
        boolean iaParo     = false;

        while (!humanoParo || !iaParo) {

            // — Turno del humano —
            if (!humanoParo) {
                System.out.println("\n--- Turno del jugador humano ---");
                if (jugadorHumano.seDetuvo(mesa)) {
                    System.out.println("¡Equipo completo!");
                    humanoParo = true;
                    iaParo     = true;      
                    agregaBonusOPenaliza(jugadorHumano, true);
                    break;                 
                } else {
                    jugadorHumano.escogeCarta(mesa);
                    
                }
            }

            // — Turno de la IA —
            if (!iaParo) {
                System.out.println("\n--- Turno de la IA ---");
                if (jugadorIA.seDetuvo(mesa)) {
                    System.out.println("¡Equipo completo IA!");
                    iaParo     = true;
                    humanoParo = true;     
                    agregaBonusOPenaliza(jugadorIA, false);
                    break;                
                } else {
                    Carta cartaIA = jugadorIA.escogeCarta(mesa);
                    System.out.println("IA seleccionó: " + cartaIA);
                }
            }
        }

        terminarRonda();
        ronda++;
    }
    finalizarJuego();
}

    /**
     * Agrega un bonus de puntuación si el jugador hizo bien su secuencia de cartas.
     * Si no habia más cartas por robar +1 punto y roba carta extra.
     * Si quedaba alguna carta más por robar se penaliza y descarta la secuencia entera.
     * @param p el jugador que termina su secuencia.
     * @param esHumano true si es el jugador humano, false si es la IA
     */
    private void agregaBonusOPenaliza(Jugador p, boolean esHumano) {
        Carta ultima = p.obtenerSecuencia().obtenerElemento(p.obtenerSecuencia().obtenerTamaño() - 1);
        boolean hayValida = false;
        for (int i = 0; i < mesa.obtenerCartas().obtenerTamaño(); i++) {
            Carta c = mesa.obtenerCartas().obtenerElemento(i);
            if (c.contarDiferencias(ultima) <= 1) {
                hayValida = true;
                break;
            }
        }

        if (!hayValida) {
            System.out.println("¡Bien hecho! :) Ganas 1 punto de bonus.");
            if (esHumano) puntosHumano++; else puntosIA++;
            Carta bonus = mesa.tomarCartaDeMesa(0);
            System.out.println("Carta bonus: " + bonus);
        } else {
            System.out.println("Aún se podía extender tu secuencia. Has perdido todas tus cartas de esta ronda :(");
            p.obtenerSecuencia().vaciarLista();
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
        
        if (validador.verificaSecuencia(jugadorIA.obtenerSecuencia())) {
            puntosIA += jugadorIA.obtenerSecuencia().obtenerTamaño();
        }
        
        System.out.printf("Puntos: Humano=%d IA=%d\n", puntosHumano, puntosIA);
    }

    /**
     * Muestra el resultado final de la partida.
     */
    private void finalizarJuego() {
        System.out.println("--- Fin del juego ---");
        System.out.printf("Resultado final: Humano=%d, IA=%d\n", puntosHumano, puntosIA);
        if (puntosHumano > puntosIA) {
            System.out.println("¡Ganaste!");
        } else if (puntosIA > puntosHumano) {
            System.out.println("¡Ganó la IA!");
        } else {
            System.out.println("¡Empate!");
        }
    }
}