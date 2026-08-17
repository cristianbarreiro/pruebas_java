# Gestor de Subscripciones (Java - Consola + Maven)

Aplicación de consola en Java para gestionar suscripciones mensuales/anuales a
aplicaciones (Netflix, Spotify, GitHub, herramientas SaaS, etc.).

## Características

### Gestión
- Alta, edición y eliminación de suscripciones.
- Tipo de facturación: **mensual** o **anual**, con cálculo automático del próximo pago.
- Registrar pago (avanza automáticamente la fecha de próximo cobro).
- Cancelar / reactivar suscripciones sin perder el historial.
- Buscar por nombre.

### Fechas
- Próximas a vencer en los próximos X días.
- Timeline de pagos cronológico.
- Calendario de pagos del mes.
- Ver por rango de fechas.
- Gastos por mes del año.

### Resumen
- Dashboard con resumen rápido al iniciar.
- Resumen de gastos: total mensual, total anual y desglose por categoría.

### Persistencia
- Los datos se guardan automáticamente en `data/subscripciones.csv`.

## Estructura del proyecto

```
subscription_gestor/
├── pom.xml
├── README.md
├── data/                              # Se crea automáticamente
│   └── subscripciones.csv
└── src/main/java/com/gestorsubscripciones/
    ├── Main.java                      # Menú de consola
    ├── Subscripcion.java              # Modelo de datos
    └── GestorSubscripciones.java      # Lógica de negocio + persistencia
```

## Requisitos

- JDK 25 o superior (configurable en `pom.xml`)
- Maven 3.9+

## Cómo compilar y ejecutar

### Desde IntelliJ IDEA
1. Abrir como proyecto Maven
2. Ejecutar `Main.java`

### Desde línea de comandos
```bash
cd subscription_gestor
mvn compile exec:java -Dexec.mainClass="com.gestorsubscripciones.Main"
```

### Compilar manualmente sin Maven
```bash
javac -d out src/main/java/com/gestorsubscripciones/*.java
java -cp out com.gestorsubscripciones.Main
```

## Uso rápido

Al ejecutar la aplicación verás un menú numerado con 15 opciones organizadas
en categorías: Gestión, Consulta, Fechas y Resumen.

Ejemplo para agregar una suscripción:
```
1. Agregar suscripción
Nombre de la app: Netflix
Categoría: streaming
Precio: 15.99
Tipo de facturación (1=Mensual, 2=Anual): 1
Fecha de inicio (yyyy-MM-dd) [enter = hoy]: 2026-08-15
Notas (opcional): Plan estándar
```

## Próximos pasos sugeridos

- Migrar la persistencia a una base de datos (SQLite/H2).
- Agregar notificaciones/recordatorios antes del vencimiento.
- Construir una versión con interfaz gráfica (JavaFX) o una API REST (Spring Boot).
