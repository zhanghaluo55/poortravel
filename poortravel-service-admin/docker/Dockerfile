FROM openjdk:8-jre

MAINTAINER Rainer <346143235@qq.com>

#ENV APP_VERSION 1.0.0-SNAPSHOT
ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://raw.githubusercontent.com/zhanghaluo55/wsndc/master/dockerize-linux-amd64-v0.6.1.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz
RUN mkdir /app

COPY poortravel-service-admin-1.0.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://47.101.152.189:8889/poortravel-service-admin/prod/master", "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8501