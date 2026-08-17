package com.gestorsubscripciones;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestorSubscripciones {

    private final List<Subscripcion> subscripciones = new ArrayList<>();
    private final String archivo;
    private int siguienteId = 1;

    public GestorSubscripciones(String archivo) {
        this.archivo = archivo;
        cargar();
    }

    public Subscripcion agregar(String nombreApp, String categoria, double precio,
                                Subscripcion.TipoFacturacion tipo, LocalDate fechaInicio, String notas) {
        Subscripcion s = Subscripcion.nueva(siguienteId++, nombreApp, categoria, precio, tipo, fechaInicio, notas);
        subscripciones.add(s);
        guardar();
        return s;
    }

    public boolean editar(int id, String nombre, String categoria, Double precio,
                          Subscripcion.TipoFacturacion tipoFacturacion, String notas) {
        Optional<Subscripcion> opt = buscarPorId(id);
        if (opt.isEmpty()) return false;
        Subscripcion s = opt.get();
        if (nombre != null) s.setNombreApp(nombre);
        if (categoria != null) s.setCategoria(categoria);
        if (precio != null) s.setPrecio(precio);
        if (tipoFacturacion != null) {
            s.setTipoFacturacion(tipoFacturacion);
            s.setProximoPago(Subscripcion.calcularProximoPago(s.getFechaInicio(), tipoFacturacion));
        }
        if (notas != null) s.setNotas(notas);
        guardar();
        return true;
    }

    public boolean eliminar(int id) {
        boolean removido = subscripciones.removeIf(s -> s.getId() == id);
        if (removido) guardar();
        return removido;
    }

    public Optional<Subscripcion> buscarPorId(int id) {
        return subscripciones.stream().filter(s -> s.getId() == id).findFirst();
    }

    public boolean cancelar(int id) {
        Optional<Subscripcion> s = buscarPorId(id);
        if (s.isPresent()) {
            s.get().setEstado(Subscripcion.Estado.CANCELADA);
            guardar();
            return true;
        }
        return false;
    }

    public boolean reactivar(int id) {
        Optional<Subscripcion> s = buscarPorId(id);
        if (s.isPresent()) {
            s.get().setEstado(Subscripcion.Estado.ACTIVA);
            if (s.get().getProximoPago().isBefore(LocalDate.now())) {
                s.get().setProximoPago(Subscripcion.calcularProximoPago(LocalDate.now(), s.get().getTipoFacturacion()));
            }
            guardar();
            return true;
        }
        return false;
    }

    public boolean registrarPago(int id) {
        Optional<Subscripcion> s = buscarPorId(id);
        if (s.isPresent()) {
            s.get().avanzarProximoPago();
            guardar();
            return true;
        }
        return false;
    }

    public List<Subscripcion> listarTodas() {
        return new ArrayList<>(subscripciones);
    }

    public List<Subscripcion> listarActivas() {
        return subscripciones.stream()
                .filter(s -> s.getEstado() == Subscripcion.Estado.ACTIVA)
                .collect(Collectors.toList());
    }

    public List<Subscripcion> buscarPorNombre(String texto) {
        String q = texto.toLowerCase();
        return subscripciones.stream()
                .filter(s -> s.getNombreApp().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public List<Subscripcion> proximasAVencer(int dias) {
        LocalDate limite = LocalDate.now().plusDays(dias);
        return subscripciones.stream()
                .filter(s -> s.getEstado() == Subscripcion.Estado.ACTIVA)
                .filter(s -> !s.getProximoPago().isAfter(limite))
                .sorted(Comparator.comparing(Subscripcion::getProximoPago))
                .collect(Collectors.toList());
    }

    public List<Subscripcion> timelinePagos() {
        return listarActivas().stream()
                .sorted(Comparator.comparing(Subscripcion::getProximoPago))
                .collect(Collectors.toList());
    }

    public List<Map.Entry<LocalDate, Subscripcion>> calendarioDelMes(int mes, int anio) {
        List<Map.Entry<LocalDate, Subscripcion>> resultado = new ArrayList<>();
        for (Subscripcion s : listarActivas()) {
            LocalDate fecha = s.fechaPagoEnMes(mes, anio);
            if (fecha != null) {
                resultado.add(Map.entry(fecha, s));
            }
        }
        resultado.sort(Map.Entry.comparingByKey());
        return resultado;
    }

    public List<Map.Entry<LocalDate, Subscripcion>> porRangoFechas(LocalDate inicio, LocalDate fin) {
        List<Map.Entry<LocalDate, Subscripcion>> resultado = new ArrayList<>();
        for (Subscripcion s : listarActivas()) {
            LocalDate mesActual = inicio.withDayOfMonth(1);
            while (!mesActual.isAfter(fin.withDayOfMonth(1))) {
                LocalDate fecha = s.fechaPagoEnMes(mesActual.getMonthValue(), mesActual.getYear());
                if (fecha != null && !fecha.isBefore(inicio) && !fecha.isAfter(fin)) {
                    resultado.add(Map.entry(fecha, s));
                }
                mesActual = mesActual.plusMonths(1);
            }
        }
        resultado.sort(Map.Entry.comparingByKey());
        return resultado;
    }

    public Map<Integer, Double> gastosPorMes(int anio) {
        Map<Integer, Double> gastos = new TreeMap<>();
        for (int mes = 1; mes <= 12; mes++) {
            double total = 0;
            for (Subscripcion s : listarActivas()) {
                LocalDate fecha = s.fechaPagoEnMes(mes, anio);
                if (fecha != null) {
                    total += s.getPrecio();
                }
            }
            gastos.put(mes, total);
        }
        return gastos;
    }

    public double totalMensual() {
        return listarActivas().stream().mapToDouble(Subscripcion::costoMensualEquivalente).sum();
    }

    public double totalAnual() {
        return listarActivas().stream().mapToDouble(Subscripcion::costoAnualEquivalente).sum();
    }

    public Map<String, Double> gastoPorCategoria() {
        Map<String, Double> mapa = new TreeMap<>();
        for (Subscripcion s : listarActivas()) {
            mapa.merge(s.getCategoria(), s.costoMensualEquivalente(), Double::sum);
        }
        return mapa;
    }

    public Optional<Subscripcion> proximoPagoGeneral() {
        return listarActivas().stream()
                .min(Comparator.comparing(Subscripcion::getProximoPago));
    }

    private void guardar() {
        try {
            Path p = Paths.get(archivo);
            if (p.getParent() != null) Files.createDirectories(p.getParent());
            try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
                for (Subscripcion s : subscripciones) {
                    bw.write(s.toCsv());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private void cargar() {
        Path p = Paths.get(archivo);
        if (!Files.exists(p)) return;
        try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                try {
                    Subscripcion s = Subscripcion.fromCsv(linea);
                    subscripciones.add(s);
                    if (s.getId() >= siguienteId) siguienteId = s.getId() + 1;
                } catch (Exception ex) {
                    System.out.println("Aviso: se ignoró una línea corrupta en el archivo de datos.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }
}
