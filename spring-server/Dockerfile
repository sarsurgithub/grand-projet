FROM openjdk:13-alpine

COPY target/*.jar server.jar

ENTRYPOINT ["java", "-jar", "server.jar"]