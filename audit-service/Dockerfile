FROM bellsoft/liberica-openjdk-alpine:latest
MAINTAINER Petr

COPY target/audit-service-0.0.1-SNAPSHOT.jar audit-service.jar
ENTRYPOINT ["java", "-jar", "/audit-service.jar"]