import java.util.Scanner;

/**
 * Clase principal para probar el comportamiento y funcionamiento de la clase reloj.
 * 
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reloj reloj = new Reloj(23, 59, 00);

        int entrada; // Almacenamos la opción seleccionada por el usuario
        do {
            // Menú de opciones
            System.out.println("\nMenú:");
            System.out.println("1. Probar reloj con bucle while");
            System.out.println("2. Probar reloj con bucle for");
            System.out.println("3. Probar reloj con bucle do-while");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción que desee probar (1 / 2 / 3): ");
            entrada = scanner.nextInt();

            // Opciones del menú
            switch (entrada) {
                case 1:
                    System.out.println("\nSimulación con bucle while:");
                    // Bucle while para simular el reloj hasta que llegue a 00:00:00
                    while (!reloj.obtenerHora().equals("00:00:00")) {
                        System.out.println(reloj.obtenerHora()); 
                        reloj.incrementarSegundos(); // Incrementa los segundos
                    }
                    System.out.println(reloj.obtenerHora()); // Muestra 00:00:00
                    reloj.resetear(); // Reseteamos el reloj
                    break;

                case 2:
                    System.out.println("\nSimulación con bucle for:");
                    // Bucle for para simular un minuto
                    for (int i = 0; i < 60; i++) {
                        System.out.println(reloj.obtenerHora());
                        reloj.incrementarSegundos(); // Incrementamos los segundos
                    }
                    reloj.resetear(); // Reseteamos el reloj
                    break;

                case 3:
                    System.out.println("\nSimulación con bucle do-while:");
                    // Bucle do-while para simula el reloj hasta que llegue a 00:00:00
                    do {
                        System.out.println(reloj.obtenerHora()); // Muestra la hora actual
                        reloj.incrementarSegundos();
                    } while (!reloj.obtenerHora().equals("00:00:00"));
                    reloj.resetear(); // Reseteamos el reloj
                    break;

                case 0:
                    System.out.println("Saliendo del programa");
                    break;

                default:
                    System.out.println("Opción no válida. Ingrese alguna de las opciones posibles."); 
                    break;
            }
        } while (entrada != 0);

        scanner.close();
    }
}