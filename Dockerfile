# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /backend
COPY . .
RUN mvn clean package -e -X


FROM openjdk:17
WORKDIR /backend
COPY --from=build /backend/target/*.jar /target/app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]
