# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install

# Stage 2: Create the final image
FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/PFE-Back-End-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "PFE-Back-End-0.0.1-SNAPSHOT.jar"]
