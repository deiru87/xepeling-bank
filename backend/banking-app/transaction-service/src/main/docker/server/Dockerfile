FROM openjdk:11
WORKDIR /
COPY maven/*.war /transaction-service.war

ENTRYPOINT ["java", "-jar",  "/transaction-service.war"]
USER root