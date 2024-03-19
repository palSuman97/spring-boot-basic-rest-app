# Use a base image with Java 11 installed
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Spring Boot application JAR file into the container
COPY target/*.jar /app/app.jar

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]

# Expose the port that your Spring Boot application runs on
EXPOSE 8080