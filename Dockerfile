# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el jar generado por Maven
COPY target/*.jar app.jar

# Puerto del Spring Boot
EXPOSE 8080

# Arranque de la app
ENTRYPOINT ["java", "-jar", "app.jar"]
