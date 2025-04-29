/**
 * Clase abstracta que representa una figura geométrica genérica.
 * Define la estructura base para todas las figuras.
 */
public abstract class FiguraGeometrica {
    
    protected String nombre;

    /**
     * Constructor de la figura geométrica.
     * 
     * @param nombre Nombre de la figura.
     */
    public FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Calcula el área de la figura.
     * 
     * @return El área como un valor double.
     */
    public abstract double calcularArea();

    /**
     * Calcula el perímetro de la figura.
     * 
     * @return El perímetro como un valor double.
     */
    public abstract double calcularPerimetro();

    /**
     * Devuelve una de descripción de la figura.
     * 
     * @return Descripción con medidas de los lados.
     */
    public abstract String toString();
}
