# Build stage
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# Run stage
FROM eclipse-temurin:11-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
