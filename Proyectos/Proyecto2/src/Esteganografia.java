import java.util.Scanner;

/**
 * 
 * Clase que implementa métodos de descifrado
 */
public class Esteganografia {

    /**
     * Toma la n-ésima letra de cada palabra para obtener el mensaje
     * oculto.
     *
     * @param original El mensaje original.
     * @param n El ı́ ndice que se tiene que tomar de cada palabra.
     * @return El mensaje oculto, sin espacios.
     */
    public String descifraNulo(String original, int n) {
        String textoOculto = "";
        // Usamos Scanner para extraer palabra a palabra
        Scanner sc = new Scanner(original);
        while (sc.hasNext()) {
            String palabra = sc.next();
            // Si la palabra es lo suficientemente larga, se extrae la letra en la posición (n-1)
            if (palabra.length() >= n) {
                textoOculto += palabra.charAt(n - 1);
            }
            // Si no tiene n letras, se omite
        }
        sc.close();
        return textoOculto;
    }

    /**
     * Toma la n-ésima letra de cada palabra para obtener el mensaje
     * oculto.
     *
     * @param original El mensaje original con n espacios al final .
     * @return El mensaje oculto, sin espacios.
     */
    public String descifraNulo(String original) {
        // Contar espacios al final
        int n = 0;
        int i = original.length() - 1;
        while (i >= 0 && original.charAt(i) == ' ') {
            n++;
            i--;
        }
        // Quitamos los espacios para que Scanner no detecte palabras vacías
        String sinEspaciosFinales = original.substring(0, original.length() - n);
        return descifraNulo(sinEspaciosFinales, n);
    }

    /**
     * Busca un nombre oculto en un texto arbitrario ignorando
     * espacios, signos de puntuación y sin hacer distinciones
     * entre mayúsculas y minúsculas.
     *
     * @param mensaje Una cadena arbitraria donde se hará la búsqueda.
     * @param nombre El nombre que se buscará en el texto.
     * @return true si el mensaje está contenido, false en otro caso.
     */
    public boolean contieneNombre(String mensaje, String nombre) {
        // Quitamos espacios y signos de puntuación, y convertimos a minúsculas.
        String mensajeNormalizado = normalizaTexto(mensaje);
        String nombreNormalizado = normalizaTexto(nombre);
        // Buscamos el nombre en el mensaje
        return mensajeNormalizado.contains(nombreNormalizado);
    }

