# ===== BUILD STAGE =====
FROM maven:3.9-amazoncorretto-21-alpine AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY . .
RUN mvn clean package -Pproduction -DskipTests

# ===== RUNTIME STAGE =====
FROM amazoncorretto:21-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=pro","-jar","/app/app.jar"]