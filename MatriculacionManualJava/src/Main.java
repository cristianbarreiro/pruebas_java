import java.util.*;

public class Main {

    public static void main(String[] args) {

        int option;
        Scanner sc = new Scanner(System.in);
        HashSet<Estudiante> estudiantes = new HashSet<>();
        HashSet<Curso> cursos = new HashSet<>();

        do {
            mostrarMenu();
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
                System.out.println("Error: introduce un número.");
                option = 0;
                continue;
            }

            switch(option) {
                case 1:
                    anadirEstudiante(sc, estudiantes);
                    break;
                case 2:
                    anadirCurso(sc, cursos);
                    break;
                case 3:
                    inscribirEstudiante(sc, estudiantes, cursos);
                    break;
                case 4:
                    darDeBaja(sc, estudiantes, cursos);
                    break;
                case 5:
                    verDetallesEstudiante(sc, estudiantes, cursos);
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (option != 6);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Instituto BrightPath ===");
        System.out.println("1. Añadir estudiante");
        System.out.println("2. Añadir curso");
        System.out.println("3. Inscribir a un estudiante en un curso");
        System.out.println("4. Darse de baja de un curso");
        System.out.println("5. Ver detalles del estudiante");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void anadirEstudiante(Scanner sc, HashSet<Estudiante> estudiantes) {
        int id = leerEnteroPositivo(sc, "ID del estudiante");
        String nombre = leerTexto(sc, "Nombre");

        if (estudiantes.add(new Estudiante(id, nombre))) {
            System.out.println("Estudiante añadido.");
        } else {
            System.out.println("Error: ya existe un estudiante con el ID " + id);
        }
    }

    private static void anadirCurso(Scanner sc, HashSet<Curso> cursos) {
        String codigo = leerTexto(sc, "Código del curso");
        String nombre = leerTexto(sc, "Nombre del curso");
        int aforo = leerEnteroPositivo(sc, "Aforo máximo");

        if (cursos.add(new Curso(codigo, nombre, aforo))) {
            System.out.println("Curso añadido.");
        } else {
            System.out.println("Error: ya existe un curso con el código " + codigo);
        }
    }

    private static void inscribirEstudiante(Scanner sc, HashSet<Estudiante> estudiantes, HashSet<Curso> cursos) {
        int id = leerEnteroPositivo(sc, "ID del estudiante");
        String codigo = leerTexto(sc, "Código del curso");

        Estudiante e = buscarEstudiante(estudiantes, id);
        Curso c = buscarCurso(cursos, codigo);

        if (e == null) {
            System.out.println("Error: no existe un estudiante con el ID " + id);
        } else if (c == null) {
            System.out.println("Error: no existe un curso con el código " + codigo);
        } else if (!c.hayPlaza()) {
            System.out.println("Error: el curso " + codigo + " ha alcanzado su aforo máximo.");
        } else if (e.getCursosMatriculados().contains(codigo)) {
            System.out.println("Error: el estudiante ya está matriculado en el curso.");
        } else {
            c.matricular(id);
            e.getCursosMatriculados().add(codigo);
            System.out.println("Estudiante matriculado en el curso.");
        }
    }

    private static void darDeBaja(Scanner sc, HashSet<Estudiante> estudiantes, HashSet<Curso> cursos) {
        int id = leerEnteroPositivo(sc, "ID del estudiante");
        String codigo = leerTexto(sc, "Código del curso");

        Estudiante e = buscarEstudiante(estudiantes, id);
        Curso c = buscarCurso(cursos, codigo);

        if (e == null) {
            System.out.println("Error: no existe un estudiante con el ID " + id);
        } else if (c == null) {
            System.out.println("Error: no existe un curso con el código " + codigo);
        } else if (!e.getCursosMatriculados().contains(codigo)) {
            System.out.println("Error: el estudiante no está matriculado en el curso.");
        } else {
            e.getCursosMatriculados().remove(codigo);
            c.desmatricular(id);
            System.out.println("Estudiante dado de baja del curso.");
        }
    }

    private static void verDetallesEstudiante(Scanner sc, HashSet<Estudiante> estudiantes, HashSet<Curso> cursos) {
        int id = leerEnteroPositivo(sc, "ID del estudiante");

        Estudiante e = buscarEstudiante(estudiantes, id);
        if (e == null) {
            System.out.println("Error: no existe un estudiante con el ID " + id);
            return;
        }

        System.out.println("Estudiante: " + e.getNombre() + " (ID " + e.getId() + ")");
        if (e.getCursosMatriculados().isEmpty()) {
            System.out.println("No está matriculado en ningún curso.");
        } else {
            System.out.println("Cursos matriculados:");
            for (String codigo : e.getCursosMatriculados()) {
                Curso c = buscarCurso(cursos, codigo);
                System.out.println("  - " + codigo + (c != null ? " (" + c.getNombre() + ")" : ""));
            }
        }
    }

    private static int leerEnteroPositivo(Scanner sc, String campo) {
        int valor;
        while (true) {
            System.out.print(campo + ": ");
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                sc.nextLine();
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Error: el valor debe ser un número positivo.");
            } else {
                sc.nextLine();
                System.out.println("Error: introduce un número válido.");
            }
        }
    }

    private static String leerTexto(Scanner sc, String campo) {
        String texto;
        while (true) {
            System.out.print(campo + ": ");
            texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: el campo no puede estar vacío.");
        }
    }

    private static Estudiante buscarEstudiante(HashSet<Estudiante> estudiantes, int id) {
        for (Estudiante e : estudiantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    private static Curso buscarCurso(HashSet<Curso> cursos, String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }
}

class Estudiante {
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
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Estudiante{id=" + id + ", nombre='" + nombre + "'}";
    }
}

class Curso {
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
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "Curso{codigo='" + codigo + "', nombre='" + nombre + "', aforo=" + aforo + "}";
    }
}
