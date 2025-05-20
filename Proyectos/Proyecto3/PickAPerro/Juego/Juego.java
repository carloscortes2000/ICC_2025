package Juego;

import Jugadores.Jugador;
import Jugadores.UsuarioJugador;
import Jugadores.IAJugador;
import Modelos.Baraja;
import Modelos.Carta;
import Modelos.MazoCartas;
import Modelos.ListaCarta;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Controla el flujo general del juego Pick-a-Perro,
 * ahora con varios jugadores IA concurrentes y con delay.
 */
public class Juego {

    //Cartas de la mesa y jugador humano
    private final Baraja mazo = new Baraja(12345);
    private final MazoCartas mesa;
    private final Jugador jugadorHumano = new UsuarioJugador();

    // Jugadores artificiales y sus puntuaciones
    private final ListaCarta<IAJugador> iaJugadores = new ListaCarta<>();
    private final ListaCarta<Integer> puntosPorIA = new ListaCarta<>();
    private final ListaCarta<Integer> puntosIndividualesIA = new ListaCarta<>();

    // Control de los hilos y validación de secuencias de cartas
    private final AtomicBoolean juegoDetenido = new AtomicBoolean(false);
    private final Secuencia validador = new Secuencia();

    // Estado del juego
    private int puntosHumano = 0;
    private HiloIA[] hilosIAActuales;

    // Cantidad de jugadores artificiales
    private final int NUM_IAS = 3;

    /**
     * Constructor del juego. Inicializa componentes principales:
     * - Baraja
     * - Mazo de cartas en la mesa
     * - Jugadores artificiales con sus respectivas puntuaciones cada uno
     */
    public Juego() {
        mazo.barajar();
        mesa = new MazoCartas(mazo);
        
        // Inicializamos los jugadores artificiales y sus puntuaciones
        for (int i = 0; i < NUM_IAS; i++) {
            iaJugadores.agregarElemento(new IAJugador());
            puntosPorIA.agregarElemento(0);
            puntosIndividualesIA.agregarElemento(0);
        }
    }

    /**
     * Método que inicia la partida de Pick-a-Perro.
     * Ahora se crean varios hilos para jugadores artificiales (2–5), arranca al humano en el hilo principal,
     * y se detiene todo en cuanto alguien dice “¡Equipo completo!”.
     */
    public void iniciar() {
        Random rnd = new Random();
        int ronda = 1;

        /*
         * Mientras haya las cartas suficientes para seguir una nueva ronda
         * el juego continua
         */
        while (mazo.cartasRestantes() > NUM_IAS + 1) {
            System.out.println("\n--- Ronda " + ronda + " ---");
            reiniciarRonda();
            
            // Iniciar hilos IA
            hilosIAActuales = new HiloIA[NUM_IAS];
            for (int i = 0; i < NUM_IAS; i++) {
                int delay = 10000 + rnd.nextInt(10001); //delay de 10-20 segundos
                hilosIAActuales[i] = new HiloIA(iaJugadores.obtenerElemento(i), delay, i);
                hilosIAActuales[i].setName("IA-" + (i + 1));
                hilosIAActuales[i].start();
            }

            // Turno del jugador humano
            manejarTurnoHumano();

            // Esperar finalización de hilos IA
            esperarHilosIA();
            
            // Calculamos y mostramos las puntuaciones actuales
            terminarRonda();
            ronda++;
        }
        
        // Mostramos los resultados finales de todos los jugadores
        finalizarJuego();
    }

    /**
     * Se reinicia el estado para una nueva ronda, se reparten nuevas cartas,
     * se rellena el mazo actual en la mesa.
     */
    private void reiniciarRonda() {
        juegoDetenido.set(false); 
        puntosPorIA.vaciarLista();
        for (int i = 0; i < NUM_IAS; i++) puntosPorIA.agregarElemento(0);
        
        // Repartimos nuevas cartas a todos los jugadores
        jugadorHumano.empezarNuevaRonda(mazo.robarCarta());
        for (int i = 0; i < NUM_IAS; i++) {
            iaJugadores.obtenerElemento(i).empezarNuevaRonda(mazo.robarCarta());
        }
        // Rellenamos el mazo actual en la mesa.
        mesa.reponerCartas(mazo);
    }

