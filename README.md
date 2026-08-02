# Pruebas Java — OOP

Proyecto de práctica en Java que demuestra los fundamentos de la programación orientada a objetos: encapsulación, colaboración entre objetos y composición.

## Estructura

```
oop1/src/
├── TestDrive.java          # Punto de entrada (main)
└── oop1/
    ├── Car.java            # Auto que usa un Engine
    └── Engine.java         # Motor con RPM
```

## Cómo compilar y ejecutar

Desde la raíz del proyecto:

```powershell
javac -d out oop1\src\TestDrive.java oop1\src\oop1\Car.java oop1\src\oop1\Engine.java
java -cp out TestDrive
```

Salida esperada:

```
CURR SPEED:17
CURR SPEED:34
CURR SPEED:51
CURR SPEED:51
CURR SPEED:51
```

## En IntelliJ IDEA

- Clic derecho sobre `TestDrive` → **Run 'TestDrive.main()'**.
- La carpeta `out/` (código compilado `.class`) se genera automáticamente y no debe subirse al repositorio.

## Cómo funciona

- `Engine` mantiene las RPM y las incrementa de a 1000 hasta un tope de 3000.
- `Car` recibe un `Engine` en su constructor (composición) y delega en él para acelerar y calcular la velocidad actual.
- `TestDrive` crea los objetos y ejecuta la simulación.
