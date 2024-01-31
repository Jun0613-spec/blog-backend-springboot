# FROM openjdk:17
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} target/blog-0.0.1-SNAPSHOT.jar
# ENTRYPOINT ["java", "-jar", "target/blog-0.0.1-SNAPSHOT.jar"]

# Stage 1: Build
FROM maven:3.8.4 AS build
WORKDIR /backend
COPY . /backend
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17
WORKDIR /backend
COPY --from=build /backend/target/*.jar /backend/blog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "blog-0.0.1-SNAPSHOT.jar"]