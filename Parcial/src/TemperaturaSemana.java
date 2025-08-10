import java.util.Scanner;
import java.util.ArrayList;

public class TemperaturaSemana {

    static Scanner scanner = new Scanner(System.in);
    static double[] temperaturas = new double[7];
    static String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    static boolean datosIngresados = false;

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ingresarTemperaturas();
                    datosIngresados = true;
                    break;
                case 2:
                    if (datosIngresados) {
                        mostrarTemperaturas();
                    } else {
                        System.out.println("⚠️ Primero debes ingresar las temperaturas.");
                    }
                    break;
                case 3:
                    if (datosIngresados) {
                        double max = obtenerTemperaturaMaxima();
                        int indice = obtenerIndiceTemperaturaMaxima(max);
                        mostrarMaxima(max);
                        mostrarMaxima(max, dias[indice]);
                    } else {
                        System.out.println("⚠️ Primero debes ingresar las temperaturas.");
                    }
                    break;
                case 4:
                    if (datosIngresados) {
                        double suma = sumarTemperaturas(0);
                        System.out.println("📊 Suma total de temperaturas: " + suma + "°C");
                    } else {
                        System.out.println("⚠️ Primero debes ingresar las temperaturas.");
                    }
                    break;
                case 5:
                    System.out.println("👋 ¡Gracias por usar el programa!");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta otra vez.");
            }
        } while (opcion != 5);
    }

    static void mostrarMenu() {
        System.out.println("\n🌡️ MENÚ DE CONTROL DE TEMPERATURAS 🌡️");
        System.out.println("1️⃣ Ingresar temperaturas");
        System.out.println("2️⃣ Mostrar todas las temperaturas");
        System.out.println("3️⃣ Mostrar temperatura máxima");
        System.out.println("4️⃣ (Extra) Mostrar suma total de temperaturas");
        System.out.println("5️⃣ Salir");
        System.out.print("👉 Elige una opción: ");
    }

    static void ingresarTemperaturas() {
        System.out.println("📝 Ingresa las temperaturas máximas de la semana:");
        for (int i = 0; i < temperaturas.length; i++) {
            System.out.print("🌞 " + dias[i] + ": ");
            temperaturas[i] = scanner.nextDouble();
        }
        System.out.println("✅ ¡Temperaturas guardadas correctamente!");
    }

    static void mostrarTemperaturas() {
        System.out.println("\n📋 Temperaturas registradas:");
        for (int i = 0; i < temperaturas.length; i++) {
            System.out.println("📅 " + dias[i] + ": " + temperaturas[i] + "°C");
        }
    }

    static double obtenerTemperaturaMaxima() {
        double max = temperaturas[0];
        for (int i = 1; i < temperaturas.length; i++) {
            if (temperaturas[i] > max) {
                max = temperaturas[i];
            }
        }
        return max;
    }

    static int obtenerIndiceTemperaturaMaxima(double max) {
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] == max) {
                return i;
            }
        }
        return -1; // por si acaso
    }

    // Método sobrecargado 1
    static void mostrarMaxima(double temperatura) {
        System.out.println("🔥 Temperatura máxima: " + temperatura + "°C");
    }

    // Método sobrecargado 2
    static void mostrarMaxima(double temperatura, String dia) {
        System.out.println("📆 Día más caluroso: " + dia + " con " + temperatura + "°C");
    }

    // Función recursiva para sumar temperaturas
    static double sumarTemperaturas(int indice) {
        if (indice == temperaturas.length) {
            return 0;
        } else {
            return temperaturas[indice] + sumarTemperaturas(indice + 1);
        }
    }
}
