FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="Lab 6"
LABEL org.opencontainers.image.authors="Adrian Szwaczyk"

ENV VERSION="0.0.1-SNAPSHOT"
ENV SERVER_PORT=8080
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://elements-db:5432/country-management
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.postgresql.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV CITY_URL=http://city-management:8080

EXPOSE 8080

COPY target/country-management-${VERSION}.jar /opt/country-management/country-management.jar

CMD ["java", "-jar", "/opt/country-management/country-management.jar"]
