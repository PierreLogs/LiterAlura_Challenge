# LiterAlura - Catálogo de Libros 📚

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

**LiterAlura** es una aplicación de consola desarrollada en Java utilizando el framework **Spring Boot**. El proyecto interactúa con la API de [Gutendex](https://gutendex.com/) para buscar información sobre libros y autores, almacenándolos en una base de datos PostgreSQL para realizar consultas avanzadas y estadísticas dinámicas.



## ✨ Funcionalidades

El sistema ofrece un menú interactivo con las siguientes opciones:

1.  **Buscar libro por título (API):** Consulta la API de Gutendex y persiste el libro y su autor en la base de datos (evitando duplicados).
2.  **Mostrar historial de libros buscados:** Lista todos los libros almacenados en la base de datos local.
3.  **Listar autores registrados:** Muestra todos los autores con sus respectivos años de nacimiento y fallecimiento.
4.  **Listar autores vivos en determinado año:** Filtra autores que estaban vivos en un año específico ingresado por el usuario.
5.  **Listar libros por idioma:** Filtra libros por códigos (es, en, fr, pt).
6.  **Top 10 Libros más descargados:** Consulta directamente a la base de datos los libros con mayor número de descargas.
7.  **Generar estadísticas de descargas:** Utiliza Java Streams y `DoubleSummaryStatistics` para mostrar promedio, máximo y mínimo de descargas.
8.  **Buscar autor por nombre:** Localiza autores específicos dentro de la base de datos utilizando filtros `IgnoreCase`.



---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17+ (Desarrollado en Java 25)
* **Framework:** Spring Boot 3.4.0 (o superior)
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL
* **Gestión de Dependencias:** Maven
* **Consumo de API:** `HttpClient` y `Jackson` para el procesamiento de JSON.

---

## 🚀 Configuración e Instalación

### Requisitos previos
* JDK 17 o superior instalado.
* Maven instalado.
* Instancia de PostgreSQL activa.

### Pasos para ejecutar
1.  Clona este repositorio:
    ```bash
    git clone [https://github.com/PierreLogs/LiterAlura_Challenge.git](https://github.com/PierreLogs/LiterAlura_Challenge.git)
    ```
2.  Configura tus **variables de entorno** en tu IDE:
    * `DB_HOST`: Host de tu base de datos (ej. `localhost:5432`)
    * `DB_NAME`: Nombre de la base de datos (ej. `literalura`)
    * `DB_USER`: Tu usuario de PostgreSQL.
    * `DB_PASSWORD`: Tu contraseña de PostgreSQL.

3.  Ejecuta la aplicación desde tu terminal o IDE:
    ```bash
    mvn spring-boot:run
    ```

---

## 📊 Estructura del Código

* **`model`**: Contiene las entidades JPA (`Libros`, `Autor`) y los Records para el mapeo de la API.
* **`repository`**: Interfaces que gestionan las consultas a la base de datos mediante *Derived Queries* y `@Query`.
* **`service`**: Lógica de negocio, consumo de API y conversión de datos.
* **`controller`**: Clase `Principal` que gestiona el flujo del menú y la interacción con el usuario.

---

## 📝 Autor
Desarrollado por **PierreLogs** como parte del Challenge de **Alura Latam** y **Oracle Next Education (ONE)**.
