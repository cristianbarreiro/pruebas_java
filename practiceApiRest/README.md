# practiceApiRest

Proyecto mínimo para practicar el consumo de una API REST externa en Java.

## Tecnologías

- Java 25
- Maven
- `java.net.http.HttpClient`
- Jackson (`jackson-databind`)

## Dependencias

Requiere un **JDK 25 o superior** en `JAVA_HOME` (verificar con `java -version`).
Maven descarga las dependencias automáticamente:

```powershell
mvn dependency:resolve
```

## Ejecución

```powershell
cd practiceApiRest
mvn compile exec:java
```

## API utilizada

[JSONPlaceholder](https://jsonplaceholder.typicode.com) — API pública de prueba.

Endpoints consultados:

- `GET /posts`
- `GET /posts/1`
- `GET /users/1`

## Ejemplo de respuesta

`GET /posts/1`

```json
{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
  "body": "quia et suscipit\nsuscipit recusandae..."
}
```

Salida en consola:

```text
GET /posts/1
✓ Post encontrado: 1

Post encontrado
----------------
ID: 1
Usuario: 1
Título: sunt aut facere repellat provident occaecati excepturi optio reprehenderit
Contenido: ...
```
