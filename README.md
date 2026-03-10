# literatura

Este proyecto es una aplicación desarrollada en **Java con Spring Boot** que permite consultar libros desde una API externa y almacenarlos en una base de datos PostgreSQL.  

La aplicación consume la API de **Gutendex** para buscar libros por título y guarda la información de los libros y sus autores en la base de datos.


# Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Hibernate
- Maven
- API Gutendex
- IntelliJ IDEA

# Funcionalidades

La aplicación funciona mediante un menú en consola que permite:

1. **Buscar libro por título**
2. **Listar libros registrados**
3. **Listar autores registrados**
4. **Listar autores vivos en un año específico**
5. **Listar libros por idioma**
6. **Salir del programa**

Cuando se busca un libro:

- Se consulta la API de Gutendex
- Se obtiene la información del libro
- Se guarda el autor en la base de datos
- Se guarda el libro en la base de datos


# Arquitectura del proyecto

El proyecto sigue una estructura organizada por capas:

src
│
├── client
│ └── GutendexClient.java
│
├── dto
│ ├── DatosAutor.java
│ ├── DatosLibro.java
│ └── DatosRespuesta.java
│
├── model
│ ├── Autor.java
│ ├── Libro.java
│ └── Idioma.java
│
├── repository
│ ├── AutorRepository.java
│ └── LibroRepository.java
│
├── service
│ ├── ConsumoAPI.java
│ └── ConvierteDatos.java
│
├── principal
│ └── Principal.java
│
└── LiteraturaApplication.java


# Base de datos

La aplicación utiliza **PostgreSQL** para almacenar la información de los libros y autores.

## Crear la base de datos

Antes de ejecutar el proyecto debes crear la base de datos:

```sql
CREATE DATABASE literatura3;
```

El archivo de configuración se encuentra en:

src/main/resources/application.properties

Configuración utilizada:

```
spring.application.name=literatura

spring.datasource.url=jdbc:postgresql://localhost:5432/literatura3
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

Hibernate creará automáticamente las tablas necesarias:

autor
libro


Utilizamos una API. que es:
https://gutendex.com/



Proyecto desarrollado por Violett como práctica de aprendizaje en Backend (Java, Spring Boot).
