# Catálogo de Series – CRUD con Spring Boot y H2

Taller de un API REST CRUD para gestionar un catálogo de series, construido con **Spring Boot**, **Spring Data JPA** y la base de datos **H2** (en archivo), con acceso a la **consola web de H2**.

## Requisitos

- **Java 25** (JDK) — verificar con `java -version`
- **Maven** no es obligatorio: el proyecto incluye el wrapper (`mvnw` / `mvnw.cmd`)
- Un cliente HTTP para probar el API: Postman, Insomnia, Thunder Client o `curl`

## Estructura del proyecto

```
catalogoSeriesBackend/
└── series/
    ├── pom.xml
    └── src/main/
        ├── java/com/catalogo/series/
        │   ├── SeriesApplication.java      # Clase principal
        │   ├── controller/SerieController  # Endpoints REST
        │   ├── models/Serie                # Entidad JPA
        │   ├── repository/SerieRepository  # Acceso a datos
        │   └── service/                    # Lógica de negocio
        └── resources/application.properties
```

## Ejecución

1. Clonar el repositorio y entrar a la carpeta del proyecto:

   ```bash
   git clone <url-del-repositorio>
   cd catalogoSeriesBackend/series
   ```

2. Levantar la aplicación con el wrapper de Maven:

   **Windows (PowerShell / CMD)**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   **Linux / macOS / Git Bash**
   ```bash
   ./mvnw spring-boot:run
   ```

   También se puede ejecutar desde el IDE (VS Code, IntelliJ, Eclipse) corriendo la clase `SeriesApplication`.

3. La aplicación queda disponible en **http://localhost:8080**.

### Generar y ejecutar el JAR (opcional)

```bash
./mvnw clean package
java -jar target/series-0.0.1-SNAPSHOT.jar
```

## Consola H2

Con la aplicación corriendo, abrir en el navegador:

**http://localhost:8080/h2-console**

Completar el formulario de conexión con estos datos:

| Campo        | Valor                        |
|--------------|------------------------------|
| Driver Class | `org.h2.Driver`              |
| JDBC URL     | `jdbc:h2:file:./data/seriesdb` |
| User Name    | `sa`                         |
| Password     | *(vacío)*                    |

Presionar **Connect**. Desde ahí se puede consultar la tabla `SERIE`, por ejemplo:

```sql
SELECT * FROM SERIE;
```

> **Nota:** la base de datos se guarda en archivo (`./data/seriesdb.mv.db`), por lo que los datos **persisten** entre reinicios. La ruta es relativa a la carpeta desde donde se ejecuta la aplicación: si se corre desde `series/`, el archivo queda en `series/data/`; si se corre desde el IDE con la raíz del repositorio como directorio de trabajo, queda en `data/`. Las tablas se crean/actualizan automáticamente (`spring.jpa.hibernate.ddl-auto=update`).

## Endpoints del API

URL base: `http://localhost:8080/api/series`

| Método | Ruta                 | Descripción                  |
|--------|----------------------|------------------------------|
| POST   | `/register`          | Registrar una nueva serie    |
| GET    | `/`                  | Listar todas las series      |
| GET    | `/search/{id}`       | Buscar una serie por ID      |
| PUT    | `/update/{id}`       | Actualizar una serie         |
| DELETE | `/delete/{id}`       | Eliminar una serie           |

### Modelo `Serie`

```json
{
  "id": 1,
  "title": "Breaking Bad",
  "platform": "Netflix",
  "qualification": 9.5
}
```

`title`, `platform` y `qualification` son obligatorios; el `id` se genera automáticamente.

### Ejemplos con `curl`

**Crear una serie**
```bash
curl -X POST http://localhost:8080/api/series/register \
  -H "Content-Type: application/json" \
  -d '{"title":"Breaking Bad","platform":"Netflix","qualification":9.5}'
```

**Listar todas**
```bash
curl http://localhost:8080/api/series
```

**Buscar por ID**
```bash
curl http://localhost:8080/api/series/search/1
```

**Actualizar**
```bash
curl -X PUT http://localhost:8080/api/series/update/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Breaking Bad","platform":"Prime Video","qualification":9.8}'
```

**Eliminar**
```bash
curl -X DELETE http://localhost:8080/api/series/delete/1
```

> En PowerShell, usar `curl.exe` en lugar de `curl` y escapar las comillas del JSON, o bien probar los endpoints con Postman.

## Solución de problemas

- **Puerto 8080 ocupado:** agregar `server.port=8081` en `application.properties`.
- **Error de versión de Java:** el proyecto requiere JDK 25; revisar `java -version` y la variable `JAVA_HOME`.
- **La consola H2 no muestra la tabla:** verificar que la JDBC URL sea exactamente `jdbc:h2:file:./data/seriesdb` y que se haya registrado al menos una serie o que la aplicación haya arrancado una vez.
