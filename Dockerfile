FROM maven:3-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/manager.jar manager.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=pro","-jar","manager.jar"]