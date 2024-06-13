
# Stage 1: Build stage
FROM maven:3-amazoncorretto-21 AS build
WORKDIR /app


# Copy pom.xml and download dependencies (cached)
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2/repository mvn dependency:go-offline -B

# Copy source code and build

COPY src ./src
RUN --mount=type=cache,target=/root/.m2/repository mvn clean package -DskipTests

# Stage 2: Run stage
FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/target/edutie-backend.jar .
ENTRYPOINT ["java", "-jar", "edutie-backend.jar"]