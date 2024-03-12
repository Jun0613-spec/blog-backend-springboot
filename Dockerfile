# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/blog-0.0.1-SNAPSHOT.jar /app/blog-0.0.1-SNAPSHOT.jar
COPY application.properties /app/
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "blog-0.0.1-SNAPSHOT.jar"]


