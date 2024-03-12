#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests


#
# Package stage
#
FROM openjdk:17
COPY --from=build /target/blog-0.0.1-SNAPSHOT.jar blog.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "blog.jar"]


