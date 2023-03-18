FROM postgres:15.2-alpine
MAINTAINER Petr

ADD /product-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /user-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /audit-service/ddl/*.sql /docker-entrypoint-initdb.d/
#ADD /user-service/dml/*.sql /docker-entrypoint-initdb.d/
#ADD /product-service/dml/*.sql /docker-entrypoint-initdb.d/