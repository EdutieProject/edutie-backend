# Stage 1: Build stage
FROM maven:3-amazoncorretto-21 AS build
WORKDIR /app

# Copy the pom.xml first and run the dependency download step
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run stage
FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/target/edutie-backend.jar .
EXPOSE 8081
CMD ["java", "-jar", "edutie-backend.jar"]