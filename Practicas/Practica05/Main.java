public class Main {
    public static void main(String[] args) {

        /* Elige alguna de las tres clases y crea un ejemplar de 
         * dicha clase utilizando dos manerasdiferentes.
         */ 
        Triangulo triangulo1 = new Triangulo(3, 4, 4, 3, 4);
        FiguraGeometrica triangulo2 = new Triangulo(4, 5, 6, 5, 4);

        /* Con las dos clases restantes crea un ejemplar respectivamente 
         * usando la misma clase (no la superclase).
         */ 
        Rectangulo rectangulo = new Rectangulo(5, 6);
        Cuadrado cuadrado = new Cuadrado(7);

        /* Manda a llamar para cada objeto los métodos de perímetro y área. 
         * Donde en alguno de los casos ejemplifiques el concepto de polimorfismo.
         */ 
        System.out.println(triangulo1.toString());
        System.out.println("Área: " + triangulo1.calcularArea() + ", Perímetro: " + triangulo1.calcularPerimetro());

        // Polimorfismo
        System.out.println(triangulo2.toString());
        System.out.println("Área: " + triangulo2.calcularArea() + ", Perímetro: " + triangulo2.calcularPerimetro());

        System.out.println(rectangulo.toString());
        System.out.println("Área: " + rectangulo.calcularArea() + ", Perímetro: " + rectangulo.calcularPerimetro());

        System.out.println(cuadrado.toString());
        System.out.println("Área: " + cuadrado.calcularArea() + ", Perímetro: " + cuadrado.calcularPerimetro());
    }
}