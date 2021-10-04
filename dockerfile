FROM amazoncorretto:11

MAINTAINER Ilya Lapshinov <lis6608@gmail.com>

ADD target/StockExchange-0.0.1-SNAPSHOT.jar StockExchange-0.0.1-SNAPSHOT.jar

EXPOSE 3306
ENTRYPOINT java -version && /bin/bash
