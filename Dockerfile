FROM maven:3.6.3-openjdk-11-slim
ENV DB_HOST=host \
     DB_PORT=port \
     DB_NAME=db \
     DB_USER=username \
     DB_PASSWORD=password \
     TOKEN=token

COPY ./ /usr/local/challenge

WORKDIR /usr/local/challenge/

RUN mvn -Dmaven.test.skip=true clean package

EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar -Duser.timezone=$TIMEZONE -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=30 /usr/local/challenge/target/*.jar" ]