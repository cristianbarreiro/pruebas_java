package subs;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
    private static final GestorSubscripciones gestor = new GestorSubscripciones("data/subscripciones.csv");
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        // Fuerza UTF-8 en la salida para que tildes y ñ se vean bien en cualquier consola
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println("=== Gestor de Subscripciones ===");
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1": agregarSubscripcion(); break;
                case "2": listarSubscripciones(); break;
                case "3": buscarSubscripcion(); break;
                case "4": registrarPago(); break;
                case "5": cancelarSubscripcion(); break;
                case "6": reactivarSubscripcion(); break;
                case "7": eliminarSubscripcion(); break;
                case "8": proximasAVencer(); break;
                case "9": resumenGastos(); break;
                case "0":
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("1. Agregar subscripción");
        System.out.println("2. Listar subscripciones");
        System.out.println("3. Buscar subscripción por nombre");
        System.out.println("4. Registrar pago (avanzar próximo pago)");
        System.out.println("5. Cancelar subscripción");
        System.out.println("6. Reactivar subscripción");
        System.out.println("7. Eliminar subscripción");
        System.out.println("8. Ver próximas a vencer");
        System.out.println("9. Resumen de gastos");
        System.out.println("0. Salir");
        System.out.println("---------------------------------------");
        System.out.print("Elige una opción: ");
    }

    private static void agregarSubscripcion() {
        System.out.print("Nombre de la app: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Categoría (ej: streaming, productividad, gaming): ");
        String categoria = sc.nextLine().trim();
        if (categoria.isEmpty()) categoria = "General";

        double precio = leerDouble("Precio: ");

        Subscripcion.TipoFacturacion tipo = leerTipoFacturacion();

        LocalDate fechaInicio = leerFecha("Fecha de inicio (yyyy-MM-dd) [enter = hoy]: ", LocalDate.now());

        System.out.print("Notas (opcional): ");
        String notas = sc.nextLine().trim();

        Subscripcion s = gestor.agregar(nombre, categoria, precio, tipo, fechaInicio, notas);
        System.out.println("Subscripción agregada correctamente:");
        System.out.println(s);
    }

    private static void listarSubscripciones() {
        System.out.print("¿Mostrar solo activas? (s/n) [n]: ");
        String r = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        List<Subscripcion> lista = r.equals("s") ? gestor.listarActivas() : gestor.listarTodas();

        if (lista.isEmpty()) {
            System.out.println("No hay subscripciones registradas.");
            return;
        }
        lista.forEach(System.out::println);
        System.out.printf("Total: %d subscripción(es)%n", lista.size());
    }

    private static void buscarSubscripcion() {
        System.out.print("Texto a buscar en el nombre: ");
        String texto = sc.nextLine().trim();
        List<Subscripcion> resultado = gestor.buscarPorNombre(texto);
        if (resultado.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    private static void registrarPago() {
        int id = leerInt("ID de la subscripción a la que registrar el pago: ");
        if (gestor.registrarPago(id)) {
            System.out.println("Pago registrado. Próximo pago actualizado.");
            gestor.buscarPorId(id).ifPresent(System.out::println);
        } else {
            System.out.println("No se encontró una subscripción con ese ID.");
        }
    }

    private static void cancelarSubscripcion() {
        int id = leerInt("ID de la subscripción a cancelar: ");
        if (gestor.cancelar(id)) {
            System.out.println("Subscripción cancelada.");
        } else {
            System.out.println("No se encontró una subscripción con ese ID.");
        }
    }

    private static void reactivarSubscripcion() {
        int id = leerInt("ID de la subscripción a reactivar: ");
        if (gestor.reactivar(id)) {
            System.out.println("Subscripción reactivada.");
        } else {
            System.out.println("No se encontró una subscripción con ese ID.");
        }
    }

    private static void eliminarSubscripcion() {
        int id = leerInt("ID de la subscripción a eliminar: ");
        System.out.print("¿Confirmas eliminación permanente? (s/n): ");
        String conf = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        if (!conf.equals("s")) {
            System.out.println("Operación cancelada.");
            return;
        }
        if (gestor.eliminar(id)) {
            System.out.println("Subscripción eliminada.");
        } else {
            System.out.println("No se encontró una subscripción con ese ID.");
        }
    }

    private static void proximasAVencer() {
        int dias = leerInt("Ver subscripciones que vencen en los próximos X días (ej: 7): ");
        List<Subscripcion> lista = gestor.proximasAVencer(dias);
        if (lista.isEmpty()) {
            System.out.println("No hay subscripciones activas que venzan en ese rango.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void resumenGastos() {
        System.out.printf("Gasto mensual total (equivalente): $%.2f%n", gestor.totalMensual());
        System.out.printf("Gasto anual total (equivalente): $%.2f%n", gestor.totalAnual());
        System.out.println("Desglose por categoría (mensual equivalente):");
        Map<String, Double> porCategoria = gestor.gastoPorCategoria();
        if (porCategoria.isEmpty()) {
            System.out.println("  (sin datos)");
        } else {
            porCategoria.forEach((cat, total) -> System.out.printf("  - %-15s $%.2f%n", cat, total));
        }
    }

    // --- Helpers de lectura ---
    private static double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, ingresa un número (ej: 9.99).");
            }
        }
    }

    private static int leerInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, ingresa un número entero.");
            }
        }
    }

    private static LocalDate leerFecha(String prompt, LocalDate porDefecto) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) return porDefecto;
            try {
                return LocalDate.parse(linea, FMT);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido, usa yyyy-MM-dd (ej: 2026-08-17).");
            }
        }
    }

    private static Subscripcion.TipoFacturacion leerTipoFacturacion() {
        while (true) {
            System.out.print("Tipo de facturación (1=Mensual, 2=Anual): ");
            String linea = sc.nextLine().trim();
            if (linea.equals("1")) return Subscripcion.TipoFacturacion.MENSUAL;
            if (linea.equals("2")) return Subscripcion.TipoFacturacion.ANUAL;
            System.out.println("Opción inválida, escribe 1 o 2.");
        }
    }
}
