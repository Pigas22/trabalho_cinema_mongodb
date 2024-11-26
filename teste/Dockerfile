FROM openjdk:21

VOLUME /tmp

ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS

WORKDIR /src

COPY . .
RUN ./mvnw clean package
COPY target/teste-1.0-SNAPSHOT.jar trabalhocinemamongodb.jar

EXPOSE 8000

ENTRYPOINT exec java $JAVA_OPTS -jar trabalhocinemamongodb.jar