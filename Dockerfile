FROM openjdk:8-jdk-alpine
### run as non-root user.
RUN addgroup -S demogrp && adduser -S demousr -G demogrp
USER demousr:demogrp
### locate the jar & start container
ARG JAR_FILE=target/companyservice-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]