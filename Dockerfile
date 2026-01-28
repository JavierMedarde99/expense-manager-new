# Stage 1: Build
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom y dependencias primero para cachear
COPY pom.xml .
COPY src ./src

# Build del proyecto y empaquetado
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar el jar generado en el build
COPY --from=build /app/target/*.jar app.jar

# Puerto que expondrá la app (ajusta si tu app usa otro)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]