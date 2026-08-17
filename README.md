# Java Testing Lab

Repositorio de práctica y experimentación con el ecosistema Java.

---

## Fundamentos de Java

| Proyecto | Qué demuestra |
|----------|---------------|
| **firstIdea/** | `ArrayList<String>`, iteración con for-each y lambdas |
| **practicesArrays/** | Operaciones con `ArrayList`: add, get, remove |
| **practiceHashSets/** | Uso básico de `HashSet` y eliminación de duplicados |
| **praticeHashMap/** | `HashMap<Integer, String>` y pares clave-valor |
| **manejoExcepciones/** | Excepciones personalizadas (`InsufficientBalanceException`) y manejo con try-catch |

---

## Programación Orientada a Objetos (OOP)

### Encapsulación y Composición

| Proyecto | Qué demuestra |
|----------|---------------|
| **oop1/** | Composición: `Car` contiene un `Engine`, delegación de responsabilidades |
| **oop2/** | Constructores y sobrecarga: 6 constructores en `User`, pitfall de ambigüedad |
| **oop3/** | Sobrecarga de métodos: 5 versiones de `accelerate()` con distintos parámetros |

### Herencia y Polimorfismo

| Proyecto | Qué demuestra |
|----------|---------------|
| **oop4/** | Herencia simple: `Employee extends Person`, `super()`, `instanceof`, downcasting |
| **oop5/** | Herencia multinivel: `Person → Employee → Manager`, arrays polimórficos, cálculo de vacaciones |
| **oop6/** | Interfaces: interfaz `Flyer` implementada por `Airplane`, `UFO` y `Superhero` con menú interactivo |

---

## Proyectos Aplicados

| Proyecto | Qué demuestra |
|----------|---------------|
| **ConsoleBankApp/** | App bancaria v1: lógica inline, menú con switch-case y Scanner |
| **ConsoleBankAppUp/** | Refactorización: misma lógica pero con métodos separados y mejor organización |
| **MatriculacionManual/** | POO completa: clases `Estudiante`/`Curso` con equals/hashCode, matriculación con `HashSet` |
| **MatriculacionManualJava/** | Mismo sistema de matriculación con validación de entrada robusta |

---

## Web y Frameworks

| Proyecto | Qué demuestra |
|----------|---------------|
| **Proyecto Servlet JSP/** | Servlet Jakarta 6.0 + JSP, configuración web.xml, packaging WAR (Tomcat 10.1) |
| **Springboot/DependencyInjection/** | Inyección de dependencias con Spring Boot: `@Autowired`, `@Qualifier`, inyección por campo/constructor/setter |
| **demo/** | Scaffold de Spring Boot 4.1.0 (template base) |
| **Maven APP/** | Configuración de proyecto Maven con Java 25 |

---

## Cómo ejecutar los proyectos OOP

```powershell
# Ejemplo: oop1
javac -d out oop1\src\TestDrive.java oop1\src\oop1\Car.java oop1\src\oop1\Engine.java
java -cp out TestDrive
```

Para los proyectos Maven o Spring Boot, usar el IDE (IntelliJ IDEA o Eclipse) o `mvn spring-boot:run`.
