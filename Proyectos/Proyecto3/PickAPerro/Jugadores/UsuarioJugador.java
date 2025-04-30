package Jugadores;

import Modelos.Carta;
import Modelos.ListaCarta;
import Modelos.MazoCartas;
import java.util.Scanner;

/**
 * Usuario que interactúa por la terminal.
 */
public class UsuarioJugador implements Jugador {
    private final ListaCarta<Carta> secuencia = new ListaCarta<>();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void empezarNuevaRonda(Carta inicial) {
        secuencia.vaciarLista();
        secuencia.agregarElemento(inicial);
        System.out.println("Tu carta inicial: " + inicial);
    }

    @Override
    public boolean seDetuvo(MazoCartas mesa) {
        System.out.print("¿Terminar secuencia? (s/n): ");
        String resp = sc.nextLine().trim().toLowerCase();
        return resp.equals("s");
    }

    @Override
    public Carta escogeCarta(MazoCartas mesa) {
        System.out.println("Cartas en mesa:");
        for (int i = 0; i < mesa.obtenerCartas().obtenerTamaño(); i++) {
            System.out.printf("%d: %s\n", i, mesa.obtenerCartas().obtenerElemento(i));
        }
        System.out.print("Índice de carta a tomar: ");
        int idx = Integer.parseInt(sc.nextLine());
        Carta c = mesa.tomarCartaDeMesa(idx);
        secuencia.agregarElemento(c);
        System.out.println("Seleccionaste: " + c);
        return c;
    }

    @Override
    public ListaCarta<Carta> obtenerSecuencia() {
        return secuencia;
    }
}