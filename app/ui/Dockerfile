################
# Run in NGINX #
################
FROM nginx:latest

ARG ENVIRONMENT
ARG VERSION

ENV APP_VERSION $VERSION
ENV APP_ENVIRONMENT $ENVIRONMENT


#COPY nginx.conf /etc/nginx/conf.d/default.conf
#COPY dist/app-ui/* /usr/share/nginx/html

COPY nginx.conf  /etc/nginx/conf.d/default.conf
COPY dist/app-ui /usr/share/nginx/html

CMD ["/bin/sh",  "-c",  "envsubst < /usr/share/nginx/html/assets/env.template.js > /usr/share/nginx/html/assets/env.js && exec nginx -g 'daemon off;'"]
# Expose port 80
EXPOSE $API_UI_PORT_EXT
