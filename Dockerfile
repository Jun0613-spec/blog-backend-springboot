# FROM openjdk:17
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} target/blog-0.0.1-SNAPSHOT.jar
# ENTRYPOINT ["java", "-jar", "target/blog-0.0.1-SNAPSHOT.jar"]

# Stage 1: Build
FROM maven:3.8.4 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Stage 2: Run
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]