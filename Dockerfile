# FROM openjdk:17
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} target/blog-0.0.1-SNAPSHOT.jar
# ENTRYPOINT ["java", "-jar", "target/blog-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.4 AS build
WORKDIR /backend
COPY . .
RUN mvn clean package -Pprod -DskipTests

FROM openjdk:17
WORKDIR /backend
COPY --from=build target/*.jar blog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "blog-0.0.1-SNAPSHOT.jar"]