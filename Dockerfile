# Stage 1: Build the Spring Boot application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the final lightweight runtime image
# Use a slim OpenJDK image for smaller size
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar
# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080
# Define the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]