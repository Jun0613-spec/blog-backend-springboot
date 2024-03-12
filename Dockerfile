# FROM maven:3.8.3-openjdk-17 AS build
# WORKDIR /app
# COPY . /app/
# RUN mvn clean package -e -X
# --from=build 


FROM openjdk:17
WORKDIR /app
COPY /app/target/*.jar /app/app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","app.jar"]

