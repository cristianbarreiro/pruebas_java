package matriculacionmanual;

import java.util.HashSet;
import java.util.Objects;

public class Curso {
    private final String codigo;
    private String nombre;
    private final int aforo;
    private final HashSet<Integer> idsMatriculados;

    public Curso(String codigo, String nombre, int aforo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.aforo = aforo;
        this.idsMatriculados = new HashSet<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAforo() {
        return aforo;
    }

    public HashSet<Integer> getIdsMatriculados() {
        return idsMatriculados;
    }

    public boolean estaMatriculado(int id) {
        return idsMatriculados.contains(id);
    }

    public boolean matricular(int id) {
        return idsMatriculados.add(id);
    }

    public boolean desmatricular(int id) {
        return idsMatriculados.remove(id);
    }

    public boolean hayPlaza() {
        return idsMatriculados.size() < aforo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        return codigo.equals(((Curso) o).codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Curso{codigo='" + codigo + "', nombre='" + nombre + "', aforo=" + aforo + "}";
    }
}
