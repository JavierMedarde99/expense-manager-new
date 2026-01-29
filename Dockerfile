FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn package -Dvaadin.commercialWithBanner

FROM eclipse-temurin:21-jre
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar","-Dspring.profiles.active=pro" ,"app.jar" ]