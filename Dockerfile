FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} target/*.jar
ENTRYPOINT ["java", "-jar", "/*.jar"]
