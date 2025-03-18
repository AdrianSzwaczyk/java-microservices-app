FROM nginx:1.23.3

LABEL org.opencontainers.image.title="Lab 6"
LABEL org.opencontainers.image.authors="Adrian Szwaczyk"

ENV API_URL http://gateway:8080/api

EXPOSE 8080

ADD dist/angular-app/browser /usr/share/nginx/html/
ADD nginx.conf /etc/nginx/conf.d/default.conf