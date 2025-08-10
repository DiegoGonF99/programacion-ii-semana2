import java.util.Scanner;

public class EscaleraNumerica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("🔢 Ingrese el número de niveles: ");
        int niveles = scanner.nextInt();

        if (niveles < 1) {
            System.out.println("⚠️ Error: El número debe ser mayor o igual a 1.");
            return;
        }

        // Escalera numérica
        System.out.println("\n📈 Escalera Numérica Ascendente:");
        imprimirEscaleraAscendente(niveles);

        System.out.println("\n📉 Escalera Numérica Descendente:");
        imprimirEscaleraDescendente(niveles - 1);

        // Escalera con símbolos
        System.out.println("\n🌟 Escalera de Estrellas Ascendente:");
        imprimirEscaleraSimbolosAscendente(niveles, '*');

        System.out.println("\n🌙 Escalera de Estrellas Descendente:");
        imprimirEscaleraSimbolosDescendente(niveles - 1, '*');
    }

    // ==========================
    // ESCALERA NUMÉRICA
    // ==========================

    static void imprimirEscaleraAscendente(int n) {
        for (int i = 1; i <= n; i++) {
            imprimirLinea(i);
        }
    }

    static void imprimirEscaleraDescendente(int n) {
        if (n == 0) return;
        imprimirLinea(n);
        imprimirEscaleraDescendente(n - 1);
    }

    // Método sobrecargado 1: imprime números
    static void imprimirLinea(int nivel) {
        for (int i = 1; i <= nivel; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // ==========================
    // ESCALERA DE ESTRELLAS (simbolos)
    // ==========================

    static void imprimirEscaleraSimbolosAscendente(int n, char simbolo) {
        for (int i = 1; i <= n; i++) {
            imprimirLinea(i, simbolo);
        }
    }

    static void imprimirEscaleraSimbolosDescendente(int n, char simbolo) {
        if (n == 0) return;
        imprimirLinea(n, simbolo);
        imprimirEscaleraSimbolosDescendente(n - 1, simbolo);
    }

    // Método sobrecargado 2: imprime un símbolo
    static void imprimirLinea(int nivel, char simbolo) {
        for (int i = 1; i <= nivel; i++) {
            System.out.print(simbolo + " ");
        }
        System.out.println();
    }
}