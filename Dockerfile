FROM maven:3-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Pproduction -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/manager.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=pro","-jar","app.jar"]