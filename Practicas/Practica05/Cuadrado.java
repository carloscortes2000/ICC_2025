/**
 * Clase que representa un cuadrado.
 */
public class Cuadrado extends FiguraGeometrica {
    private double lado;

    /**
     * Constructor del cuadrado.
     * 
     * @param lado Longitud del lado del cuadrado.
     */
    public Cuadrado(double lado) {
        super("Cuadrado");
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }

    @Override
    public String toString() {
        return String.format(
            "%s con lados = [%.2f, %.2f, %.2f, %.2f]",
            nombre, lado, lado, lado, lado
        );
    }
}
