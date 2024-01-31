FROM openjdk:17
ARG JAR_FILE=target/**.jar
COPY ${JAR_FILE} target/blog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "target/blog-0.0.1-SNAPSHOT.jar"]
