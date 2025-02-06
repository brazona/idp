################
# BackEnd #
################
FROM openjdk:21 AS backend
LABEL maintainer=BrazonaTech
LABEL stage="backend"
LABEL author="BrazonaTech"
VOLUME /tmp

ARG ARG_SPRING_ACTIVE_PROFILE=local
ARG ARG_CONFIG_SERVER_HOST=localhost:8888
ARG ARG_CONFIG_SERVER_USER=admin
ARG ARG_CONFIG_SERVER_PASS=admin

ENV SPRING_ACTIVE_PROFILE=$ARG_SPRING_ACTIVE_PROFILE
ENV CONFIG_SERVER_HOST=$ARG_CONFIG_SERVER_HOST
ENV CONFIG_SERVER_USER=$ARG_CONFIG_SERVER_USER
ENV CONFIG_SERVER_PASS=$ARG_CONFIG_SERVER_PASS

WORKDIR /app

COPY ./api/target/*.jar /app
COPY ./api/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE $API_PORT_INTERNAL

################
# FrontEnd #
################
FROM nginx:latest AS frontend
LABEL maintainer=BrazonaTech
LABEL stage="frontend"
LABEL author="BrazonaTech"

ARG ARG_APP_API_URL=http://localhost:8080
ARG ARG_BASIC_USER=user
ARG ARG_BASIC_PASS=user

ENV APP_API_URL=$ARG_APP_API_URL
ENV BASIC_USER=$ARG_BASIC_USER
ENV BASIC_PASS=$ARG_BASIC_PASS

COPY --from=backend /app/*.jar app.jar

COPY ./ui/nginx.conf  /etc/nginx/conf.d/default.conf
COPY ./ui/dist/app-ui/browser /usr/share/nginx/html

CMD ["/bin/sh",  "-c",  "envsubst < /usr/share/nginx/html/assets/env.template.js > /usr/share/nginx/html/assets/env.js && exec nginx -g 'daemon off;'"]

EXPOSE $UI_PORT_INTERNAL