# Use the official Eclipse Temurin JDK 17 image as base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /blog2

# Copy the compiled JAR file from the local machine to the container
COPY  backend/target/*.jar app.jar

# Expose port 8000 to the outside world
EXPOSE 8000

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
