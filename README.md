# programacion-ii-semana2

import java.util.Scanner; // Importamos la clase Scanner para leer la entrada del usuario
public class Semana2_edad { // La clase debe ser pública y el nombre del archivo debe coincidir con el nombre de la clase
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Creamos un objeto Scanner llamado "scanner" para capturar la entrada del usuario
        System.out.print("Por favor, introduce tu nombre: ");     // Pedimos al usuario que introduzca su nombre
        String nombre = scanner.nextLine(); // Leemos la entrada del usuario y la guardamos en la variable "nombre"
        System.out.print("Por favor, introduce tu año de nacimiento: "); // Pedimos al usuario que introduzca su año de nacimiento
        int añoNacimiento = scanner.nextInt(); // Leemos la entrada del usuario y la guardamos en la variable "añoNacimiento"
        int añoActual = 2025; // Definimos el año actual
        int edad = añoActual - añoNacimiento; // Calculamos la edad del usuario restando su año de nacimiento del año actual
        System.out.println("Hola " + nombre + ", tu edad aproximada es: " + edad + " años."); // Imprimimos un mensaje en la consola con el nombre del usuario y su edad aproximada
    }
}
