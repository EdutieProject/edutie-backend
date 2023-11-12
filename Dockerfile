# Stage 1: Build stage
FROM maven:3-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run stage
FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/edutie-backend.jar .
EXPOSE 8081
CMD ["java", "-jar", "edutie-backend.jar"]