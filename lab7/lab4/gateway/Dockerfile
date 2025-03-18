FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="Lab 6"
LABEL org.opencontainers.image.authors="Adrian Szwaczyk"

ENV VERSION="0.0.1-SNAPSHOT"
ENV SERVER_PORT=8080

ENV GATEWAY_HOST=http://gateway:8080
ENV COUNTRY_URL=http://country-management:8080
ENV CITY_URL=http://city-management:8080

EXPOSE 8080

COPY target/gateway-${VERSION}.jar /opt/gateway/gateway.jar

CMD ["java", "-jar", "/opt/gateway/gateway.jar"]
