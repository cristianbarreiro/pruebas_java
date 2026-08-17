package subs;

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

    public void guardarCambios() {
        guardar();
    }

    public List<Subscripcion> listarTodas() {
        return new ArrayList<>(subscripciones);
    }

    public List<Subscripcion> listarActivas() {
        return subscripciones.stream()
                .filter(s -> s.getEstado() == Subscripcion.Estado.ACTIVA)
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

    public List<Subscripcion> buscarPorNombre(String texto) {
        String q = texto.toLowerCase();
        return subscripciones.stream()
                .filter(s -> s.getNombreApp().toLowerCase().contains(q))
                .collect(Collectors.toList());
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

    // --- Persistencia ---
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
