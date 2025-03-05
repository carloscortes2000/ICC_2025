/**
 * 
 * Clase que modela recipientes cilíndricos y permite almacenar líquidos.
 * 
 * @author Carlos Daniel Cortes Jimenez
 * 
 */
public class Recipiente implements ServiciosRecipiente {

    private double altura;
    private double radio;
    private double cantidadActual;

    /**
     * Constructor que inicializa el recipiente con una altura, radio y cantidad inicial de líquido.
     * Se verifica que no se introduzcan valores negativos, en caso que suceda manda un mensaje de error.
     * 
     */
    public Recipiente(double altura, double radio, double cantidadActual) {
        if (altura <= 0 || radio <= 0) {
            System.out.println("Error: No se aceptan valores negativos, la altura y el radio deben ser valores positivos. Asiganando valores por defecto: altura=1, radio=1.");
            this.altura = 1;
            this.radio = 1;
        } else {
            this.altura = altura;
            this.radio = radio;
        }
    
        double capacidadTotal = capacidad();
        if (cantidadActual < 0 || cantidadActual > capacidadTotal) {
            System.out.println("Error: No se aceptan valores negativos, la cantidad inicial debe estar entre 0 y " + capacidadTotal + " cm³. Se asigna cantidadActual=0.");
            this.cantidadActual = 0;
        } else {
            this.cantidadActual = cantidadActual;
        }
    }

    /**
     * Constructor por defecto que crea un recipiente vacío.
     */
    public Recipiente(double altura, double radio) {
        this(altura, radio, 0);
    }

