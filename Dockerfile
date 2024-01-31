FROM openjdk:17
WORKDIR /backend
COPY --from=build /backend/target/*.jar /backend/blog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "blog-0.0.1-SNAPSHOT.jar"]