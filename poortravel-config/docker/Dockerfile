FROM openjdk:8-jre

MAINTAINER Rainer <346143235@qq.com>

#ENV APP_VERSION 1.0.0-SNAPSHOT
RUN mkdir /app

COPY poortravel-config-1.0.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8889