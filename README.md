🛝 Playground API

Backend REST desarrollado con Spring Boot para la gestión de parques infantiles, sus reseñas, fotos y usuarios, con autenticación JWT, roles, Swagger y control de calidad con Sonar.

🚀 Tecnologías

Java 21

Spring Boot

Spring Web

Spring Data JPA

Spring Security

JWT (JSON Web Tokens)

PostgreSQL

MapStruct

Lombok

Swagger / OpenAPI

SonarQube

Maven

🧱 Arquitectura

Arquitectura en capas:

controller
service
repository
dto
mapper
model
security


Principios aplicados:

Separación de responsabilidades

DTOs para exposición de datos

Seguridad desacoplada

Código preparado para escalado

🗄️ Modelo de dominio

Playground

Nombre

Dirección

Coordenadas (lat / lng)

Descripción

Fotos

Valoración media

Fecha de creación

Review

Usuario

Puntuación (score)

Comentario

Fecha

Relación con Playground

User

Username

Password cifrada

Roles (USER, ADMIN)

🔐 Seguridad

Autenticación

JWT con HS256

Tokens enviados mediante:

Authorization: Bearer <token>


Roles

USER

ADMIN

Control de acceso

Endpoints públicos:

Registro

Login

Endpoints protegidos:

Gestión de parques

Reseñas

Acceso por rol configurable en SecurityConfig

🔑 Endpoints de autenticación

Registrar usuario

POST /api/auth/register


{
"username": "usuario",
"password": "password123"
}


Login

POST /api/auth/login


Respuesta:

JWT TOKEN


🛝 Playground API

Listar parques

GET /api/playground


Obtener parque por ID

GET /api/playground/{id}


Crear parque (ADMIN)

POST /api/playground
Authorization: Bearer <token>


{
"name": "Parque Central",
"address": "Calle Mayor",
"latitude": 36.84,
"longitude": -2.46,
"description": "Zona infantil",
"photos": [
"https://url1.jpg",
"https://url2.jpg"
]
}


Actualizar parque

PUT /api/playground/{id}
Authorization: Bearer <token>


Eliminar parque (ADMIN)

DELETE /api/playground/{id}
Authorization: Bearer <token>


Añadir foto a parque

POST /api/playground/{id}/photos
Authorization: Bearer <token>


{
"url": "https://nueva-foto.jpg"
}


Eliminar foto

DELETE /api/playground/{id}/photos
Authorization: Bearer <token>


⭐ Reviews API

Listar reseñas de un parque

GET /api/reviews/playground/{playgroundId}


Crear reseña

POST /api/reviews
Authorization: Bearer <token>


{
"username": "usuario",
"score": 5,
"comment": "Muy buen parque",
"playgroundId": 1
}


Actualizar reseña

PUT /api/reviews/{id}
Authorization: Bearer <token>


Eliminar reseña

DELETE /api/reviews/{id}
Authorization: Bearer <token>


📊 Valoración media

La valoración media del parque se recalcula automáticamente cuando:

Se crea una reseña

Se actualiza una reseña

Se elimina una reseña

🧪 Swagger

Documentación interactiva disponible en:

http://localhost:8081/swagger-ui/index.html


Incluye:

Todos los endpoints

DTOs

Autenticación JWT con botón Authorize

🗄️ Base de datos

PostgreSQL

Generación automática de tablas con Hibernate

Relaciones:

Playground → Photos (1:N)

Playground → Reviews (1:N)

⚙️ Configuración

application.properties:

jwt.secret=CAMBIAR_POR_CLAVE_DE_256_BITS


📈 Calidad de código

Integración con SonarQube

Control de:

Code smells

Complejidad

Buenas prácticas

Preparado para CI/CD

🧠 Buenas prácticas aplicadas

DTOs desacoplados de entidades

Passwords cifradas

Seguridad por filtros

Código preparado para producción

Controllers separados por dominio

🚧 Próximas mejoras (opcional)

Paginación y filtros

Subida real de imágenes (S3 / Cloudinary)

Docker

Tests unitarios y de integración

Refresh Tokens

Rate limiting

👨‍💻 Autor

Proyecto desarrollado por Fran GuelfoBackend Java · Spring Boot · APIs REST · Seguridad JWT