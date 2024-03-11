
FROM openjdk:17

WORKDIR /backend
COPY target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]