FROM ubuntu:20.04

MAINTAINER Ilya Lapshinov <lis6608@gmail.com>

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 \
    PATH=$JAVA_HOME/bin:$PATH

RUN echo "Europe/Moscow" | tee /etc/timezone \
    && apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install -y openjdk-11-jre

EXPOSE 3306
ENTRYPOINT java -version && /bin/bash