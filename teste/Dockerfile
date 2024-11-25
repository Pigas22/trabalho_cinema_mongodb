FROM openjdk:21
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/teste-1.0-SNAPSHOT.jar trabalhocinemamongodb.jar
CMD ["./mvnw", "clean", "package"]
EXPOSE 8000
ENTRYPOINT exec java $JAVA_OPTS -jar trabalhocinemamongodb.jar

WORKDIR /src

COPY . .
