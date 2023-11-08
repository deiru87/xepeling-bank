FROM openjdk:11
WORKDIR /
COPY maven/*.war /account-service.war

ENTRYPOINT ["java", "-jar", "/account-service.war"
USER root