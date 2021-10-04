FROM amazoncorretto:11

MAINTAINER Ilya Lapshinov <lis6608@gmail.com>

ADD target/StockExchange-0.0.1-SNAPSHOT.jar StockExchange-0.0.1-SNAPSHOT.jar

RUN echo "Europe/Moscow" | tee /etc/timezone \
    && apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install -y openjdk-11-jre

EXPOSE 3306
ENTRYPOINT java -version && /bin/bash
