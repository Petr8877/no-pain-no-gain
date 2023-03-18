FROM bellsoft/liberica-openjdk-alpine:latest
MAINTAINER Petr

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]