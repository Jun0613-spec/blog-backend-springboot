FROM openjdk:17

WORKDIR /backend
COPY /target/blog-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]