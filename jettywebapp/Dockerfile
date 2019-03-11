FROM openjdk:8-jre-slim

COPY ./target/jettywebapp-jar-with-dependencies.jar /opt/jettywebapp.jar

EXPOSE 80

# 'exec' needs to be added to ENTRYPOINT command to make sure java process receives SIGTERM upon 'docker stop'
# see https://docs.docker.com/engine/reference/builder/#entrypoint
ENTRYPOINT exec java ${JAVA_OPTS} -jar /opt/jettywebapp.jar