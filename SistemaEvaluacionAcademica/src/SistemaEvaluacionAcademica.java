import java.util.ArrayList;
import java.util.Scanner;

public class SistemaEvaluacionAcademica {

    // Calcular promedio usando streams
    public static double calcularPromedio(ArrayList<Double> notas) {
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    // Determinar literal
    public static char obtenerLiteral(double promedio) {
        if (promedio >= 90) return 'A';
        else if (promedio >= 80) return 'B';
        else if (promedio >= 70) return 'C';
        else if (promedio >= 60) return 'D';
        else return 'F';
    }

    // Verificar si aprueba
    public static boolean estaAprobado(char literal) {
        return literal == 'A' || literal == 'B' || literal == 'C';
    }

    // Mostrar resultado con formato
    public static void mostrarResultado(String nombre, ArrayList<Double> notas) {
        double promedio = calcularPromedio(notas);
        char literal = obtenerLiteral(promedio);
        boolean aprobado = estaAprobado(literal);

        System.out.println("\n==================================");
        System.out.println("📚 Estudiante: " + nombre);
        System.out.println("Notas: " + notas);
        System.out.printf("Promedio: %.2f\n", promedio);
        System.out.println("Literal: " + literal);
        System.out.println("Resultado: " + (aprobado ? "✅ Aprobado" : "❌ Reprobado"));
        System.out.println("==================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de estudiantes: ");
        int cantidadEstudiantes = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= cantidadEstudiantes; i++) {
            System.out.print("\nNombre del estudiante " + i + ": ");
            String nombre = sc.nextLine();

            ArrayList<Double> notas = new ArrayList<>();
            System.out.print("Ingrese la cantidad de notas: ");
            int cantidadNotas = Integer.parseInt(sc.nextLine());

            for (int j = 1; j <= cantidadNotas; j++) {
                System.out.print("Nota " + j + ": ");
                notas.add(Double.parseDouble(sc.nextLine()));
            }

            mostrarResultado(nombre, notas);
        }

        sc.close();
    }
}