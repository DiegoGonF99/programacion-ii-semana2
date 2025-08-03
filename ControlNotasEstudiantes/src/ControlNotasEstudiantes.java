import java.util.Scanner;
public class ControlNotasEstudiantes {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

        // Solicitar el número de estudiantes
        System.out.print("Ingrese el número de estudiantes (máximo 5): ");
        int numEstudiantes = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (numEstudiantes < 1 || numEstudiantes > 5) {
            System.out.println("Número de estudiantes inválido.");
            return;
        }

        // Arreglos para almacenar los datos
        String[] nombres = new String[numEstudiantes];
        double[] promedios = new double[numEstudiantes];
        String[] estados = new String[numEstudiantes];

        // Ciclo para ingresar los datos de cada estudiante
        for (int i = 0; i < numEstudiantes; i++) {
            System.out.println("Ingrese los datos del estudiante " + (i + 1) + ":");

            // Pedir el nombre del estudiante
            System.out.print("Nombre: ");
            nombres[i] = scanner.nextLine();

            // Pedir las 3 notas numéricas
            double nota1 = solicitarNota(scanner);
            double nota2 = solicitarNota(scanner);
            double nota3 = solicitarNota(scanner);

            // Calcular el promedio
            promedios[i] = calcularPromedio(nota1, nota2, nota3);

            // Determinar si el estudiante aprueba o no
            estados[i] = determinarEstado(promedios[i]);
        }

        // Mostrar el resumen
        mostrarResumen(nombres, promedios, estados);
    }

    // Función para solicitar una nota
    private static double solicitarNota(Scanner scanner) {
        System.out.print("Nota: ");
        double nota = scanner.nextDouble();
        scanner.nextLine(); //Limpiar el salto de línea pendiente
        return nota;
    }

    // Función para calcular el promedio
    private static double calcularPromedio(double nota1, double nota2, double nota3) {
        return (nota1 + nota2 + nota3) / 3;
    }

    // Función para determinar el estado del estudiante
    private static String determinarEstado(double promedio) {
        return promedio >= 61 ? "Aprobado" : "Reprobado";
    }

    // Función para mostrar el resumen
    private static void mostrarResumen(String[] nombres, double[] promedios, String[] estados) {
        System.out.println("\nResumen de estudiantes:");
        System.out.println("---------------------------------");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println("Nombre: " + nombres[i] + " Promedio: " + promedios[i] + " Estado: " + estados[i]);
        }
    }
}