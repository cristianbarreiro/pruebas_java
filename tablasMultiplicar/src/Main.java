import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Elige una opcion: ");

            switch (opcion) {
                case 1:
                    int numero = leerEntero(scanner, "Escribe un numero del 1 al 10: ");
                    if (numero >= 1 && numero <= 10) {
                        mostrarTabla(numero);
                    } else {
                        System.out.println("El numero debe estar entre 1 y 10.");
                    }
                    break;
                case 2:
                    mostrarTodasLasTablas();
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== TABLAS DE MULTIPLICAR ===");
        System.out.println("1. Ver una tabla");
        System.out.println("2. Ver todas las tablas");
        System.out.println("0. Salir");
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Introduce un numero entero.");
            scanner.next();
            System.out.print(mensaje);
        }
        return scanner.nextInt();
    }

    private static void mostrarTabla(int numero) {
        System.out.println("\nTabla del " + numero);
        for (int multiplicador = 1; multiplicador <= 10; multiplicador++) {
            System.out.println(numero + " x " + multiplicador + " = " + (numero * multiplicador));
        }
    }

    private static void mostrarTodasLasTablas() {
        for (int numero = 1; numero <= 10; numero++) {
            mostrarTabla(numero);
        }
    }
}
