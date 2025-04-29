/**
 * Clase que representa un rectángulo.
 * 
 */
public class Rectangulo extends FiguraGeometrica {
    private double base, altura;

    /**
     * Constructor de Rectangulo.
     * 
     * @param base   La base del rectangulo.
     * @param altura Altura del rectángulo.
     */
    public Rectangulo(double base, double altura) {
        super("Rectángulo");
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    @Override
    public String toString() {
        return String.format(
            "%s con lados = [%.2f, %.2f, %.2f, %.2f], base = %.2f, altura = %.2f",
            nombre, base, altura, base, altura, base, altura
        );
    }
}
