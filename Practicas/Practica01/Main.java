import java.util.Scanner;
/**
 * Clase que modela una calculadora básica de dos numeros, y realiza las operaciones de suma, multiplicación, división y concatena dos cadenas de texto.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);

        System.out.println("\nCALCULADORA\n");

        System.out.println("Operaciones disponibles:\n");
        System.out.println("1. Suma");
        System.out.println("2. Multiplicación");
        System.out.println("3. División");
        System.out.println("4. Concatenar cadenas de texto\n");

        System.out.print("Ingrese el número de la opción deseada: ");
        
        if (!sn.hasNextInt()) {
            System.out.println("Solo se pueden ingresar números para elegir una opción.");
            sn.close();
            return;
        }

        int opcion = sn.nextInt();
        double num1, num2;

        if (opcion == 1) {
            System.out.print("Ingrese el primer número de la suma: ");
            if (!sn.hasNextDouble()) { 
                System.out.println("Solo se pueden ingresar números.");
                sn.close();
                return;
            }
            num1 = sn.nextDouble();

            System.out.print("Ingrese el segundo número de la suma: ");
            if (!sn.hasNextDouble()) {
                System.out.println("Solo se pueden ingresar números.");
                sn.close();
                return;
            }
            num2 = sn.nextDouble();

            System.out.println("La suma de " + num1 + " y " + num2 + " es: " + (num1 + num2));

        } else if (opcion == 2) {
            System.out.print("Ingrese el primer número: ");
            if (!sn.hasNextDouble()) {
                System.out.println("Solo se pueden ingresar números.");
                sn.close();
                return;
            }
            num1 = sn.nextDouble();

            System.out.print("Ingrese el segundo número: ");
            if (!sn.hasNextDouble()) {
                System.out.println("Solo se pueden ingresar números.");
                sn.close();
                return;
            }
            num2 = sn.nextDouble();

            System.out.println("La multiplicación de " + num1 + " y " + num2 + " es: " + (num1 * num2));

        } else if (opcion == 3) {
            System.out.print("Ingrese el primer número: ");
            if (!sn.hasNextDouble()) {
                System.out.println("Solo se pueden ingresar números.");
                sn.close();
                return;
            }
            num1 = sn.nextDouble();

            System.out.print("Ingrese el segundo número: ");
            if (!sn.hasNextDouble()) {
                System.out.println("Solo se pueden ingresar números.");
                sn.close();
                return;
            }
            num2 = sn.nextDouble();

            if (num2 == 0) {
                System.out.println("No se puede dividir entre cero.");
            } else {
                System.out.println("La división de " + num1 + " y " + num2 + " es: " + (num1 / num2));
            }

        } else if (opcion == 4) {

            sn.nextLine(); 
            System.out.print("Ingrese la primera cadena de texto a unir: ");
            String texto1 = sn.nextLine();
            System.out.print("Ingrese la segunda cadena de texto a unir: ");
            String texto2 = sn.nextLine();
            System.out.println("El texto concatenado es: " + texto1 + " " + texto2);

        } else {
            System.out.println("Opción incorrecta. Por favor ingrese una opción válida.");
        }

        sn.close();
    }
}