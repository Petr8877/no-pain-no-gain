FROM bellsoft/liberica-openjdk-alpine:latest
MAINTAINER Petr

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]