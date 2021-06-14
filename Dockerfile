FROM adoptopenjdk/openjdk11:latest
RUN mkdir /usr/src/w2mdocker
COPY ./target/w2m-0.0.1-SNAPSHOT.jar /usr/src/w2mdocker
WORKDIR /usr/src/w2mdocker
EXPOSE 8080
CMD ["java", "-jar","-Dspring.redis.host=rediscontainer", "/usr/src/w2mdocker/w2m-0.0.1-SNAPSHOT.jar"]
