################
# FrontEnd #
################
FROM nginx:latest AS frontend
LABEL maintainer=BrazonaTech
LABEL stage="frontend"
LABEL author="BrazonaTech"

ARG ARG_APP_API_URL=http://localhost:8080
ARG ARG_APP_API_BASIC_USER=user
ARG ARG_APP_API_BASIC_PASS=user

ENV APP_API_URL=$ARG_APP_API_URL
ENV APP_API_BASIC_USER=$ARG_APP_API_BASIC_USER
ENV APP_API_BASIC_PASS=$ARG_APP_API_BASIC_PASS


COPY ./nginx.conf  /etc/nginx/conf.d/default.conf
COPY ./dist/app-ui/browser /usr/share/nginx/html

CMD ["/bin/sh",  "-c",  "envsubst < /usr/share/nginx/html/assets/env.template.js > /usr/share/nginx/html/assets/env.js && exec nginx -g 'daemon off;'"]

EXPOSE $UI_PORT_INTERNAL