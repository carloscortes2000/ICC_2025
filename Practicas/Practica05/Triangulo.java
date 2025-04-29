/**
 * Clase que representa un triángulo.
 */
public class Triangulo extends FiguraGeometrica {
    private double ladoA, ladoB, ladoC;
    private double base, altura;

    /**
     * Constructor del triángulo.
     * 
     * @param ladoA  Longitud del lado A.
     * @param ladoB  Longitud del lado B.
     * @param ladoC  Longitud del lado C.
     * @param base   La base del triángulo.
     * @param altura Altura respecto a la base.
     */
    public Triangulo(double ladoA, double ladoB, double ladoC, double base, double altura) {
        super("Triángulo");
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return ladoA + ladoB + ladoC;
    }

    @Override
    public String toString() {
        return String.format(
            "%s con lados = [%.2f, %.2f, %.2f], base = %.2f, altura = %.2f",
            nombre, ladoA, ladoB, ladoC, base, altura
        );
    }
}
