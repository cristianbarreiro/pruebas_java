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
| **first/** | Interfaces funcionales (`@FunctionalInterface`), lambdas y Streams (`filter`, `map`, `sorted`, `collect`) |
| **tablasMultiplicar/** | Menú interactivo en consola con `Scanner` y `switch-case`: tabla de multiplicar individual y completa |

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

## Concurrencia y Threads

| Proyecto | Qué demuestra |
|----------|---------------|
| **Demo Threads/** | Coordinación entre hilos con `synchronized`, `wait()` y `notify()` (turnos alternados) |
| **syncAndLocks/** | Sincronización explícita con `ReentrantLock` (`lock()`/`unlock()` en `try/finally`) |
| **threadPoolsExecutors/** | Pool de hilos con `ExecutorService` y `Executors.newFixedThreadPool()` |

---

## Proyectos Aplicados

| Proyecto | Qué demuestra |
|----------|---------------|
| **ConsoleBankApp/** | App bancaria v1: lógica inline, menú con switch-case y Scanner |
| **ConsoleBankAppUp/** | Refactorización: misma lógica pero con métodos separados y mejor organización |
| **MatriculacionManual/** | POO completa: clases `Estudiante`/`Curso` con equals/hashCode, matriculación con `HashSet` |
| **MatriculacionManualJava/** | Mismo sistema de matriculación con validación de entrada robusta |
| **subscriptions_gestor/** | Gestor de suscripciones en consola: CRUD, facturación mensual/anual, persistencia en CSV |
| **subscription_gestor/** | Versión ampliada del gestor: 15 opciones, calendario de pagos, dashboards y resúmenes (Maven, Java 25) |

---

## Web y Frameworks

| Proyecto | Qué demuestra |
|----------|---------------|
| **Proyecto Servlet JSP/** | Servlet Jakarta 6.0 + JSP, configuración web.xml, packaging WAR (Tomcat 10.1) |
| **Springboot/DependencyInjection/** | Inyección de dependencias con Spring Boot: `@Autowired`, `@Qualifier`, inyección por campo/constructor/setter |
| **Use-of-Annotation/** | Anotaciones Spring sin servidor web: `@Component`, `@Configuration`, `@ComponentScan` y `ApplicationContext` |
| **demo/** | Scaffold de Spring Boot 4.1.0 (template base) |
| **demoApp/** | CRUD REST con Spring Data JPA + H2, manejo global de excepciones con `@RestControllerAdvice` |
| **Maven APP/** | Configuración de proyecto Maven con Java 25 |

---

## Microservicios (Spring Cloud + Eureka)

| Proyecto | Qué demuestra |
|----------|---------------|
| **Microservices/** | Versión sencilla: un solo controlador REST devuelve dispositivos "Alpha" y "Beta" (sin Eureka) |
| **EurekaDiscoveryServer/** | Servidor de registro y descubrimiento de servicios Netflix Eureka (`@EnableEurekaServer`) |
| **AlphaMicroservice/** | Microservicio REST con endpoint `/alpha`, registrado en Eureka (puerto 8086) |
| **BetaMicroservice/** | Microservicio REST con endpoint `/beta`, registrado en Eureka (puerto 8087) |
| **OnlineStore/** | Cliente web Spring MVC + JSP que consume Alpha/Beta con `RestTemplate` |

---

## Cómo ejecutar los proyectos OOP

```powershell
# Ejemplo: oop1
javac -d out oop1\src\TestDrive.java oop1\src\oop1\Car.java oop1\src\oop1\Engine.java
java -cp out TestDrive
```

Para los proyectos Maven o Spring Boot, usar el IDE (IntelliJ IDEA o Eclipse) o `mvn spring-boot:run`. En el demo de microservicios, levantar primero `EurekaDiscoveryServer` (panel en `http://localhost:8761`) y después `AlphaMicroservice`, `BetaMicroservice` y `OnlineStore`.