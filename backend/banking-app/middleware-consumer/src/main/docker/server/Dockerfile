FROM openjdk:11
WORKDIR /
COPY maven/*.jar /middleware-consumer.jar

ENTRYPOINT ["java", "-jar",  "/middleware-consumer.jar"]
USER root