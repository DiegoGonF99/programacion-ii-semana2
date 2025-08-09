import java.util.ArrayList;
import java.util.Scanner;

public class CajeroAutomatico {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> historial = new ArrayList<>();

    // Procedimiento para mostrar el menú
    public static void mostrarMenu() {
        System.out.println("\n💳 Bienvenido al Cajero Automático - I´m getting rich 💸");
        System.out.println("1️⃣  Consultar saldo");
        System.out.println("2️⃣  Depositar dinero");
        System.out.println("3️⃣  Retirar dinero");
        System.out.println("4️⃣  Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Mostrar frase motivacional
    public static void mostrarFrase(double saldo) {
        if (saldo >= 5000) {
            System.out.println("💎 ¡Damn bro, you got a lot of money!");
        } else if (saldo >= 1000) {
            System.out.println("📈 ¡You are good bro, keep it up!");
        } else {
            System.out.println("💡 ¡Check out bro! You´re running out of money.");
        }
    }

    // Procedimiento para consultar saldo
    public static void consultarSaldo(double[] saldo) {
        System.out.printf("💰 Su saldo actual es: Q%.2f\n", saldo[0]);
        historial.add("Consulta de saldo: Q" + String.format("%.2f", saldo[0]));
        mostrarFrase(saldo[0]);
    }

    // Procedimiento para depositar dinero
    public static void depositarDinero(double[] saldo) {
        System.out.print("Ingrese el monto a depositar: Q");
        double deposito = sc.nextDouble();
        saldo[0] += deposito;
        System.out.println("✅ Depósito realizado con éxito.");
        historial.add("Depósito de Q" + String.format("%.2f", deposito));
        mostrarFrase(saldo[0]);
    }

    // Procedimiento para retirar dinero
    public static void retirarDinero(double[] saldo) {
        System.out.print("Ingrese el monto a retirar: Q");
        double retiro = sc.nextDouble();
        if (retiro <= saldo[0]) {
            saldo[0] -= retiro;
            System.out.println("💸 Retiro realizado con éxito.");
            historial.add("Retiro de Q" + String.format("%.2f", retiro));
        } else {
            System.out.println("⚠️ YOUR WALLET IS EMPTY.");
            historial.add("Intento fallido de retiro: Q" + String.format("%.2f", retiro));
        }
        mostrarFrase(saldo[0]);
    }

    // Procedimiento para iniciar sesión (flujo principal)
    public static void iniciarSesion() {
        double[] saldo = {1500.00}; // saldo inicial en arreglo para simular referencia
        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> consultarSaldo(saldo);
                case 2 -> depositarDinero(saldo);
                case 3 -> retirarDinero(saldo);
                case 4 -> {
                    System.out.println("👋 Thank you for using the ATM. ¡Have a great day!");
                    mostrarHistorial();
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 4);
    }

    // Mostrar historial usando Streams
    public static void mostrarHistorial() {
        System.out.println("\n📜 Historial de transacciones:");
        historial.stream().forEach(t -> System.out.println("• " + t));
    }

    public static void main(String[] args) {
        iniciarSesion();
    }
}