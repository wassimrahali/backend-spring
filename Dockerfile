# Use Ubuntu as the base image for the build stage
FROM ubuntu:latest AS build

# Install necessary dependencies
RUN apt-get update && apt-get install openjdk-17-jdk maven -y

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use a lightweight JDK runtime for the final stage
FROM openjdk:17-jdk-slim

# Expose the application port
EXPOSE 8080

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
