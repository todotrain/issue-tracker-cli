# Stage 1: Build the application
FROM maven:3.9.8-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Run the Maven build
RUN mvn clean package




# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the target directory to the working directory in the container
COPY --from=build /app/target/cli.jar cli.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "cli.jar"]
CMD []
