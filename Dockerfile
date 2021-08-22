FROM openjdk:8-jdk-alpine
FROM maven
### setting up working directories
#WORKDIR src/app
### Copying resources
COPY pom.xml .
COPY src src
### Maven build
RUN mvn clean package
RUN ls -ltr
### locate the jar & start container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]