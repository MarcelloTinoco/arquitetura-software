#FROM openjdk

#VOLUME /tmp
#ADD target/siplad-service-discovery-1.0.jar ssd.jar
#RUN sh -c 'touch /ssd.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ssd.jar"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]