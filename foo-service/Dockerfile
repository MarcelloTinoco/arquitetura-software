FROM openjdk:8u171-jre-alpine
MAINTAINER marcellojorge.tec@gmail.com
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]