    /**
     * Método auxiliar para normalizar una cadena
     * eliminando los espacios, signos de puntuación y convertir a minúsculas.
     *
     * @param texto El texto a normalizar.
     * @return El texto normalizado.
     */
    private String normalizaTexto(String texto) {
        String textoNormalizado = "";
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            // Solo se consideran letras (entre A-Z o a-z)
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                // Se añade en minúscula
                if (c >= 'A' && c <= 'Z') {
                    textoNormalizado += (char)(c + 32); // convertir mayúscula a minúscula
                } else {
                    textoNormalizado += c;
                }
            }
            // Se ignoran espacios y cualquier otro signo de puntuación
        }
        return textoNormalizado;
    }

    /**
     * Reconstruye el mensaje oculto a partir de las palabras
     * especiales que se obtienen al comparar dos textos.
     *
     * @param m Un texto arbitrario.
     * @param e Un texto similar al primero, pero con algunas palabras especiales.
     * @return El mensaje oculto .
     */
    public String descifraPalabrasMarcadas(String m, String e) {
        String textoOculto = "";
        // Creamos dos Scanners para recorrer ambos textos palabra a palabra.
        Scanner scM = new Scanner(m);
        Scanner scE = new Scanner(e);
        while (scM.hasNext() && scE.hasNext()) {
            String palabraM = scM.next();
            String palabraE = scE.next();
            // Si las palabras son diferentes, agregamos la palabra(M) al mensaje oculto.
            if (!palabraM.equals(palabraE)) {
                // Añadimos un espacios ára separar las palabras si ya tenemos algo en textoOculto
                if (!textoOculto.equals("")) {
                    textoOculto += " ";
                }
                textoOculto += palabraM;
            }
        }
        scM.close();
        scE.close();
        return textoOculto;
    }

    /**
     * Reconstruye el mensaje oculto a partir de las letras
     * especiales que se obtienen al comparar dos textos.
     *
     * @param m Un texto arbitrario.
     * @param e Un texto similar al primero, pero con algunas letras especiales.
     * @return El mensaje oculto.
     */
    public String descifraLetrasMarcadas(String m, String e) {

        String textoOculto = "";
        Scanner scM = new Scanner(m); // Scanner para la primera letra de la primera palabra
        Scanner scE = new Scanner(e); //Scanner para la segunda letra de la segunda palabra
        while (scM.hasNext() && scE.hasNext()) {
            String palabraM = scM.next();
            String palabraE = scE.next();
            // Asuminmos que ambas palabras tienen la misma longitud.
            for (int i = 0; i < Math.min(palabraM.length(), palabraE.length()); i++) {
            if (palabraM.charAt(i) != palabraE.charAt(i)) {
                textoOculto += palabraM.charAt(i);
            }
        }
        }
        scM.close();
        scE.close();
        return textoOculto;
    }

    /**
     * Método main donde se probará el funcionamiento de los métodos anteriores.
     */
    public static void main(String[] args) {
        Esteganografia estego = new Esteganografia();
        Scanner sc = new Scanner(System.in);
        Scanner op = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n************************************ Menú ************************************");
            System.out.println("1. Descifrar mensaje con cifrado nulo (usando índice n dado)");
            System.out.println("2. Descifrar mensaje con cifrado nulo (usando espacios al final)");
            System.out.println("3. Buscar nombre oculto en un mensaje");
            System.out.println("4. Descifrar mensaje oculto (palabras marcadas)");
            System.out.println("5. Descifrar mensaje oculto (letras marcadas)");
            System.out.println("6. Salir");
            System.out.println("******************************************************************************\n");
            System.out.print("Elige una opción:");
            
            opcion = op.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\nPruebe el siguiente texto: \nPRESIDENT'S EMBARGO RULING SHOULD HAVE IMMEDIATE NOTICE. GRAVE SITUATION AFFECTING INTERNATIONAL LAW. STATEMENT FORESHADOWS RUIN OF MANY NEUTRALS. YELLOW JOURNALS UNIFYING NATIONAL EXCITEMENT IMMENSELY.\n");
                    System.out.print("Introduce el mensaje original:");
                    String original1 = sc.nextLine();
                    System.out.print("\nUtilice n = 1: \n");
                    System.out.print("Introduce el índice(n): ");
                    int n = Integer.parseInt(sc.nextLine());
                    String resultado1 = estego.descifraNulo(original1, n);
                    System.out.println("Mensaje oculto: " + resultado1);
                    break;
                case 2:
                    System.out.println("\nPruebe el mensaje:\nLas tropas pasan(con tres espacios al final)\n");
                    System.out.print("Introduce el mensaje original (con espacios al final):");
                    String original2 = sc.nextLine();
                    String resultado2 = estego.descifraNulo(original2);
                    System.out.println("Mensaje oculto: " + resultado2);
                    break;
                case 3:
                    System.out.println("\nPruebe el mensaje:\nNo seas leon o reina, pues t'ama. Cien males se doblan cada hora en que pene, Y en ti de tal guisa beldad pues se asienta, No seas cruel en así dar afrenta\n");
                    System.out.print("Introduce el mensaje en el que buscar:");
                    String mensaje = sc.nextLine();
                    System.out.println("\nIntente buscar los nombres: \nLeonor, Blanca, Isabel y Elena\n");
                    System.out.print("Introduce el nombre a buscar:");
                    String nombre = sc.nextLine();
                    boolean contiene = estego.contieneNombre(mensaje, nombre);
                    System.out.println("El mensaje " + (contiene ? "contiene" : "no contiene") + " el nombre.");
                    break;
                case 4:
                    System.out.println("\nPruebe el mensaje:\nMañana tal vez nos preocupemos por cómo nos vemos.\n");
                    System.out.print("Introduce el primer mensaje:");
                    String m1 = sc.nextLine();
                    System.out.println("\nPruebe el mensaje:\nmañana tal vez noz preocupemos por cómo nos vennos.\n");
                    System.out.print("Introduce el segundo mensaje:");
                    String e1 = sc.nextLine();
                    String resultado4 = estego.descifraPalabrasMarcadas(m1, e1);
                    System.out.println("Mensaje oculto: " + resultado4);
                    break;
                case 5:
                    System.out.println("\nPruebe el mensaje:\nNo puedo coser porque no tengo hílo.\n"); 
                    System.out.print("Introduce el primer mensaje:");
                    String m2 = sc.nextLine();
                    System.out.println("\nPruebe el mensaje:\nNo puedo cocer porque no tengo hilo.\n"); 
                    System.out.print("Introduce el segundo mensaje:");
                    String e2 = sc.nextLine();
                    String resultado5 = estego.descifraLetrasMarcadas(m2, e2);
                    System.out.println("Mensaje oculto: " + resultado5);
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 6);
        
        sc.close();
    }
}


