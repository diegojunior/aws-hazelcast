FROM openjdk:8-jre-slim

ARG JAR_FILE=target/*.jar

COPY config/*.xml /config/
COPY ${JAR_FILE} app.jar

RUN apt-get update && apt-get -y install curl
RUN groupadd -o -g 1000 booter \
    && useradd -o -r -m -u 1000 -g 1000 booter \
    && chown -R booter:booter app.jar

EXPOSE 8081

VOLUME /tmp

USER booter

# run app as user 'booter'
ENTRYPOINT java $JAVA_OPTS -jar app.jar $APP_OPTS