FROM bellsoft/liberica-openjdk-alpine:latest
MAINTAINER Petr

COPY target/mail-service-0.0.1-SNAPSHOT.jar mail-service.jar
ENTRYPOINT ["java", "-jar", "/mail-service.jar"]