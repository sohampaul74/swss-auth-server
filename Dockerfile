FROM openjdk:8-jdk-alpine
MAINTAINER org.swss
COPY target/auth-server-0.0.1-SNAPSHOT.jar docker-auth-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/docker-auth-server-0.0.1-SNAPSHOT.jar"] 