    /**
     * Calcula la capacidad maxima que cabe en el recipiente.
     */
    @Override
    public double capacidad() {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    /**
     * Calcula y devuelve la diferencia entre la capacidad total y
     * la cantidad actual del recipiente.
     */
    @Override
    public double capacidadRestante() {
        return capacidad() - cantidadActual;
    }

    /**
     * Verifica si el recipiente esta vacıo, si es así devuleve true.
     */
    @Override
    public boolean estaVacio() {
        return cantidadActual == 0;
    }

    /**
     * Verifica si  el recipiente está lleno, devuleve true si es así.
     */
    @Override
    public boolean estaLleno() {
        return cantidadActual == capacidad();
    }

    /**
     * Agrega una nueva cantidad de lıquido al recipiente, respetando
     * la capacidad maxima del mismo. En caso de que no quepa todo
     * el lıquido, debera devolver la cantidad excedente.
     */
    @Override
    public double rellena(double cantidad) {
        if (cantidad < 0) {
            System.out.println("Error: La cantidad a agregar no puede ser negativa.");
            return 0;
        }
        double nuevaCantidad = cantidadActual + cantidad;
        double capacidadTotal = capacidad();
        if (nuevaCantidad > capacidadTotal) {
            double excedente = nuevaCantidad - capacidadTotal;
            cantidadActual = capacidadTotal;
            return excedente;
        } else {
            cantidadActual = nuevaCantidad;
            return 0;
        }
    }

    /**
     * Resetea la cantidad actual del recipiente a cero .
     */
    @Override
    public double vacia() {
        double anterior = cantidadActual;
        cantidadActual = 0;
        return anterior;
    }

    /**
     * Vierte el contenido del recipiente en otro respetando su
     * capacidad maxima. En caso de haber excedente, se debe guardar
     * en el recipiente invocante.
     */
    @Override
    public void vierte(Recipiente otro) {
        double espacioDisponible = otro.capacidadRestante();
        if (cantidadActual <= espacioDisponible) {
            otro.rellena(cantidadActual);
            cantidadActual = 0;
        } else {
            otro.rellena(espacioDisponible);
            cantidadActual -= espacioDisponible;
        }
    }

    /**
     * Verifica si el recipiente tiene exactamente las mismas
    * dimensiones que otro, devuelve true si es así.
     */
    @Override
    public boolean mismasDimensiones(Recipiente otro) {
        return this.altura == otro.altura && this.radio == otro.radio;
    }

    /**
     * Indica si el recipiente tiene exactamente la misma
     * capacidad total que otro
     */
    @Override
    public boolean mismaCapacidad(Recipiente otro) {
        return this.capacidad() == otro.capacidad();
    }

    /**
     * Indica si el recipiente tiene mayor cantidad actual que otro, devuelve true si es así
     */
    @Override
    public boolean contieneMas(Recipiente otro) {
        return this.cantidadActual > otro.cantidadActual;
    }

    /**
     * * Indica si el recipiente tiene mayor capacidad restante que
     * otro, devuleve true si es así 
     */
    @Override
    public boolean cabeMas(Recipiente otro) {
        return this.capacidadRestante() > otro.capacidadRestante();
    }

    /**
     * Crea y devuelve un nuevo recipiente vacío uya capacidad total es igual
     * a la cantidad actual del recipiente invocante.
     */
    @Override
    public Recipiente creaContenedorJusto() {

        double nuevaCapacidad = this.cantidadActual;
        double nuevaAltura = nuevaCapacidad / (Math.PI * Math.pow(radio, 2)); // Despejamos la fórmula para obtener la altura.
        return new Recipiente(nuevaAltura, radio, 0);
    }

    /**
     * Devuelve una cadena con buen formato para mostar las
     * dimensiones, capacidad y cantidad actual del recipiente.
     */
    @Override
    public String muestra() {
        return String.format("Recipiente [Altura: %.2f cm, Radio: %.2f cm, Capacidad: %.2f cm³, Cantidad Actual: %.2f cm³]",
                altura, radio, capacidad(), cantidadActual);
    }

    /**
     * Método main para probar el funcionamiento de los métodos de la clase Recipeiente
     */
    public static void main(String[] args) {
        System.out.println("===============================================");

        // Creación de recipientes con distintas dimensiones y cantidades iniciales
        Recipiente recipiente1 = new Recipiente(10, 5, 0);
        Recipiente recipiente2 = new Recipiente(8, 4, 50);
        Recipiente recipiente3 = new Recipiente(10, 5, 100);
        Recipiente recipiente4 = new Recipiente(15, 7, 53);

        // Mostrar estados iniciales
        System.out.println("Recipiente 1: " + recipiente1.muestra());
        System.out.println("Recipiente 2: " + recipiente2.muestra());
        System.out.println("Recipiente 3: " + recipiente3.muestra());
        System.out.println("Recipiente 4: " + recipiente4.muestra());
        System.out.println();

        // Probamos la capacidad y capacidad restante
        System.out.println("Capacidad de recipiente1: " + recipiente1.capacidad() + " cm³");
        System.out.println("Capacidad restante de recipiente1: " + recipiente1.capacidadRestante() + " cm³");
        System.out.println();

        // Probamos los métodos estaVacio y estaLleno
        System.out.println("¿Recipiente1 está vacío? " + recipiente1.estaVacio());
        System.out.println("¿Recipiente3 está lleno? " + recipiente3.estaLleno());
        System.out.println();

        // Probamos el metodo de rellena
        System.out.println("Rellenando recipiente1 con 500 cm³...");
        double excedente = recipiente1.rellena(500);
        System.out.println("Excedente: " + excedente + " cm³");
        System.out.println("Recipiente1 después de rellenar: " + recipiente1.muestra());
        System.out.println();

        // Probamos el metodo de vacia
        System.out.println("Vaciando recipiente4...");
        double cantidadVaciada = recipiente4.vacia();
        System.out.println("Cantidad vaciada: " + cantidadVaciada + " cm³");
        System.out.println("Recipiente4 después de vaciar: " + recipiente4.muestra());
        System.out.println();

        // Probamos el método vierte
        System.out.println("Rellenando recipiente4 con 300 cm³...");
        recipiente4.rellena(300);
        System.out.println("Antes de verter:");
        System.out.println("Recipiente4: " + recipiente4.muestra());
        System.out.println("Recipiente2: " + recipiente2.muestra());
        System.out.println("Vertiendo el contenido de recipiente4 en recipiente2...");
        recipiente4.vierte(recipiente2);
        System.out.println();
        System.out.println("Después de verter:");
        System.out.println("Recipiente4: " + recipiente4.muestra());
        System.out.println("Recipiente2: " + recipiente2.muestra());
        System.out.println();

        // Probamos los metodos mismasDimensiones y mismaCapacidad
        
        System.out.println("¿Recipiente1 y Recipiente3 tienen las mismas dimensiones? " 
                           + recipiente1.mismasDimensiones(recipiente3));
        System.out.println("¿Recipiente1 y Recipiente3 tienen la misma capacidad? " 
                           + recipiente2.mismaCapacidad(recipiente4));
        System.out.println();

        // Probamos los metodos contieneMas y cabeMas
        System.out.println("¿Recipiente2 contiene más líquido que Recipiente4? " 
                           + recipiente1.contieneMas(recipiente4));
        System.out.println("¿Recipiente2 tiene mayor capacidad restante que Recipiente4? " 
                           + recipiente3.cabeMas(recipiente3));
        System.out.println();

        // Prueba del método creaContenedorJusto
        System.out.println("Creando un contenedor justo a partir de Recipiente2...");
        Recipiente contenedorJusto = recipiente1.creaContenedorJusto();
        System.out.println("Nuevo contenedor: " + contenedorJusto.muestra());
    }
}


