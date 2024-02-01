FROM maven:3.8.3-openjdk-17 AS build 
COPY src /blog2/backend/src 
COPY pom.xml /blog2/backend 
RUN mvn -f /blog2/backend/pom.xml clean package 
EXPOSE 8080 
ENTRYPOINT ["java","-jar","blog-0.0.1-SNAPSHOT.jar"]