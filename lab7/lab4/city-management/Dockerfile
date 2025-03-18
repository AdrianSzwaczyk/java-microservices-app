FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="Lab 6"
LABEL org.opencontainers.image.authors="Adrian Szwaczyk"

ENV VERSION="0.0.1-SNAPSHOT"
ENV SERVER_PORT=8080
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://elements:5432/elements
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.postgresql.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

EXPOSE 8080

COPY target/city-management-${VERSION}.jar /opt/city-management/city-management.jar

CMD ["java", "-jar", "/opt/city-management/city-management.jar"]
