FROM openjdk:17-oracle
WORKDIR /app
COPY target/blog-0.0.1-SNAPSHOT.jar blog-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "blog-0.0.1-SNAPSHOT.jar"]