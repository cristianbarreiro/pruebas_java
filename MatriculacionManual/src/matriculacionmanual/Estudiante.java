package matriculacionmanual;

import java.util.HashSet;
import java.util.Objects;

public class Estudiante {
    private final int id;
    private String nombre;
    private final HashSet<String> cursosMatriculados;

    public Estudiante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.cursosMatriculados = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashSet<String> getCursosMatriculados() {
        return cursosMatriculados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudiante)) return false;
        return id == ((Estudiante) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Estudiante{id=" + id + ", nombre='" + nombre + "'}";
    }
}
