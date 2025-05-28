# Use a lightweight Java 21 image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /project

# Copy the JAR file into the image
COPY target/project-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
