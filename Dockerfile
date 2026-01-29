FROM maven:3.9-eclipse-temurin-21-slim AS build
WORKDIR /app
COPY . .
RUN mvn -Pproduction package

FROM eclipse-temurin:21-jre
COPY --from=buildder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar","-Dspring.profiles.active=pro" ,"app.jar" ]