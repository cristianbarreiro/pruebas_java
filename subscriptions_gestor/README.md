# Gestor de Subscripciones (Java - Consola)

Aplicación de consola en Java para gestionar subscripciones mensuales/anuales a
aplicaciones (Netflix, Spotify, herramientas SaaS, etc.).

## Características

- Alta, baja y edición de subscripciones.
- Tipo de facturación: **mensual** o **anual**, con cálculo automático del
  próximo pago.
- Registrar pago (avanza automáticamente la fecha de próximo cobro).
- Cancelar / reactivar subscripciones sin perder el historial.
- Buscar por nombre.
- Ver subscripciones próximas a vencer en los próximos X días.
- Resumen de gastos: total mensual, total anual y desglose por categoría
  (streaming, productividad, gaming, etc.), normalizando anuales a su
  equivalente mensual.
- **Persistencia**: los datos se guardan automáticamente en
  `data/subscripciones.csv`, así que no se pierden entre ejecuciones.

## Estructura del proyecto

```
subscripciones-app/
├── src/
│   └── subs/
│       ├── Subscripcion.java          # Modelo de datos
│       ├── GestorSubscripciones.java  # Lógica de negocio + persistencia
│       └── Main.java                  # Menú de consola
├── data/                              # Se crea automáticamente (CSV de datos)
└── README.md
```

## Requisitos

- JDK 11 o superior (usa solo librerías estándar de Java, sin dependencias
  externas).

## Cómo compilar y ejecutar

Desde la carpeta `subscripciones-app`:

```bash
# Compilar
javac -d out src/subs/*.java

# Ejecutar
java -cp out subs.Main
```

En Windows (PowerShell/CMD) los comandos son idénticos.

## Uso rápido

Al ejecutar la aplicación verás un menú numerado. Por ejemplo, para agregar
una subscripción:

```
1. Agregar subscripción
Nombre de la app: Netflix
Categoría: streaming
Precio: 15.99
Tipo de facturación (1=Mensual, 2=Anual): 1
Fecha de inicio (yyyy-MM-dd) [enter = hoy]:
Notas (opcional): Plan estándar
```

El sistema calcula automáticamente la fecha del próximo pago según el tipo
de facturación elegido.

## Próximos pasos sugeridos (evolución del proyecto)

- Migrar la persistencia a una base de datos (SQLite/H2) si el volumen crece.
- Agregar notificaciones/recordatorios antes del vencimiento.
- Construir una versión con interfaz gráfica (JavaFX) o una API REST
  (Spring Boot) reutilizando `Subscripcion` y `GestorSubscripciones` casi
  sin cambios, ya que la lógica de negocio está desacoplada del menú de
  consola.
