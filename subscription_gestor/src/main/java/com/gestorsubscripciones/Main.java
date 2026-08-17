package com.gestorsubscripciones;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
    private static final GestorSubscripciones gestor = new GestorSubscripciones("data/subscripciones.csv");
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println("========================================");
        System.out.println("   Gestor de Subscripciones");
        System.out.println("========================================");

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1": agregarSubscripcion(); break;
                case "2": editarSubscripcion(); break;
                case "3": eliminarSubscripcion(); break;
                case "4": registrarPago(); break;
                case "5": listarSubscripciones(); break;
                case "6": buscarSubscripcion(); break;
                case "7": cancelarSubscripcion(); break;
                case "8": reactivarSubscripcion(); break;
                case "9": proximasAVencer(); break;
                case "10": timelinePagos(); break;
                case "11": calendarioDelMes(); break;
                case "12": porRangoFechas(); break;
                case "13": gastosPorMes(); break;
                case "14": dashboard(); break;
                case "15": resumenGastos(); break;
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
        System.out.println("--- Gestión ---");
        System.out.println("1.  Agregar suscripción");
        System.out.println("2.  Editar suscripción");
        System.out.println("3.  Eliminar suscripción");
        System.out.println("4.  Registrar pago");
        System.out.println();
        System.out.println("--- Consulta ---");
        System.out.println("5.  Listar suscripciones");
        System.out.println("6.  Buscar por nombre");
        System.out.println("7.  Cancelar suscripción");
        System.out.println("8.  Reactivar suscripción");
        System.out.println();
        System.out.println("--- Fechas ---");
        System.out.println("9.  Próximas a vencer");
        System.out.println("10. Timeline de pagos (cronológico)");
        System.out.println("11. Calendario de pagos del mes");
        System.out.println("12. Ver por rango de fechas");
        System.out.println("13. Gastos por mes del año");
        System.out.println();
        System.out.println("--- Resumen ---");
        System.out.println("14. Dashboard");
        System.out.println("15. Resumen de gastos");
        System.out.println();
        System.out.println("0.  Salir");
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

    private static void editarSubscripcion() {
        int id = leerInt("ID de la suscripción a editar: ");
        Optional<Subscripcion> opt = gestor.buscarPorId(id);
        if (opt.isEmpty()) {
            System.out.println("No se encontró una suscripción con ese ID.");
            return;
        }
        Subscripcion s = opt.get();
        System.out.println("Editando:");
        System.out.println(s);
        System.out.println();

        System.out.printf("Nombre actual: %s%n", s.getNombreApp());
        System.out.print("Nuevo nombre (enter = no cambiar): ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) nombre = null;

        System.out.printf("Categoría actual: %s%n", s.getCategoria());
        System.out.print("Nueva categoría (enter = no cambiar): ");
        String categoria = sc.nextLine().trim();
        if (categoria.isEmpty()) categoria = null;

        System.out.printf("Precio actual: $%.2f%n", s.getPrecio());
        System.out.print("Nuevo precio (enter = no cambiar): ");
        String precioStr = sc.nextLine().trim();
        Double precio = null;
        if (!precioStr.isEmpty()) {
            try {
                precio = Double.parseDouble(precioStr.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido, no se cambia.");
            }
        }

        System.out.printf("Tipo actual: %s%n", s.getTipoFacturacion());
        System.out.print("Nuevo tipo (1=Mensual, 2=Anual, enter = no cambiar): ");
        String tipoStr = sc.nextLine().trim();
        Subscripcion.TipoFacturacion tipo = null;
        if (tipoStr.equals("1")) tipo = Subscripcion.TipoFacturacion.MENSUAL;
        else if (tipoStr.equals("2")) tipo = Subscripcion.TipoFacturacion.ANUAL;

        System.out.printf("Notas actuales: %s%n", s.getNotas());
        System.out.print("Nuevas notas (enter = no cambiar): ");
        String notas = sc.nextLine().trim();
        if (notas.isEmpty()) notas = null;

        if (gestor.editar(id, nombre, categoria, precio, tipo, notas)) {
            System.out.println("Suscripción editada correctamente:");
            gestor.buscarPorId(id).ifPresent(System.out::println);
        } else {
            System.out.println("Error al editar la suscripción.");
        }
    }

    private static void eliminarSubscripcion() {
        int id = leerInt("ID de la suscripción a eliminar: ");
        System.out.print("¿Confirmas eliminación permanente? (s/n): ");
        String conf = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        if (!conf.equals("s")) {
            System.out.println("Operación cancelada.");
            return;
        }
        if (gestor.eliminar(id)) {
            System.out.println("Suscripción eliminada.");
        } else {
            System.out.println("No se encontró una suscripción con ese ID.");
        }
    }

    private static void registrarPago() {
        int id = leerInt("ID de la suscripción a la que registrar el pago: ");
        if (gestor.registrarPago(id)) {
            System.out.println("Pago registrado. Próximo pago actualizado.");
            gestor.buscarPorId(id).ifPresent(System.out::println);
        } else {
            System.out.println("No se encontró una suscripción con ese ID.");
        }
    }

    private static void listarSubscripciones() {
        System.out.print("¿Mostrar solo activas? (s/n) [n]: ");
        String r = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        List<Subscripcion> lista = r.equals("s") ? gestor.listarActivas() : gestor.listarTodas();

        if (lista.isEmpty()) {
            System.out.println("No hay suscripciones registradas.");
            return;
        }
        lista.forEach(System.out::println);
        System.out.printf("Total: %d suscripción(es)%n", lista.size());
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

    private static void cancelarSubscripcion() {
        int id = leerInt("ID de la suscripción a cancelar: ");
        if (gestor.cancelar(id)) {
            System.out.println("Suscripción cancelada.");
        } else {
            System.out.println("No se encontró una suscripción con ese ID.");
        }
    }

    private static void reactivarSubscripcion() {
        int id = leerInt("ID de la suscripción a reactivar: ");
        if (gestor.reactivar(id)) {
            System.out.println("Suscripción reactivada.");
        } else {
            System.out.println("No se encontró una suscripción con ese ID.");
        }
    }

    private static void proximasAVencer() {
        int dias = leerInt("Ver suscripciones que vencen en los próximos X días (ej: 7): ");
        List<Subscripcion> lista = gestor.proximasAVencer(dias);
        if (lista.isEmpty()) {
            System.out.println("No hay suscripciones activas que venzan en ese rango.");
        } else {
            lista.forEach(System.out::println);
            System.out.printf("Total: %d suscripción(es)%n", lista.size());
        }
    }

    private static void timelinePagos() {
        List<Subscripcion> lista = gestor.timelinePagos();
        if (lista.isEmpty()) {
            System.out.println("No hay suscripciones activas.");
            return;
        }
        System.out.println("Próximos pagos (orden cronológico):");
        System.out.println();
        for (Subscripcion s : lista) {
            long dias = s.diasHastaProximoPago();
            String diasStr = dias == 0 ? "HOY" : dias + " días";
            System.out.printf("  %-20s → %s  $%-10.2f (%s)%n",
                    s.getNombreApp(), s.getProximoPago(), s.getPrecio(), diasStr);
        }
        System.out.printf("%nTotal: %d pago(s) programado(s)%n", lista.size());
    }

    private static void calendarioDelMes() {
        System.out.print("Mes (1-12): ");
        int mes = leerInt("");
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inválido.");
            return;
        }
        System.out.print("Año (ej: 2026): ");
        int anio = leerInt("");

        List<Map.Entry<LocalDate, Subscripcion>> lista = gestor.calendarioDelMes(mes, anio);
        String nombreMes = Month.of(mes).getDisplayName(java.time.format.TextStyle.FULL, Locale.of("es", "ES"));
        System.out.println();
        System.out.printf("Calendario - %s %d:%n", nombreMes, anio);

        if (lista.isEmpty()) {
            System.out.println("  No hay pagos programados para este mes.");
            return;
        }

        double total = 0;
        for (Map.Entry<LocalDate, Subscripcion> entry : lista) {
            System.out.printf("  día %02d → %-20s $%.2f%n",
                    entry.getKey().getDayOfMonth(), entry.getValue().getNombreApp(), entry.getValue().getPrecio());
            total += entry.getValue().getPrecio();
        }
        System.out.printf("  ─────────────────────────────%n");
        System.out.printf("  Total: $%.2f%n", total);
    }

    private static void porRangoFechas() {
        LocalDate inicio = leerFecha("Fecha inicio (yyyy-MM-dd): ", null);
        if (inicio == null) {
            System.out.println("Debes ingresar una fecha.");
            return;
        }
        LocalDate fin = leerFecha("Fecha fin (yyyy-MM-dd): ", null);
        if (fin == null) {
            System.out.println("Debes ingresar una fecha.");
            return;
        }
        if (fin.isBefore(inicio)) {
            System.out.println("La fecha fin no puede ser anterior a la fecha inicio.");
            return;
        }

        List<Map.Entry<LocalDate, Subscripcion>> lista = gestor.porRangoFechas(inicio, fin);
        System.out.println();
        System.out.printf("Pagos del %s al %s:%n", inicio.format(FMT), fin.format(FMT));

        if (lista.isEmpty()) {
            System.out.println("  No hay pagos programados en ese rango.");
            return;
        }

        double total = 0;
        for (Map.Entry<LocalDate, Subscripcion> entry : lista) {
            System.out.printf("  %s  %-20s $%.2f%n",
                    entry.getKey().format(FMT), entry.getValue().getNombreApp(), entry.getValue().getPrecio());
            total += entry.getValue().getPrecio();
        }
        System.out.printf("  ──────────────────────────────────%n");
        System.out.printf("  Total: $%.2f (%d pago(s))%n", total, lista.size());
    }

    private static void gastosPorMes() {
        System.out.print("Año (ej: 2026): ");
        int anio = leerInt("");
        System.out.println();

        Map<Integer, Double> gastos = gestor.gastosPorMes(anio);
        System.out.printf("Gastos %d:%n", anio);

        double totalAnual = 0;
        for (Map.Entry<Integer, Double> entry : gastos.entrySet()) {
            String nombreMes = Month.of(entry.getKey())
                    .getDisplayName(java.time.format.TextStyle.FULL, Locale.of("es", "ES"));
            System.out.printf("  %-10s $%.2f%n", nombreMes, entry.getValue());
            totalAnual += entry.getValue();
        }
        System.out.printf("  ────────────%n");
        System.out.printf("  Total anual: $%.2f%n", totalAnual);
    }

    private static void dashboard() {
        List<Subscripcion> activas = gestor.listarActivas();
        System.out.println();
        System.out.println("=== Dashboard ===");
        System.out.printf("Suscripciones activas: %d%n", activas.size());
        System.out.printf("Gasto mensual: $%.2f%n", gestor.totalMensual());

        Optional<Subscripcion> proxima = gestor.proximoPagoGeneral();
        if (proxima.isPresent()) {
            Subscripcion s = proxima.get();
            long dias = s.diasHastaProximoPago();
            String diasStr = dias == 0 ? "HOY" : "en " + dias + " días";
            System.out.printf("Próximo pago: %s → %s ($%.2f) (%s)%n",
                    s.getNombreApp(), s.getProximoPago(), s.getPrecio(), diasStr);
        } else {
            System.out.println("Próximo pago: Ninguna suscripción activa");
        }

        List<Subscripcion> en7 = gestor.proximasAVencer(7);
        System.out.printf("Pagos próximos (7 días): %d%n", en7.size());
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
            if (linea.isEmpty() && porDefecto != null) return porDefecto;
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
