
/**
 * Clase que representa y simula un reloj con horas, minutos y segundos.
 * 
 */
public class Reloj {
    
    private int hora;    // Almacena la hora (0-23)
    private int minutos; // Almacena los minutos (0-59)
    private int segundos; // Almacena los segundos (0-59)

    /**
     * Constructor de la clase Reloj.
     * @param hora La hora inicial.
     * @param minutos Los minutos iniciales.
     * @param segundos Los segundos iniciales.
     */
    public Reloj(int hora, int minutos, int segundos) {
        this.hora = hora;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    /**
     * Incrementa los segundos del reloj.
     * Si los segundos llegan a 60, se resetean a 0 y se incrementan los minutos.
     */
    public void incrementarSegundos() {
        segundos++;
        if (segundos >= 60) {
            segundos = 0;
            incrementarMinutos(); // Llamada al método incrementarMinutos() para incrementar los minutos
        }
    }

    /**
     * Incrementa los minutos del reloj.
     * Si los minutos llegan a 60, se resetean a 0 y se incrementa la hora.
     */
    public void incrementarMinutos() {
        minutos++;
        if (minutos >= 60) {
            minutos = 0;
            incrementarHora(); // Llamada al método incrementarHora() para incrementar la hora
        }
    }

    /**
     * Incrementa la hora del reloj.
     * Si la hora llega a 24, se resetea a 0.
     */
    public void incrementarHora() {
        hora++;
        if (hora >= 24) {
            hora = 0;
        }
    }

    /**
     * Devuelve la hora actual en formato HH:MM:SS.
     * @return Una cadena que representa la hora en formato HH:MM:SS.
     */
    public String obtenerHora() {
        return String.format("%02d:%02d:%02d", hora, minutos, segundos);
    }

    /**
     * Resetea el reloj, estableciendo la hora, minutos y segundos con el formato 00:00:00.
     */
    public void resetear() {
        hora = 0;
        minutos = 0;
        segundos = 0;
    }
}