    /**
     * Maneja el turno del jugador humano, hasta que decida detener sus secuencia de cartas.
     */
    private void manejarTurnoHumano() {
        while (!juegoDetenido.get()) {
            System.out.println("\n--- Turno del jugador humano ---");
            if (jugadorHumano.seDetuvo(mesa)) {
                System.out.println("¡Equipo completo!");
                agregaBonusOPenalizaHumano();
                detenerJuego();
                break;
            } else {
                Carta cartaH = jugadorHumano.escogeCarta(mesa);
                System.out.println("Jugador seleccionó: " + cartaH);
            }
        }
    }

    /**
     * Esperamos a que todos los hilos de los jugadores artificiales terminen su ejecución.
     */
    private void esperarHilosIA() {
        for (HiloIA hilo : hilosIAActuales) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Detiene el juego actual e interrumpe los hilos de los jugadores artificiales.
     */
    private void detenerJuego() {
        juegoDetenido.set(true);
        interrumpirHilosIA();
    }

    /**
     * Interrumpe todos los hilos que se encuentren activos.
     */
    private void interrumpirHilosIA() {
        for (HiloIA hilo : hilosIAActuales) {
            if (hilo != null && hilo.isAlive()) {
                hilo.interrupt();
            }
        }
    }

    /**
     * Clase privada que ejecuta a un hilo de forma concurrente,
     * con delay entre movimientos.
     */
    private class HiloIA extends Thread {
        private final IAJugador ia;
        private final int delayMillis;
        private final int indice;

        HiloIA(IAJugador ia, int delayMillis, int indice) {
            this.ia = ia;
            this.delayMillis = delayMillis;
            this.indice = indice;
        }

        @Override
        public void run() {
            while (!juegoDetenido.get() && !isInterrupted()) {
                try {
                    Thread.sleep(delayMillis);
                    
                    synchronized (mesa) { // Sincronizamo el acceso a la mesa
                        if (juegoDetenido.get()) break;
                        
                        if (ia.seDetuvo(mesa)) {
                            System.out.println(getName() + " dice: ¡Equipo completo IA!");
                            agregaBonusOPenalizaIA(indice);
                            detenerJuego();
                            break;
                        } else {
                            Carta c = ia.escogeCarta(mesa);
                            System.out.println(getName() + " seleccionó: " + c);
                        }
                    }
                } catch (InterruptedException e) {
                    break; //Si el hilo es interrumpido salimos de él.
                }
            }
        }
    }

    /**
     * Agrega un bonus de puntuación si el jugador hizo bien su secuencia de cartas.
     * Si no habia más cartas por robar +1 punto y roba carta extra.
     * Si quedaba alguna carta más por robar se penaliza y descarta la secuencia entera.
     */
    private void agregaBonusOPenalizaHumano() {
        if (jugadorHumano.obtenerSecuencia().obtenerTamaño() == 0) return;
        
        Carta ultima = jugadorHumano.obtenerSecuencia().obtenerElemento(
            jugadorHumano.obtenerSecuencia().obtenerTamaño() - 1);
        
        boolean puedeRobar = false;
        for (int i = 0; i < mesa.obtenerCartas().obtenerTamaño(); i++) {
            if (mesa.obtenerCartas().obtenerElemento(i).contarDiferencias(ultima) <= 1) {
                puedeRobar = true;
                break;
            }
        }
        
        if (!puedeRobar) {
            System.out.println("¡Bien hecho! Bonus de 1 punto");
            puntosHumano++;
            if (mesa.obtenerCartas().obtenerTamaño() > 0) {
                Carta bonus = mesa.tomarCartaDeMesa(0);
                System.out.println("Carta bonus: " + bonus);
            }
        } else {
            System.out.println("¡Penalización! Secuencia borrada");
            jugadorHumano.obtenerSecuencia().vaciarLista();
        }
    }

    /** 
     * Bonus o penalización para los jugadores artificiales 
     * @param indice Índice de la IA en la lista de jugadores
     * 
     */
    private void agregaBonusOPenalizaIA(int indice) {
        IAJugador ia = iaJugadores.obtenerElemento(indice);
        if (ia.obtenerSecuencia().obtenerTamaño() == 0) return;
        
        Carta ultima = ia.obtenerSecuencia().obtenerElemento(
            ia.obtenerSecuencia().obtenerTamaño() - 1);
        
        boolean puedeRobar = false;
        for (int i = 0; i < mesa.obtenerCartas().obtenerTamaño(); i++) {
            if (mesa.obtenerCartas().obtenerElemento(i).contarDiferencias(ultima) <= 1) {
                puedeRobar = true;
                break;
            }
        }
        
        if (!puedeRobar) {
            System.out.println("IA-" + (indice+1) + " obtiene bonus de 1 punto");
            int actual = puntosPorIA.obtenerElemento(indice);
            puntosPorIA.agregarElementoEn(indice, actual + 1);
            if (mesa.obtenerCartas().obtenerTamaño() > 0) {
                Carta bonus = mesa.tomarCartaDeMesa(0);
                System.out.println("Carta bonus: " + bonus);
            }
        } else {
            System.out.println("IA-" + (indice+1) + " penalizada");
            ia.obtenerSecuencia().vaciarLista();
        }
    }

    /**
     * Finaliza la ronda actual y actualiza el marcador de los jugadores.
     */
    private void terminarRonda() {
        validarSecuenciaHumana();
        validarSecuenciasIA();
        mostrarPuntuaciones();
    }

    /**
     * Valida la secuencia de cartas del jugador humano y actualiza su puntuación.
     */
    private void validarSecuenciaHumana() {
        int baseH = jugadorHumano.obtenerSecuencia().obtenerTamaño();
        boolean valida = validador.verificaSecuencia(jugadorHumano.obtenerSecuencia());
        
        System.out.println("\nValidando secuencia humana...");
        if (valida) {
            puntosHumano += baseH;
            System.out.println("Válida (+" + baseH + " puntos)");
        } else {
            System.out.println("Inválida (+0 puntos)");
        }
    }

    /**
     * Valida la secuencia de cartas de los jugadores artificiales y actualiza su puntuación.
     */
    private void validarSecuenciasIA() {
        System.out.println("Validando secuencias IA:");
        for (int i = 0; i < NUM_IAS; i++) {
            IAJugador ia = iaJugadores.obtenerElemento(i);
            int base = ia.obtenerSecuencia().obtenerTamaño();
            int bonus = puntosPorIA.obtenerElemento(i);
            boolean valida = validador.verificaSecuencia(ia.obtenerSecuencia());
            
            if (valida) {
                int total = puntosIndividualesIA.obtenerElemento(i) + base + bonus;
                puntosIndividualesIA.agregarElementoEn(i, total);
                System.out.printf("IA-%d: Válida (+%d base +%d bonus)%n", 
                               i+1, base, bonus);
            } else {
                System.out.printf("IA-%d: Inválida (+0)%n", i+1);
            }
        }
    }

    /**
     * Muestra las puntuaciones actuales de todos los jugadores.
     */
    private void mostrarPuntuaciones() {
        System.out.println("\n--- Puntuación Actual ---");
        System.out.println("Humano: " + puntosHumano);
        for (int i = 0; i < NUM_IAS; i++) {
            System.out.printf("IA-%d: %d puntos%n", 
                           i+1, puntosIndividualesIA.obtenerElemento(i));
        }
    }

    /**
     * Muestra el resultado final de la partida.
     */
    private void finalizarJuego() {
        System.out.println("\n-- Fin del juego ---");
        mostrarPuntuaciones();
        
        int maxIAPuntos = 0;
        for (int i = 0; i < NUM_IAS; i++) {
            maxIAPuntos = Math.max(maxIAPuntos, 
                                  puntosIndividualesIA.obtenerElemento(i));
        }
        
        if (puntosHumano > maxIAPuntos) {
            System.out.println("\n¡Has Ganado!");
        } else if (maxIAPuntos > puntosHumano) {
            System.out.println("\n¡La IA ha ganado!");
        } else {
            System.out.println("\n¡Empate!");
        }
    }
}