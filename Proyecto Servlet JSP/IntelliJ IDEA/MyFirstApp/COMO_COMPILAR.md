# Cómo compilar y ejecutar MyFirstApp en IntelliJ IDEA

Proyecto: Jakarta Servlet 6.0 / JSP · Java 21 · Maven (WAR)
IntelliJ IDEA Ultimate sin trial activo **no muestra el menú "Application Servers"**,
así que se compila con Maven y se despliega en un Tomcat independiente.

## Requisitos

| Herramienta | Dónde está |
|---|---|
| JDK 21 o 25 | `C:\Users\crist\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.3.9-hotspot` |
| Maven | `C:\Program Files\Apache\Maven\apache-maven-4.0.0-rc-5` |
| Tomcat 10.1.57 | `C:\apache-tomcat-10.1.57\apache-tomcat-10.1.57` |

## 1. Abrir el proyecto

1. `File > Open...` → selecciona la carpeta `MyFirstApp`.
2. IntelliJ detecta el `pom.xml` e importa el proyecto Maven automáticamente.

## 2. Compilar el WAR

**Opción A — Panel Maven (recomendada):**
1. Abre la pestaña `Maven` (lateral derecho).
2. `MyFirstApp > Lifecycle > package` → doble clic.
   - Limpia primero con `clean` si algo falla o hay restos.
3. El WAR se genera en `target/MyFirstApp.war`.

**Opción B — Terminal:**
```powershell
mvn clean package
```

## 3. Desplegar en Tomcat

1. Copia el WAR a la carpeta de despliegue:
   ```
   target/MyFirstApp.war  →  C:\apache-tomcat-10.1.57\apache-tomcat-10.1.57\webapps\
   ```
2. Arranca Tomcat:
   ```
   C:\apache-tomcat-10.1.57\apache-tomcat-10.1.57\bin\startup.bat
   ```
   > Si da error de `JAVA_HOME`, lánzalo desde PowerShell con:
   > ```powershell
   > $env:JAVA_HOME = "C:\Users\crist\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.3.9-hotspot"
   > C:\apache-tomcat-10.1.57\apache-tomcat-10.1.57\bin\startup.bat
   > ```

3. Espera unos segundos y abre:
   - App: `http://localhost:8080/MyFirstApp/`
   - Servlet: `http://localhost:8080/MyFirstApp/HelloServlet`

## 4. Recarga tras cambios

1. Recompila (paso 2).
2. Copia de nuevo el WAR a `webapps` y **reinicia Tomcat**:
   ```
   ...\bin\shutdown.bat
   ...\bin\startup.bat
   ```
   (Tomcat redeplega el WAR si lo sobreescribes y borras la carpeta `webapps\MyFirstApp` explotada antes, pero lo más fiable es reiniciar.)

## Notas

- **Puerto 8080 ocupado:** el Tomcat de Eclipse también usa el 8080. Asegúrate de apagar uno de los dos antes de arrancar el otro.
- **Sin Ultimate:** no verás *Application Servers* ni run configuration de Tomcat en el IDE. Maven + Tomcat externo cubre todo el flujo.
- **Dónde está el código:**
  - Servlet: `src/main/java/com/learnquest/HelloServlet.java`
  - JSP de inicio: `src/main/webapp/index.jsp`
  - Configuración: `src/main/webapp/WEB-INF/web.xml`
