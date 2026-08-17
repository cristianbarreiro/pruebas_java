package com.gestorsubscripciones;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Subscripcion {

    public enum TipoFacturacion {
        MENSUAL,
        ANUAL
    }

    public enum Estado {
        ACTIVA,
        CANCELADA
    }

    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    private int id;
    private String nombreApp;
    private String categoria;
    private double precio;
    private TipoFacturacion tipoFacturacion;
    private LocalDate fechaInicio;
    private LocalDate proximoPago;
    private Estado estado;
    private String notas;

    public Subscripcion(int id, String nombreApp, String categoria, double precio,
                        TipoFacturacion tipoFacturacion, LocalDate fechaInicio,
                        LocalDate proximoPago, Estado estado, String notas) {
        this.id = id;
        this.nombreApp = nombreApp;
        this.categoria = categoria;
        this.precio = precio;
        this.tipoFacturacion = tipoFacturacion;
        this.fechaInicio = fechaInicio;
        this.proximoPago = proximoPago;
        this.estado = estado;
        this.notas = notas == null ? "" : notas;
    }

    public static Subscripcion nueva(int id, String nombreApp, String categoria, double precio,
                                     TipoFacturacion tipoFacturacion, LocalDate fechaInicio, String notas) {
        LocalDate proximo = calcularProximoPago(fechaInicio, tipoFacturacion);
        return new Subscripcion(id, nombreApp, categoria, precio, tipoFacturacion,
                fechaInicio, proximo, Estado.ACTIVA, notas);
    }

    public static LocalDate calcularProximoPago(LocalDate desde, TipoFacturacion tipo) {
        return tipo == TipoFacturacion.MENSUAL ? desde.plusMonths(1) : desde.plusYears(1);
    }

    public void avanzarProximoPago() {
        this.proximoPago = calcularProximoPago(this.proximoPago, this.tipoFacturacion);
    }

    public double costoMensualEquivalente() {
        return tipoFacturacion == TipoFacturacion.MENSUAL ? precio : precio / 12.0;
    }

    public double costoAnualEquivalente() {
        return tipoFacturacion == TipoFacturacion.MENSUAL ? precio * 12.0 : precio;
    }

    public long diasHastaProximoPago() {
        return ChronoUnit.DAYS.between(LocalDate.now(), proximoPago);
    }

    public LocalDate fechaPagoEnMes(int mes, int anio) {
        if (tipoFacturacion == TipoFacturacion.MENSUAL) {
            if (fechaInicio.isAfter(LocalDate.of(anio, mes, 1))) return null;
            int dia = Math.min(fechaInicio.getDayOfMonth(),
                    YearMonth.of(anio, mes).lengthOfMonth());
            return LocalDate.of(anio, mes, dia);
        } else {
            if (mes == fechaInicio.getMonthValue() && anio >= fechaInicio.getYear()) {
                int dia = Math.min(fechaInicio.getDayOfMonth(),
                        YearMonth.of(anio, mes).lengthOfMonth());
                return LocalDate.of(anio, mes, dia);
            }
            return null;
        }
    }

    public int getId() { return id; }
    public String getNombreApp() { return nombreApp; }
    public void setNombreApp(String nombreApp) { this.nombreApp = nombreApp; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public TipoFacturacion getTipoFacturacion() { return tipoFacturacion; }
    public void setTipoFacturacion(TipoFacturacion tipoFacturacion) { this.tipoFacturacion = tipoFacturacion; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getProximoPago() { return proximoPago; }
    public void setProximoPago(LocalDate proximoPago) { this.proximoPago = proximoPago; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public String toCsv() {
        return String.join(";",
                String.valueOf(id),
                escapar(nombreApp),
                escapar(categoria),
                String.valueOf(precio),
                tipoFacturacion.name(),
                fechaInicio.format(FMT),
                proximoPago.format(FMT),
                estado.name(),
                escapar(notas)
        );
    }

    public static Subscripcion fromCsv(String linea) {
        String[] p = linea.split(";", -1);
        return new Subscripcion(
                Integer.parseInt(p[0]),
                desescapar(p[1]),
                desescapar(p[2]),
                Double.parseDouble(p[3]),
                TipoFacturacion.valueOf(p[4]),
                LocalDate.parse(p[5], FMT),
                LocalDate.parse(p[6], FMT),
                Estado.valueOf(p[7]),
                p.length > 8 ? desescapar(p[8]) : ""
        );
    }

    private static String escapar(String s) {
        return s == null ? "" : s.replace(";", ",");
    }

    private static String desescapar(String s) {
        return s;
    }

    @Override
    public String toString() {
        long dias = diasHastaProximoPago();
        String estadoStr = estado == Estado.ACTIVA ? "ACTIVA" : "CANCELADA";
        return String.format("#%-3d %-20s %-12s $%-10.2f %-8s inicio:%-12s prox.pago:%-12s [%s] (%d días)",
                id, nombreApp, categoria, precio, tipoFacturacion, fechaInicio, proximoPago, estadoStr, dias);
    }
}
