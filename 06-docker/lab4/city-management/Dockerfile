FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="Lab 6"
LABEL org.opencontainers.image.authors="Adrian Szwaczyk"

ENV VERSION="0.0.1-SNAPSHOT"
ENV SERVER_PORT=8080

EXPOSE 8080

COPY target/city-management-${VERSION}.jar /opt/city-management/city-management.jar

CMD ["java", "-jar", "/opt/city-management/city-management.jar"]
